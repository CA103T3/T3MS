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

import com.filmreview_msg.model.Filmreview_MsgDAO;
import com.filmreview_msg.model.Filmreview_MsgDAO_interface;
import com.filmreview_msg.model.Filmreview_MsgService;
import com.filmreview_msg.model.Filmreview_MsgVO;
import com.session.model.SessionVO;

public class Filmreview_MsgVOSer implements SerStrategy_interface {
//    private String dir = "WebContent/resources/ser/Filmreview_MsgVO";
    private String dir = "resources/ser/Filmreview_MsgVO"; //for Java EE Servlet
    private Filmreview_MsgService frmSvc;
    private Filmreview_MsgDAO_interface dao;
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public Filmreview_MsgVOSer() {
        frmSvc = new Filmreview_MsgService();
        dao = new Filmreview_MsgDAO();
    }

    @Override
    public void importOne(String action, String no) throws Exception {
        File fsaveDir = checkSaveDir();
        File target = new File(fsaveDir, no + ".ser");
        Filmreview_MsgVO Filmreview_MsgVO = readFilmreview_MsgVOSer(target);
        if("add".equals(action)) {
            Filmreview_MsgVO tmpVO = frmSvc.getVO(no);
            if(tmpVO != null) {
                System.out.println(no + " already existed !");
            } else {
                String frm_no = dao.insertReturnFilmreviewNo(Filmreview_MsgVO);
                System.out.println("Filmreview_MsgVOSer insert " + frm_no);
            }
        }
         else {
            System.out.println("Filmreview_MsgVOSer no corresponding action");
        }
    }

    public Filmreview_MsgVO readFilmreview_MsgVOSer(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Filmreview_MsgVO Filmreview_MsgVO = (Filmreview_MsgVO) ois.readObject();
        ois.close();
        fis.close();
        return Filmreview_MsgVO;
    }

    @Override
    public void importAll() throws Exception {
        System.out.println("Filmreview_MsgVOSer importAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
//            FileInputStream fis = new FileInputStream(f);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            Filmreview_MsgVO Filmreview_MsgVO = (Filmreview_MsgVO) ois.readObject();
//            ois.close();
//            fis.close();

//            Filmreview_MsgVO Filmreview_MsgVO = readFilmreview_MsgVOSer(f);
            //Filmreview_MsgVO.getFilmreview_Msg_name();
//            System.out.println("Filmreview_MsgVO.getFilmreview_Msg_no() " + Filmreview_MsgVO.getFilmreview_Msg_no());
//            System.out.println("Filmreview_MsgVO.getFilmreview_Msg_name() " + Filmreview_MsgVO.getFilmreview_Msg_name());
            //String frm_no = Filmreview_MsgVO.getfrm_no();
            String filename = f.getName();
            String frm_no = filename.substring(0, filename.lastIndexOf("."));
            //System.out.println("frm_no : " + frm_no);
            String action = "add";
            importOne(action, frm_no);
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
    public Object findVOByNo(String frm_no) {
    	Filmreview_MsgVO Filmreview_MsgVO = frmSvc.getVO(frm_no);
        return Filmreview_MsgVO ;
    }

    @Override
    public Object findVOByName(String frm_no) {
      
        return null ;
    }

    @Override
    public List<Object> getAllVO() {
        // TODO Auto-generated method stub
        List<Filmreview_MsgVO> list = frmSvc.getAll();
        List<Object> listObj = new ArrayList<Object>();
        for(Filmreview_MsgVO Filmreview_MsgVO : list) {
            listObj.add((Object)Filmreview_MsgVO);
        }
        return listObj;
    }

    @Override
    public void export(Object vo) throws IOException {
        Filmreview_MsgVO Filmreview_MsgVO = (Filmreview_MsgVO) vo;
//        String realPath = getServletContext().getRealPath(dir);
        File fsaveDir = checkSaveDir();
        File file = new File(fsaveDir, Filmreview_MsgVO.getFrm_no() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Filmreview_MsgVO);
        System.out.println("Filmreview_MsgVOSer export : " + file.toString());
        oos.close();
        fos.close();
    }

    @Override
    public void export(List<Object> list) throws IOException {
        System.out.println("Filmreview_MsgVOSer export(List<Object> list)");
        for(Object vo : list) {
            export(vo);
        }
        System.out.println("list:"+list.size());
    }

    @Override
    public void importUpdateAll() throws Exception {
        System.out.println("Filmreview_MsgVOSer importUpdateAll");
        File fsaveDir = checkSaveDir();
        for(File f : fsaveDir.listFiles()){
            System.out.println(f.getName());
            String filename = f.getName();
            String frm_no = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("frmm_no : " + frm_no);
            String action = "update";
            importOne(action, frm_no);
        }
    }

}
