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

import com.filmreview.model.FilmreviewDAO;
import com.filmreview.model.FilmreviewDAO_interface;
import com.filmreview.model.FilmreviewService;
import com.filmreview.model.FilmreviewVO;

public class FilmreviewVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/FilmreviewVO";
    private String dir = "resources/ser/FilmreviewVO"; //for Java EE Servlet
    private FilmreviewService frSvc;
    private FilmreviewDAO_interface dao;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public FilmreviewVOSer() {
        frSvc = new FilmreviewService();
        dao = new FilmreviewDAO();
    }

    @Override
    public void importOne(String action, String no) throws Exception {
        File fsaveDir = checkSaveDir();
        File target = new File(fsaveDir, no + ".ser");
        FilmreviewVO FilmreviewVO = readFilmreviewVOSer(target);
        if("add".equals(action)) {
            FilmreviewVO tmpVO = frSvc.findByPrimaryKey(no);
            if(tmpVO != null) {
                System.out.println(no + " already existed !");
            } else {
                String fr_no = dao.insertReturnFilmreviewNo(FilmreviewVO);
                System.out.println("FilmreviewVOSer insert " + fr_no);
            }
        } else if("update".equals(action)) {
            dao.update(FilmreviewVO);
            System.out.println("FilmreviewVOSer update " + FilmreviewVO.getFr_no());
        } else {
            System.out.println("FilmreviewVOSer no corresponding action");
        }
    }

    public FilmreviewVO readFilmreviewVOSer(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        FilmreviewVO FilmreviewVO = (FilmreviewVO) ois.readObject();
        ois.close();
        fis.close();
        return FilmreviewVO;
    }

    @Override
    public void importAll() throws Exception {
        System.out.println("FilmreviewVOSer importAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
//            FileInputStream fis = new FileInputStream(f);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            FilmreviewVO FilmreviewVO = (FilmreviewVO) ois.readObject();
//            ois.close();
//            fis.close();

//            FilmreviewVO FilmreviewVO = readFilmreviewVOSer(f);
            //FilmreviewVO.getFilmreview_name();
//            System.out.println("FilmreviewVO.getFilmreview_no() " + FilmreviewVO.getFilmreview_no());
//            System.out.println("FilmreviewVO.getFilmreview_name() " + FilmreviewVO.getFilmreview_name());
            //String fr_no = FilmreviewVO.getFr_no();
            String filename = f.getName();
            String fr_no = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("Fr_no : " + Fr_no);
            String action = "add";
            importOne(action, fr_no);
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
    public Object findVOByNo(String fr_no) {
       
        return fr_no ;
    }

    @Override
    public Object findVOByName(String fr_name) {
      
        return fr_name ;
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        List<FilmreviewVO> list = frSvc.getAll();
        List<Object> listObj = new ArrayList<Object>();
        for(FilmreviewVO filmreviewVO : list) {
            listObj.add((Object)filmreviewVO);
        }
        return listObj;
    }

    @Override
    public void export(Object vo) throws IOException {
        FilmreviewVO filmreviewVO = (FilmreviewVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = checkSaveDir();
        File file = new File(fsaveDir, filmreviewVO.getFr_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(filmreviewVO);
        System.out.println("FilmreviewVOSer export : " + file.toString());
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("FilmreviewVOSer export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
        System.out.println("list:"+list.size());
    }

    @Override
    public void importUpdateAll() throws Exception {
        System.out.println("FilmreviewVOSer importUpdateAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
            String filename = f.getName();
            String fr_no = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("fr_no : " + fr_no);
            String action = "update";
            importOne(action, fr_no);
        }
    }

}
