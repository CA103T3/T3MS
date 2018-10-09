package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.cinema.model.*;


public class CinemaVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/CinemaVO";
  private String dir = "resources/ser/CinemaVO"; //for Java EE Servlet
  private CinemaService cSvc;
  private CinemaDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public CinemaVOSer() {
      cSvc = new CinemaService();
      dao = new CinemaDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      CinemaVO cinemaVO = readCinemaVOSer(target);
      if("add".equals(action)) {
          CinemaVO tmpVO = cSvc.getOneCinema(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String cinema_no = dao.insert(cinemaVO);
              System.out.println("CinemaVOSer insert " + cinema_no);
          }
      } else if("update".equals(action)) {
          dao.update(cinemaVO);
          System.out.println("CinemaVOSer update " + cinemaVO.getCinema_no());
      } else {
          System.out.println("CinemaVOSer no corresponding action");
      }
  }

  public CinemaVO readCinemaVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      CinemaVO cinemaVO = (CinemaVO) ois.readObject();
      ois.close();
      fis.close();
      return cinemaVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("CinemaVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String cinema_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("cinema_no : " + cinema_no);
          String action = "add";
          importOne(action, cinema_no);
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
  public Object findVOByNo(String cinema_no) {
      CinemaVO cinemaVO = cSvc.getOneCinema(cinema_no);
      return cinemaVO;
  }

  @Override
  public Object findVOByName(String cinema_name) {
      CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
      return cinemaVO;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<CinemaVO> list = cSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(CinemaVO cinemaVO : list) {
          listObj.add((Object)cinemaVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      CinemaVO cinemaVO = (CinemaVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, cinemaVO.getCinema_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(cinemaVO);
      System.out.println("CinemaVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("CinemaVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("CinemaVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String cinema_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("cinema_no : " + cinema_no);
          String action = "update";
          importOne(action, cinema_no);
      }
  }

}
