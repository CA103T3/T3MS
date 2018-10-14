package com.crawler.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.session.model.*;
import com.theater.model.TheaterService;
import com.theater.model.TheaterVO;
import com.cinema.model.*;
import com.common.ser.CinemaVOSer;
import com.common.ser.MovieVOSer;
import com.common.ser.SerUtil;
import com.common.ser.SessionVOSer;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;

public class SessionHandler {
    private List<HashMap> movieSessionList;
    //private String dir = "WebContent/resources/crawler/movieinfo"; //workable ?!
    private String dir = "resources/crawler/movieinfo"; //for Java EE Environment
    private String servletContextRealPath;
    private List<HashMap> repeat_sessions_list;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public SessionHandler() {
        // TODO Auto-generated constructor stub
    }

    public SessionHandler(List<HashMap> movieSessionList) {
        this.movieSessionList = movieSessionList;
    }

    public List<String> importDB() {
        //System.out.println("importDB");
        List<String> list = new ArrayList<String>();
        repeat_sessions_list = new ArrayList<HashMap>();
        TheaterService tSvc = new TheaterService();
        MovieService mSvc = new MovieService();
        CinemaService cSvc = new CinemaService();
        SessionService sSvc = new SessionService();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0; i < movieSessionList.size(); i++) {
            String movieTitle = (String) movieSessionList.get(i).get("movieTitle");
            //System.out.println(this.getClass().getSimpleName() + " importDB movieTitle :" + movieSessionList.get(i).get("movieTitle"));
            MovieVO movieVO = mSvc.getOneMovieByMovieName(movieTitle);
            String movie_no = movieVO.getMovie_no();
            String cinemaName = (String) movieSessionList.get(i).get("cinemaName");
            CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinemaName);
            String cinema_no =cinemaVO.getCinema_no();
            String sessionDate = (String) movieSessionList.get(i).get("sessionDate");
            sessionDate = sessionDate.replaceAll("/", "-");
            int session_month = Integer.valueOf(sessionDate.split("-")[0]).intValue();
            String time = (String) movieSessionList.get(i).get("time");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            year = month > session_month ? year + 1 : year; // next year or not
            String sessionTime = year + "-" + sessionDate + " " + time + ":00";
            System.out.println(this.getClass().getSimpleName() + " : MovieSession " + i + " "
                    + movie_no + " " + movieTitle + " " + cinema_no + " " + cinemaName + " "
                    + sessionDate + " "+ time + " , " + sessionTime);
            //用SessionTime MovieNo CinemaNo 去  table MOVIE_SESSION 找 有無資料
            List<SessionVO> session_list = sSvc.getAllBySessionTimeMovieNoCinemaNo(sessionTime, movie_no, cinema_no);
            if(session_list.size() > 0) {
                //repeating session, do not import DB
                System.out.println("repeating session :");
                HashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("movie_no", movie_no);
                map.put("movieTitle", movieTitle);
                map.put("cinema_no", cinema_no);
                map.put("cinemaName", cinemaName);
                map.put("sessionDate", sessionDate);
                map.put("time", time);
                repeat_sessions_list.add(map);
            } else {
                //找出 CINEMA_NO 的 THEATER_NO 然後移除  有衝突的THEATER_NO (在 MOVIE_SESSION 找 session_time 在 兩個時間內 和 是 在某CINEMA_NO的THEATER_NO 的 不重複 THEATER_NO)
                Date utilDateSession = null;
                try {
                    utilDateSession = dateFormat.parse(sessionTime); //get starting time of target session
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                String lengthMin = (String) movieSessionList.get(i).get("lengthMin");
                if(utilDateSession == null) {
                    System.out.println(this.getClass().getSimpleName() + " utilDateSession null : "
                                       + movie_no + " " + movieTitle + " " + cinema_no + " " + cinemaName + " "
                                       + sessionDate + " "+ time + " , " + sessionTime);
                    continue;
                }
                cal.setTime(utilDateSession);
                cal.add(Calendar.MINUTE, Integer.valueOf(lengthMin).intValue()); //add length of movie
                Date utilDateSec = cal.getTime(); //get ending time of target session
                String sessionTimeSec = dateFormat.format(utilDateSec);
                List<TheaterVO> tList = tSvc.getAllofNoConflictingTheater(cinema_no, sessionTime, sessionTimeSec);
                System.out.println("tSvc.getAllofNoConflictingTheater : tList.size() " + tList.size() + ", " + cinema_no + ", " + sessionTime + ", " + sessionTimeSec);
                if(tList.size() == 0) {
                    System.out.println(this.getClass().getSimpleName() + " no idle theater : "
                            + movie_no + " " + movieTitle + " " + cinema_no + " " + cinemaName + " "
                            + sessionDate + " "+ time + " , " + sessionTime);
                    continue;
                }

                List<String> idle_theater_list = new ArrayList<String>();
                for(TheaterVO tVO : tList) {
                    SessionVO sessionVO = sSvc.getOneSessionByTheaterNoBeforeSessionTime(tVO.getTheater_no(), sessionTime);
                    if(sessionVO == null) {
                        idle_theater_list.add(tVO.getTheater_no());
                        //System.out.println("sessionVO = null, idle_theater_list.add() :" + tVO.getTheater_no());
                    } else {
                        //判斷 THEATER_NO 的 新增場次時間 的前一場的 (場次時間 加 片長) 是否大於 新增場次時間
                        movieVO = mSvc.getOneMovie(sessionVO.getMovie_no());
                        int length = movieVO.getLength().intValue();
                        Timestamp previous_session_time = sessionVO.getSession_time();
                        Date datePrevious = new Date(previous_session_time.getTime());
                        Calendar calPrevious = Calendar.getInstance();
                        calPrevious.setTime(datePrevious);
                        calPrevious.add(Calendar.MINUTE, length);
                        Date datePreviousEnd = calPrevious.getTime();
                        //judge ending time of previous session is earlier than starting time of target session, or later than ending time of target session
                        if(datePreviousEnd.compareTo(utilDateSession) < 0 || datePreviousEnd.compareTo(utilDateSec) > 0) {
                            idle_theater_list.add(tVO.getTheater_no());
                            //System.out.println("idle_theater_list.add() : " + tVO.getTheater_no());
                        } else {
                            System.out.println(this.getClass().getSimpleName() + " previous session conflict : "
                                    + sessionVO.getTheater_no() + " " + dateFormat.format(datePrevious) + " - " + dateFormat.format(datePreviousEnd) + ", "
                                    + movie_no + " " + movieTitle + " " + cinema_no + " " + cinemaName + " "
                                    + sessionDate + " "+ time + " , " + sessionTime);
                        }
                    }
                }

                //找出筆數最少的TheaterNo, 新增場次時間
                List<HashMap> countList = sSvc.getAllCountInTheaterNoListGroupByTheaterNo(idle_theater_list);
                //System.out.println("countList.size() : " + countList.size());
                String theater_no = null;
                if(countList.size() > 0) {
                    //System.out.println("countList.size() > 0 : " + countList.size());
                    // for(HashMap<String, String> item : countList) {
                        // System.out.println("Count " + item.get("theater_no") + " : " + item.get("cnt"));
                    // }
                    theater_no = (String)countList.get(0).get("theater_no");

                } else {
                    //empty data : theater_no - idle_theater_list.get(0)
                    theater_no = idle_theater_list.get(0);
                    System.out.println("empty data : theater_no - idle_theater_list.get(0) " + theater_no);
                }

                TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
                String session_no = null;
                synchronized (sSvc) {
                    session_no = sSvc.addSession(theater_no, movie_no, new Timestamp(utilDateSession.getTime()), theaterVO.getSeat_model());
                }
                list.add(session_no);
                System.out.println("insert " + session_no + " successfully !");
            }
        }

