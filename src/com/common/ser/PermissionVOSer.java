package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.permission.model.*;


public class PermissionVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/PermissionVO";
  private String dir = "resources/ser/PermissionVO"; //for Java EE Servlet
  private PermissionService tSvc;
  private PermissionDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public PermissionVOSer() {
      tSvc = new PermissionService();
      dao = new PermissionDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      PermissionVO pVO = readPermissionVOSer(target);
      if("add".equals(action)) {
          PermissionVO tmpVO = tSvc.findbypk(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String rp_no = dao.insert(pVO);
              System.out.println("PermissionVOSer insert " + rp_no);
          }
      } else if("update".equals(action)) {
          dao.update(pVO);
          System.out.println("PermissionVOSer update " + pVO.getRp_no());
      } else {
          System.out.println("PermissionVOSer no corresponding action");
      }
  }

  public PermissionVO readPermissionVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      PermissionVO pVO = (PermissionVO) ois.readObject();
      ois.close();
      fis.close();
      return pVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("PermissionVOSer importAll");
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
  public Object findVOByNo(String rp_no) {
      PermissionVO pVO = tSvc.findbypk(rp_no);
      return pVO;
  }

  @Override
  public Object findVOByName(String theater_name) {
	  System.out.println("PermissionVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<PermissionVO> list = tSvc.getall();
      List<Object> listObj = new ArrayList<Object>();
      for(PermissionVO pVO : list) {
          listObj.add((Object)pVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      PermissionVO pVO = (PermissionVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, pVO.getRp_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(pVO);
      System.out.println("PermissionVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("PermissionVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
//      System.out.println("PermissionVOSer export(List<Object> list) size " + list.size());
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("PermissionVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String rp_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "update";
          importOne(action, rp_no);
      }
  }

}
