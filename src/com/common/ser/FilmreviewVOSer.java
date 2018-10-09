package com.common.ser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.filmreview.model.FilmreviewService;
import com.filmreview.model.FilmreviewVO;

public class FilmreviewVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/MovieVO";
    private String dir = "resources/ser/FilmreviewVO"; //for Java EE Servlet
    FilmreviewService frSvc;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public FilmreviewVOSer() {
    	frSvc = new FilmreviewService();
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
    public Object findVOByNo(String fr_no) {
        FilmreviewVO filmreviewvo = frSvc.findByPrimaryKey(fr_no);
        return filmreviewvo;
    }

    @Override
    public Object findVOByName(String movie_name) {
		return movie_name;
        
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void export(Object vo) throws IOException {
        FilmreviewVO filmreviewVO = (FilmreviewVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = new File(servletContextRealPath, dir);
        if(!fsaveDir.exists()) {
            fsaveDir.mkdirs();
        }
        File file = new File(fsaveDir, filmreviewVO.getFr_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(filmreviewVO);
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
