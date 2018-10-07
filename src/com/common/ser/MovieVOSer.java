package com.common.ser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.movie.model.MovieService;
import com.movie.model.MovieVO;

public class MovieVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/MovieVO";
    private String dir = "resources/ser/MovieVO"; //for Java EE Servlet
    MovieService mSvc;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public MovieVOSer() {
        mSvc = new MovieService();
    }

    @Override
    public void importOne(String filename) {
        // TODO Auto-generated method stub

    }

    @Override
    public void importAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public Object findVOByNo(String movie_no) {
        MovieVO movievo = mSvc.getOneMovie(movie_no);
        return movievo;
    }

    @Override
    public Object findVOByName(String movie_name) {
        MovieVO movievo = mSvc.getOneMovieByMovieName(movie_name);
        return movievo;
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void export(Object vo) throws IOException {
        MovieVO movieVO = (MovieVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = new File(servletContextRealPath, dir);
        if(!fsaveDir.exists()) {
            fsaveDir.mkdirs();
        }
        File file = new File(fsaveDir, movieVO.getMovie_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(movieVO);
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
    }

}
