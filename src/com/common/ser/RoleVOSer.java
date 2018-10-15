package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.role.model.*;


public class RoleVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/RoleVO";
  private String dir = "resources/ser/RoleVO"; //for Java EE Servlet
  private RoleService tSvc;
  private RoleDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public RoleVOSer() {
      tSvc = new RoleService();
      dao = new RoleDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      RoleVO roleVO = readRoleVOSer(target);
      if("add".equals(action)) {
          RoleVO tmpVO = tSvc.findbyno(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String br_no = dao.insert(roleVO);
              System.out.println("RoleVOSer insert " + br_no);
          }
      } else if("update".equals(action)) {
          dao.update(roleVO);
          System.out.println("RoleVOSer update " + roleVO.getBr_no());
      } else {
          System.out.println("RoleVOSer no corresponding action");
      }
  }

  public RoleVO readRoleVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      RoleVO roleVO = (RoleVO) ois.readObject();
      ois.close();
      fis.close();
      return roleVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("RoleVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String br_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("br_no : " + br_no);
          String action = "add";
          importOne(action, br_no);
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
  public Object findVOByNo(String br_no) {
      RoleVO roleVO = tSvc.findbyno(br_no);
      return roleVO;
  }

  @Override
  public Object findVOByName(String br_name) {
	  System.out.println("RoleVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<RoleVO> list = tSvc.getall();
      List<Object> listObj = new ArrayList<Object>();
      for(RoleVO roleVO : list) {
          listObj.add((Object)roleVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      RoleVO roleVO = (RoleVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, roleVO.getBr_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(roleVO);
      System.out.println("RoleVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("RoleVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
//      System.out.println("RoleVOSer export(List<Object> list) size " + list.size());
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("RoleVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String br_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("br_no : " + br_no);
          String action = "update";
          importOne(action, br_no);
      }
  }

}
