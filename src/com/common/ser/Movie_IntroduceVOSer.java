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

import com.movie_introduce.model.Movie_IntroduceDAO;
import com.movie_introduce.model.Movie_IntroduceDAO_interface;
import com.movie_introduce.model.Movie_IntroduceService;
import com.movie_introduce.model.Movie_IntroduceVO;



public class Movie_IntroduceVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/Movie_IntroduceVO";
    private String dir = "resources/ser/Movie_IntroduceVO"; //for Java EE Servlet
    private Movie_IntroduceService miSvc;
    private Movie_IntroduceDAO_interface dao;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public Movie_IntroduceVOSer() {
        miSvc = new Movie_IntroduceService();
        dao = new Movie_IntroduceDAO();
    }

    @Override
    public void importOne(String action, String no) throws Exception {
        File fsaveDir = checkSaveDir();
        File target = new File(fsaveDir, no + ".ser");
        Movie_IntroduceVO Movie_IntroduceVO = readMovie_IntroduceVOSer(target);
        if("add".equals(action)) {
        	Movie_IntroduceVO tmpVO = miSvc.getOneMovie_Itde(no);
            if(tmpVO != null) {
                System.out.println(no + " already existed !");
            } else {
                String introd_no = dao.insertReturnMovie_IntroduceNo(Movie_IntroduceVO);
                System.out.println("Movie_IntroduceVOSer insert " + introd_no);
            }
        } else if("update".equals(action)) {
            dao.update(Movie_IntroduceVO);
            System.out.println("Movie_IntroduceVOSer update " + Movie_IntroduceVO.getIntrod_no());
        } else {
            System.out.println("Movie_IntroduceVOSer no corresponding action");
        }
    }

    public Movie_IntroduceVO readMovie_IntroduceVOSer(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Movie_IntroduceVO Movie_IntroduceVO = (Movie_IntroduceVO) ois.readObject();
        ois.close();
        fis.close();
        return Movie_IntroduceVO;
    }

    @Override
    public void importAll() throws Exception {
        System.out.println("Movie_IntroduceVOSer importAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
//            FileInputStream fis = new FileInputStream(f);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            Movie_IntroduceVO Movie_IntroduceVO = (Movie_IntroduceVO) ois.readObject();
//            ois.close();
//            fis.close();

//            Movie_IntroduceVO Movie_IntroduceVO = readMovie_IntroduceVOSer(f);
            //Movie_IntroduceVO.getFilmreview_name();
//            System.out.println("Movie_IntroduceVO.getIntrod_no() " + Movie_IntroduceVO.getIntrod_no());
//            System.out.println("Movie_IntroduceVO.getFilmreview_name() " + Movie_IntroduceVO.getFilmreview_name());
            //String introd_no = Movie_IntroduceVO.getIntrod_no();
            String filename = f.getName();
            String introd_no = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("introd_no : " + introd_no);
            String action = "add";
            importOne(action, introd_no);
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
    public Object findVOByNo(String introd_no) {
    	Movie_IntroduceVO movie_introduceVO = miSvc.getOneMovie_Itde(introd_no);
        return movie_introduceVO ;
    }

    @Override
    public Object findVOByName(String introd_no) {
      
        return null ;
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        List<Movie_IntroduceVO> list = miSvc.getAll();
        List<Object> listObj = new ArrayList<Object>();
        for(Movie_IntroduceVO movie_introduceVO : list) {
            listObj.add((Object)movie_introduceVO);
        }
        return listObj;
    }

    @Override
    public void export(Object vo) throws IOException {
    	Movie_IntroduceVO movie_introduceVO = (Movie_IntroduceVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = checkSaveDir();
        File file = new File(fsaveDir, movie_introduceVO.getIntrod_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(movie_introduceVO);
        System.out.println("Movie_IntroduceVOSer export : " + file.toString());
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("Movie_IntroduceVOSer export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
        System.out.println("list:"+list.size());
    }

    @Override
    public void importUpdateAll() throws Exception {
        System.out.println("Movie_IntroduceVOSer importUpdateAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
            String filename = f.getName();
            String introd_no = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("introd_no : " + introd_no);
            String action = "update";
            importOne(action, introd_no);
        }
    }

}
