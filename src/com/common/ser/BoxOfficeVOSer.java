package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.boxoffice.model.*;


public class BoxOfficeVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/BoxOfficeVO";
  private String dir = "resources/ser/BoxOfficeVO"; //for Java EE Servlet
  private BoxOfficeService bSvc;
  private BoxOfficeDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public BoxOfficeVOSer() {
      bSvc = new BoxOfficeService();
      dao = new BoxOfficeDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      BoxOfficeVO boVO = readBoxOfficeVOSer(target);
      if("add".equals(action)) {
          BoxOfficeVO tmpVO = bSvc.getOneBoxOffice(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String ranking_no = dao.insert(boVO);
              System.out.println("BoxOfficeVOSer insert " + ranking_no);
          }
      } else if("update".equals(action)) {
          dao.update(boVO);
          System.out.println("BoxOfficeVOSer update " + boVO.getRanking_no());
      } else {
          System.out.println("BoxOfficeVOSer no corresponding action");
      }
  }

  public BoxOfficeVO readBoxOfficeVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      BoxOfficeVO boVO = (BoxOfficeVO) ois.readObject();
      ois.close();
      fis.close();
      return boVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("BoxOfficeVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String ranking_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("ranking_no : " + ranking_no);
          String action = "add";
          importOne(action, ranking_no);
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
  public Object findVOByNo(String ranking_no) {
      BoxOfficeVO boVO = bSvc.getOneBoxOffice(ranking_no);
      return boVO;
  }

  @Override
  public Object findVOByName(String name) {
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<BoxOfficeVO> list = bSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(BoxOfficeVO boVO : list) {
          listObj.add((Object)boVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      BoxOfficeVO boVO = (BoxOfficeVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, boVO.getRanking_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(boVO);
      System.out.println("BoxOfficeVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("BoxOfficeVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("BoxOfficeVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String ranking_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("ranking_no : " + ranking_no);
          String action = "update";
          importOne(action, ranking_no);
      }
  }

}
