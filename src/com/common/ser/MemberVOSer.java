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

import com.member.model.*;

public class MemberVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/FilmreviewVO";
    private String dir = "resources/ser/MemberVO"; //for Java EE Servlet
    private MemService MemSvc;
    private MemDAO_interface dao;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public MemberVOSer() {
        MemSvc = new MemService();
        dao = new MemJNDIDAO();
    }

    @Override
    public void importOne(String action, String no) throws Exception {
        File fsaveDir = checkSaveDir();
        File target = new File(fsaveDir, no + ".ser");
        MemVO memVO = readMemberVOSer(target);
        if("add".equals(action)) {
            MemVO tmpVO = MemSvc.getMemVOByNO(no);
            if(tmpVO != null) {
                System.out.println(no + " already existed !");
            } else {
                String memno = dao.InsertReturnNO(memVO);
                System.out.println("MemberVOSer insert " + memno);
            }
        } else if("update".equals(action)) {
            dao.update(memVO);
            System.out.println("MemberVOSer update " + memVO.getmemno());
        } else {
            System.out.println("MemberVOSer no corresponding action");
        }
    }

    public MemVO readMemberVOSer(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        MemVO memVO = (MemVO) ois.readObject();
        ois.close();
        fis.close();
        return memVO;
    }

    @Override
    public void importAll() throws Exception {
        System.out.println("MemberVOSer importAll");
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
            String memno = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("Fr_no : " + Fr_no);
            String action = "add";
            importOne(action, memno);
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
    public Object findVOByNo(String memno) {
       
        return memno ;
    }

    @Override
    public Object findVOByName(String name) {
      
        return name ;
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        List<MemVO> list = MemSvc.getall();
        List<Object> listObj = new ArrayList<Object>();
        for(MemVO memVO : list) {
            listObj.add((Object)memVO);
        }
        return listObj;
    }

    @Override
    public void export(Object vo) throws IOException {
        MemVO memVO = (MemVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = checkSaveDir();
        File file = new File(fsaveDir, memVO.getMemno() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(memVO);
        System.out.println("MemberVOSer export : " + file.toString());
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("MemberVOSer export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
        System.out.println("list:"+list.size());
    }

    @Override
    public void importUpdateAll() throws Exception {
        System.out.println("MemberVOSer importUpdateAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
            String filename = f.getName();
            String memno = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("memno : " + memno);
            String action = "update";
            importOne(action, memno);
        }
    }

}
