package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.announcement.model.*;

public class AnnouncementVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/AnnouncementVO";
  private String dir = "resources/ser/AnnouncementVO"; //for Java EE Servlet
  private AnnouncementService aSvc;
  private AnnouncementDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public AnnouncementVOSer() {
      aSvc = new AnnouncementService();
      dao = new AnnouncementJNDIDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      AnnouncementVO annVO = readAnnouncementVOSer(target);
      if("add".equals(action)) {
          AnnouncementVO tmpVO = aSvc.getOneAnn(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String anc_no = dao.insertReturnAncNo(annVO);
              System.out.println("AnnouncementVOSer insert " + anc_no);
          }
      } else if("update".equals(action)) {
          dao.update(annVO);
          System.out.println("AnnouncementVOSer update " + annVO.getAnc_no());
      } else {
          System.out.println("AnnouncementVOSer no corresponding action");
      }
  }

  public AnnouncementVO readAnnouncementVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      AnnouncementVO annVO = (AnnouncementVO) ois.readObject();
      ois.close();
      fis.close();
      return annVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("AnnouncementVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String anc_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("anc_no : " + anc_no);
          String action = "add";
          importOne(action, anc_no);
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
  public Object findVOByNo(String anc_no) {
      AnnouncementVO annVO = aSvc.getOneAnn(anc_no);
      return annVO;
  }

  @Override
  public Object findVOByName(String name) {
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<AnnouncementVO> list = aSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(AnnouncementVO annVO : list) {
          listObj.add((Object)annVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      AnnouncementVO annVO = (AnnouncementVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, annVO.getAnc_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(annVO);
      System.out.println("AnnouncementVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("AnnouncementVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("AnnouncementVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String anc_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("anc_no : " + anc_no);
          String action = "update";
          importOne(action, anc_no);
      }
  }

}
