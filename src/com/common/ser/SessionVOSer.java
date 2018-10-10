package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.session.model.*;


public class SessionVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/SessionVO";
  private String dir = "resources/ser/SessionVO"; //for Java EE Servlet
  private SessionService sSvc;
  private SessionDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public SessionVOSer() {
      sSvc = new SessionService();
      dao = new SessionDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      SessionVO sessionVO = readSessionVOSer(target);
      if("add".equals(action)) {
          SessionVO tmpVO = sSvc.getOneSession(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String session_no = dao.insert(sessionVO);
              System.out.println("SessionVOSer insert " + session_no);
          }
      } else if("update".equals(action)) {
          dao.update(sessionVO);
          System.out.println("SessionVOSer update " + sessionVO.getSession_no());
      } else {
          System.out.println("SessionVOSer no corresponding action");
      }
  }

  public SessionVO readSessionVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      SessionVO sessionVO = (SessionVO) ois.readObject();
      ois.close();
      fis.close();
      return sessionVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("SessionVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String session_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("session_no : " + session_no);
          String action = "add";
          importOne(action, session_no);
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
  public Object findVOByNo(String session_no) {
      SessionVO sessionVO = sSvc.getOneSession(session_no);
      return sessionVO;
  }

  @Override
  public Object findVOByName(String session_name) {
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<SessionVO> list = sSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(SessionVO sessionVO : list) {
          listObj.add((Object)sessionVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      SessionVO sessionVO = (SessionVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, sessionVO.getSession_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(sessionVO);
      System.out.println("SessionVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("SessionVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("SessionVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String session_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("session_no : " + session_no);
          String action = "update";
          importOne(action, session_no);
      }
  }

}