        outputRepeatSessions();
        return list;
    }

    public void outputRepeatSessions() {
        StringBuilder sb = getDataSb(repeat_sessions_list);
        outputFile("repeat_sessions", sb.toString());
    }

    public void exportSer(List<String> list) {
        SerUtil su = new SerUtil();
        SessionVOSer sSer = new SessionVOSer();
        sSer.setServletContextRealPath(servletContextRealPath);
        su.setStrategy(sSer);
        for(String session_no : list) {
            Object vo = su.findVOByNo(session_no);
            if(vo != null) {
                try {
                    su.export(vo);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

//    public String addCinema(HashMap<String, String> map) {
//        CinemaService cSvc = new CinemaService();
//        String cinema_name = map.get("cinemaName");;
//        String cinema_engname = "";
//        String cinema_address = "";
//        String cinema_tel = "";
//        String introduction = "";
//        String traffic = "" ;
//        String photo_title = "";
//        String photo_path = "";
//        String photo_small = "";
//        Integer active = 1;
//        Integer state = 1;
//        String cinema_no = cSvc.addCinema(cinema_name, cinema_engname, cinema_address, cinema_tel, introduction, traffic, photo_title, photo_path, photo_small, active, state);
//        return cinema_no;
//    }

    public StringBuilder getDataSb(List<HashMap> sessions) {
        StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> r : sessions) {
            for(Map.Entry<String, String> e : r.entrySet()) {
                String output = String.format("%s\t", e.getValue());
                sb.append(output);
            }
            sb.append("\n");
        }
        return sb;
    }

    public void outputFile(String output, String str) {
        //File out = new File(output);
        //File out = new File(output + page + ".txt");

        //create dir
        //File targetDir = new File(dir);
        File targetDir = new File(servletContextRealPath + dir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        //target file
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String strDate = dateFormat.format(date);
        File out = new File(targetDir, output + "_" + strDate + ".txt");

        FileWriter fw;
        try {
            fw = new FileWriter(out);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
