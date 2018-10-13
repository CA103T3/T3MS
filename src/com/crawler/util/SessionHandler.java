package com.crawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.session.model.*;
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
        for(int i = 0; i < movieSessionList.size(); i++) {
            String movie_name = (String) movieSessionList.get(i).get("movieTitle");
            System.out.println(this.getClass().getSimpleName() + " importDB movieTitle :" + movieSessionList.get(i).get("movieTitle"));
            MovieService mSvc = new MovieService();
            MovieVO movieVO = mSvc.getOneMovieByMovieName(movie_name);
            String movie_no = movieVO.getMovie_no();

            String cinema_name = (String) movieSessionList.get(i).get("cinemaName");
            CinemaService cSvc = new CinemaService();
            CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
            String cinema_no =cinemaVO.getCinema_no();

            String sessionDate = (String) movieSessionList.get(i).get("sessionDate");
            sessionDate = sessionDate.replaceAll("/", "-");
            int session_month = Integer.valueOf(sessionDate.split("-")[0]).intValue();
            String time = (String) movieSessionList.get(i).get("time");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            year = month > session_month ? year + 1 : year; // next year or not
            String sessionTime = year + "-" + sessionDate + " " + time + ":00";
            SessionService sessionSvc = new SessionService();
            List<SessionVO> session_list = sessionSvc.getAllBySessionTimeMovieNoCinemaNo(sessionTime, movie_no, cinema_no);
            if(session_list.size() > 0) {
//                String cinema_name = (String) movieSessionList.get(i).get("cinemaName");
//                CinemaService cSvc = new CinemaService();
//                CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
            } else {
                //add
            }
        }
        return list;
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
    
}
