package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.account.model.*;


public class AccountVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/AccountVO";
  private String dir = "resources/ser/AccountVO"; //for Java EE Servlet
  private AccountService tSvc;
  private AccountDAO_interface dao;
  private String servletContextRealPath;

  public void setServletContextRealPath(String servletContextRealPath) {
      this.servletContextRealPath = servletContextRealPath;
  }

  public AccountVOSer() {
      tSvc = new AccountService();
      dao = new AccountJNDIDAO();
  }

  @Override
  public void importOne(String action, String no) throws Exception {
      File fsaveDir = checkSaveDir();
      File target = new File(fsaveDir, no + ".ser");
      AccountVO aVO = readAccountVOSer(target);
      if("add".equals(action)) {
          AccountVO tmpVO = tSvc.findVObyno(no);
          if(tmpVO != null) {
              System.out.println(no + " already existed !");
          } else {
              String Bs_acc_no = dao.insert(aVO);
              System.out.println("AccountVOSer insert " + Bs_acc_no);
          }
      } else if("update".equals(action)) {
          dao.update(aVO);
          System.out.println("AccountVOSer update " + aVO.getBs_acc_no());
      } else {
          System.out.println("AccountVOSer no corresponding action");
      }
  }

  public AccountVO readAccountVOSer(File f) throws Exception {
      FileInputStream fis = new FileInputStream(f);
      ObjectInputStream ois = new ObjectInputStream(fis);
      AccountVO aVO = (AccountVO) ois.readObject();
      ois.close();
      fis.close();
      return aVO;
  }

  @Override
  public void importAll() throws Exception {
      System.out.println("AccountVOSer importAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String Bs_acc_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "add";
          importOne(action, Bs_acc_no);
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
  public Object findVOByNo(String bs_acc_no) {
      AccountVO aVO = tSvc.findVObyno(bs_acc_no);
      return aVO;
  }

  @Override
  public Object findVOByName(String theater_name) {
	  System.out.println("AccountVOSer findVOByName not implement");
      return null;
  }

  @Override
  public List<Object> getAllVO() {
      // TODO Auto-generated method stub
      List<AccountVO> list = tSvc.getAll();
      List<Object> listObj = new ArrayList<Object>();
      for(AccountVO aVO : list) {
          listObj.add((Object)aVO);
      }
      return listObj;
  }

  @Override
  public void export(Object vo) throws IOException {
      AccountVO aVO = (AccountVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
      File fsaveDir = checkSaveDir();
      File file = new File(fsaveDir, aVO.getBs_acc_no() + ".ser");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(aVO);
      System.out.println("AccountVOSer export : " + file.toString());
      oos.close();
      fos.close();
  }

  @Override
  public void export(List<Object> list) throws IOException {
      System.out.println("AccountVOSer export(List<Object> list)");
      for(Object vo : list) {
          export(vo);
      }
//      System.out.println("AccountVOSer export(List<Object> list) size " + list.size());
  }

  @Override
  public void importUpdateAll() throws Exception {
      System.out.println("AccountVOSer importUpdateAll");
      File fsaveDir = checkSaveDir();
      for(File f : fsaveDir.listFiles()){
          System.out.println(f.getName());
          String filename = f.getName();
          String Bs_acc_no = filename.substring(0, filename.lastIndexOf("."));
          //System.out.println("theater_no : " + theater_no);
          String action = "update";
          importOne(action, Bs_acc_no);
      }
  }

}
