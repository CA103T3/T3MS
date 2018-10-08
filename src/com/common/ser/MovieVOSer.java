package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.movie.model.MovieDAO;
import com.movie.model.MovieDAO_interface;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;

public class MovieVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/MovieVO";
    private String dir = "resources/ser/MovieVO"; //for Java EE Servlet
    private MovieService mSvc;
    private MovieDAO_interface dao;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public MovieVOSer() {
        mSvc = new MovieService();
        dao = new MovieDAO();
    }

    @Override
    public void importOne(String action, String no) throws Exception {
        File fsaveDir = checkSaveDir();
        File target = new File(fsaveDir, no + ".ser");
        MovieVO movieVO = readMovieVOSer(target);
        if("add".equals(action)) {
            MovieVO tmpVO = mSvc.getOneMovie(no);
            if(tmpVO != null) {
                System.out.println(no + " already existed !");
            } else {
                String movie_no = dao.insertReturnMovieNo(movieVO);
                System.out.println("MovieVOSer insert " + movie_no);
            }
        } else if("update".equals(action)) {
            dao.update(movieVO);
            System.out.println("MovieVOSer update " + movieVO.getMovie_no());
        } else {
            System.out.println("MovieVOSer no corresponding action");
        }
    }

    public MovieVO readMovieVOSer(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        MovieVO movieVO = (MovieVO) ois.readObject();
        ois.close();
        fis.close();
        return movieVO;
    }

    @Override
    public void importAll() throws Exception {
        System.out.println("MovieVOSer importAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
//            FileInputStream fis = new FileInputStream(f);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            MovieVO movieVO = (MovieVO) ois.readObject();
//            ois.close();
//            fis.close();

//            MovieVO movieVO = readMovieVOSer(f);
            //movieVO.getMovie_name();
//            System.out.println("movieVO.getMovie_no() " + movieVO.getMovie_no());
//            System.out.println("movieVO.getMovie_name() " + movieVO.getMovie_name());
            //String movie_no = movieVO.getMovie_no();
            String filename = f.getName();
            String movie_no = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("movie_no : " + movie_no);
            String action = "add";
            importOne(action, movie_no);
        }
    }

    public File checkSaveDir() {
        File fsaveDir = new File(servletContextRealPath, dir);
        if(!fsaveDir.exists()) {
            fsaveDir.mkdirs();
        }
        return fsaveDir;
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
        List<MovieVO> list = mSvc.getAll();
        List<Object> listObj = new ArrayList<Object>();
        for(MovieVO movieVO : list) {
            listObj.add((Object)movieVO);
        }
        return listObj;
    }

    @Override
    public void export(Object vo) throws IOException {
        MovieVO movieVO = (MovieVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = checkSaveDir();
        File file = new File(fsaveDir, movieVO.getMovie_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(movieVO);
        System.out.println("MovieVOSer export : " + file.toString());
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("MovieVOSer export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
    }

    @Override
    public void importUpdateAll() throws Exception {
        System.out.println("MovieVOSer importUpdateAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
            String filename = f.getName();
            String movie_no = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("movie_no : " + movie_no);
            String action = "update";
            importOne(action, movie_no);
        }
    }

}
