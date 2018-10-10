package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.theater.model.*;


public class TheaterVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/TheaterVO";
  private String dir = "resources/ser/TheaterVO"; //for Java EE Servlet
  private TheaterService tSvc;
  private TheaterDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public TheaterVOSer() {
      tSvc = new TheaterService();
      dao = new TheaterDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      TheaterVO theaterVO = readTheaterVOSer(target);
      if("add".equals(action)) {
          TheaterVO tmpVO = tSvc.getOneTheater(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String theater_no = dao.insert(theaterVO);
              System.out.println("TheaterVOSer insert " + theater_no);
          }
      } else if("update".equals(action)) {
          dao.update(theaterVO);
          System.out.println("TheaterVOSer update " + theaterVO.getTheater_no());
      } else {
          System.out.println("TheaterVOSer no corresponding action");
      }
  }

  public TheaterVO readTheaterVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      TheaterVO theaterVO = (TheaterVO) ois.readObject();
      ois.close();
      fis.close();
      return theaterVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("TheaterVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String theater_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "add";
          importOne(action, theater_no);
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
  public Object findVOByNo(String theater_no) {
      TheaterVO theaterVO = tSvc.getOneTheater(theater_no);
      return theaterVO;
  }

  @Override
  public Object findVOByName(String theater_name) {
	  System.out.println("TheaterVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<TheaterVO> list = tSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(TheaterVO theaterVO : list) {
          listObj.add((Object)theaterVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      TheaterVO theaterVO = (TheaterVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, theaterVO.getTheater_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(theaterVO);
      System.out.println("TheaterVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("TheaterVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
//      System.out.println("TheaterVOSer export(List<Object> list) size " + list.size());
//      export(list.get(0));
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("TheaterVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String theater_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "update";
          importOne(action, theater_no);
      }
  }

}
