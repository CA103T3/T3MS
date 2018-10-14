package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ticketType.model.*;


public class TypeVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/TypeVO";
  private String dir = "resources/ser/TypeVO"; //for Java EE Servlet
  private TypeService tSvc;
  private TypeDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public TypeVOSer() {
      tSvc = new TypeService();
      dao = new TypeDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      TypeVO typeVO = readTypeVOSer(target);
      if("add".equals(action)) {
          TypeVO tmpVO = tSvc.getOneType(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String type_no = dao.insert(typeVO);
              System.out.println("TypeVOSer insert " + type_no);
          }
      } else if("update".equals(action)) {
          dao.update(typeVO);
          System.out.println("TypeVOSer update " + typeVO.getType_no());
      } else {
          System.out.println("TypeVOSer no corresponding action");
      }
  }

  public TypeVO readTypeVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      TypeVO typeVO = (TypeVO) ois.readObject();
      ois.close();
      fis.close();
      return typeVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("TypeVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String type_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("type_no : " + type_no);
          String action = "add";
          importOne(action, type_no);
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
  public Object findVOByNo(String type_no) {
      TypeVO typeVO = tSvc.getOneType(type_no);
      return typeVO;
  }

  @Override
  public Object findVOByName(String type_name) {
      System.out.println("TypeVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<TypeVO> list = tSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(TypeVO typeVO : list) {
          listObj.add((Object)typeVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      TypeVO typeVO = (TypeVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, typeVO.getType_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(typeVO);
      System.out.println("TypeVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("TypeVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("TypeVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String type_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("type_no : " + type_no);
          String action = "update";
          importOne(action, type_no);
      }
  }

}
