package com.crawler.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        for(int i = 0; i < movieSessionList.size(); i++) {
            String movieTitle = (String) movieSessionList.get(i).get("movieTitle");
            System.out.println(this.getClass().getSimpleName() + " importDB movieTitle :" + movieSessionList.get(i).get("movieTitle"));
            MovieService mSvc = new MovieService();
            MovieVO movieVO = mSvc.getOneMovieByMovieName(movieTitle);
            String movie_no = movieVO.getMovie_no();

            String cinemaName = (String) movieSessionList.get(i).get("cinemaName");
            CinemaService cSvc = new CinemaService();
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
            SessionService sessionSvc = new SessionService();
            List<SessionVO> session_list = sessionSvc.getAllBySessionTimeMovieNoCinemaNo(sessionTime, movie_no, cinema_no);
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
                TheaterService tSvc = new TheaterService();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date utilDate = null;
                try {
                    utilDate = dateFormat.parse(sessionTime);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                String lengthMin = (String) movieSessionList.get(i).get("lengthMin");
                if(utilDate == null) {
                    System.out.println(this.getClass().getSimpleName() + " utilDate null : "
                                       + movie_no + " " + movieTitle + " " + cinema_no + " " + cinemaName + " "
                                       + sessionDate + " "+ time + " , " + sessionTime);
                    continue;
                }
                cal.setTime(utilDate);
                cal.add(Calendar.MINUTE, Integer.valueOf(lengthMin).intValue());
                Date utilDateSec = cal.getTime();
                String sessionTimeSec = dateFormat.format(utilDateSec);
                List<TheaterVO> tList = tSvc.getAllofNoConflictingTheater(cinema_no, sessionTime, sessionTimeSec);
            }
        }
        return list;
    }

    public void outputRepeatSessions() {
        StringBuilder sb = getDataSb(repeat_sessions_list);
        outputFile("repeat_sessions", sb.toString());
    }

    public void exportSer(List<String> list) {
        SerUtil su = new SerUtil();
        CinemaVOSer cSer = new CinemaVOSer();
        cSer.setServletContextRealPath(servletContextRealPath);
        su.setStrategy(cSer);
        for(String cinema_no : list) {
            Object vo = su.findVOByNo(cinema_no);
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

    public String addCinema(HashMap<String, String> map) {
        CinemaService cSvc = new CinemaService();
        String cinema_name = map.get("cinemaName");;
        String cinema_engname = "";
        String cinema_address = "";
        String cinema_tel = "";
        String introduction = "";
        String traffic = "" ;
        String photo_title = "";
        String photo_path = "";
        String photo_small = "";
        Integer active = 1;
        Integer state = 1;
        String cinema_no = cSvc.addCinema(cinema_name, cinema_engname, cinema_address, cinema_tel, introduction, traffic, photo_title, photo_path, photo_small, active, state);
        return cinema_no;
    }

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
