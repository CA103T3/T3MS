package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.nav_backstage.model.*;


public class NavVOSer implements SerStrategy_interface {


  private String dir = "resources/ser/NavVO"; //for Java EE Servlet
  private NavService tSvc;
  private NavDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public NavVOSer() {
      tSvc = new NavService();
      dao = new NavDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      NavVO navVO = readNavVOSer(target);
      if("add".equals(action)) {
          NavVO tmpVO = tSvc.getVO(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String itemlist_no = dao.insert(navVO);
              System.out.println("NavVOSer insert " + itemlist_no);
          }
      } else if("update".equals(action)) {
          dao.update(navVO);
          System.out.println("NavVOSer update " + navVO.getListitem_no());
      } else {
          System.out.println("NavVOSer no corresponding action");
      }
  }

  public NavVO readNavVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      NavVO navVO = (NavVO) ois.readObject();
      ois.close();
      fis.close();
      return navVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("NavVOSer importAll");
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
  public Object findVOByNo(String listitem_no) {
      NavVO navVO = tSvc.getVO(listitem_no);
      return navVO;
  }

  @Override
  public Object findVOByName(String listitem_name) {
	  System.out.println("NavVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<NavVO> list = tSvc.getall();
      List<Object> listObj = new ArrayList<Object>();
      for(NavVO navVO : list) {
          listObj.add((Object)navVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      NavVO navVO = (NavVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, navVO.getListitem_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(navVO);
      System.out.println("NavVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("NavVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
//      System.out.println("NavVOSer export(List<Object> list) size " + list.size());
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("NavVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String listitem_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "update";
          importOne(action, listitem_no);
      }
  }

}
