package com.crawler.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.common.ser.MovieVOSer;
import com.common.ser.SerUtil;
import com.common.util.ImageUtil;
import com.movie.model.*;

public class MovieHandler {
    private List<HashMap> movieInfoList = new ArrayList<HashMap>();
    //private String dir = "WebContent/resources/crawler/movieinfo"; //workable ?!
    private String dir = "resources/crawler/movieinfo"; //for Java EE Environment
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public MovieHandler() {
        // TODO Auto-generated constructor stub
    }

    public MovieHandler(List<HashMap> movieInfoList) {
        this.movieInfoList = movieInfoList;
    }

    public List<String> importDB() {
        System.out.println("importDB");
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < movieInfoList.size(); i++) {
            String movie_name = (String) movieInfoList.get(i).get("movieTitle");
            System.out.println("importDB movieTitle :" + movieInfoList.get(i).get("movieTitle"));
            MovieService mSvc = new MovieService();
            MovieVO movieVO = mSvc.getOneMovieByMovieName(movie_name);
            if(movieVO == null) {
                String movie_no = addMovieReturnMovieNo(movieInfoList.get(i));
                System.out.println("importDB movie_no: " + movie_no);
                list.add(movie_no);
            }
        }
        return list;
    }
    
    public void exportSer(List<String> list) {
        SerUtil su = new SerUtil();
        MovieVOSer mSer = new MovieVOSer();
        mSer.setServletContextRealPath(servletContextRealPath);
        su.setStrategy(mSer);
        for(String movie_no : list) {
            Object vo = su.findVOByNo(movie_no);
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

    public String addMovieReturnMovieNo(HashMap<String, String> map) {
        File fsaveDir = new File(servletContextRealPath, dir + "/" + map.get("movieTitle"));
        if(!fsaveDir.exists()) {
            fsaveDir.mkdirs();
        }
        //String targetPath = fsaveDir + "/" + map.get("movieTitle") + "/" + map.get("imgFileName");
        File f = new File(fsaveDir, map.get("imgFileName"));
        String targetPath = f.toString();
        System.out.println("targetPath : " + targetPath);
        byte[] movie_pic = null;
        try {
            movie_pic = ImageUtil.getPictureByteArray(targetPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String movie_type = "";
        String movie_name = map.get("movieTitle");
        String eng_name = map.get("movieTitleEn");
        java.sql.Date relased = java.sql.Date.valueOf(map.get("releaseDate").replaceAll("/", "-"));
        String distributed = "";
        Integer length = Integer.valueOf(map.get("lengthMin"));
        String language = "";
        String madein = "";
        Double imdb = Double.valueOf(map.get("imdb"));;
        String tomato = map.get("tomato");
        String rating = map.get("grade");
        String trailer_url = map.get("videoUrl");
        String brief_intro = map.get("intro");
        Integer active = 1;
        String director = map.get("director");
        String starring = map.get("staff");

        MovieService mSvc = new MovieService();
        String movie_no = mSvc.addMovieReturnMovieNo(movie_type, movie_name, eng_name, movie_pic, relased, distributed, length,
                language, madein, imdb, tomato, rating, trailer_url, brief_intro, active, director, starring);

        return movie_no;
    }

}
