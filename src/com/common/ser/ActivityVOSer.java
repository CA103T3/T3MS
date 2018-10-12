package com.common.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.activity.model.*;

public class ActivityVOSer implements SerStrategy_interface {

//  private String dir = "WebContent/resources/ser/ActivityVO";
	private String dir = "resources/ser/ActivityVO"; // for Java EE Servlet
	private ActivityService cSvc;
	private ActivityDAO_interface dao;
	private String servletContextRealPath;

	public void setServletContextRealPath(String servletContextRealPath) {
		this.servletContextRealPath = servletContextRealPath;
	}

	public ActivityVOSer() {
		cSvc = new ActivityService();
		dao = new ActivityJNDIDAO();
	}

	@Override
	public void importOne(String action, String no) throws Exception {
		File fsaveDir = checkSaveDir();
		File target = new File(fsaveDir, no + ".ser");
		ActivityVO ActivityVO = readActivityVOSer(target);
		if ("add".equals(action)) {
			ActivityVO tmpVO = cSvc.getOneActivity(no);
			if (tmpVO != null) {
				System.out.println(no + " already existed !");
			} else {
				String Activity_no = dao.insertReturnActivityNo(ActivityVO);
				System.out.println("ActivityVOSer insert " + Activity_no);
			}
		} else if ("update".equals(action)) {
			dao.update(ActivityVO);
			System.out.println("ActivityVOSer update " + ActivityVO.getActivity_no());
		} else {
			System.out.println("ActivityVOSer no corresponding action");
		}
	}

	public ActivityVO readActivityVOSer(File f) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ActivityVO ActivityVO = (ActivityVO) ois.readObject();
		ois.close();
		fis.close();
		return ActivityVO;
	}

	@Override
	public void importAll() throws Exception {
		System.out.println("ActivityVOSer importAll");
		File fsaveDir = checkSaveDir();
		for (File f : fsaveDir.listFiles()) {
			System.out.println(f.getName());
			String filename = f.getName();
			String Activity_no = filename.substring(0, filename.lastIndexOf("."));
			// System.out.println("Activity_no : " + Activity_no);
			String action = "add";
			importOne(action, Activity_no);
		}
	}

	public File checkSaveDir() {
		File fsaveDir = new File(servletContextRealPath, dir);
		if (!fsaveDir.exists()) {
			fsaveDir.mkdirs();
		}
		return fsaveDir;
	}

	@Override
	public Object findVOByNo(String Activity_no) {
		ActivityVO ActivityVO = cSvc.getOneActivity(Activity_no);
		return ActivityVO;
	}

	@Override
	public Object findVOByName(String Activity_name) {
		ActivityVO ActivityVO = cSvc.getOneActivity(Activity_name);
		return ActivityVO;
	}

	@Override
	public List<Object> getAllVO() {
		// TODO Auto-generated method stub
		List<ActivityVO> list = cSvc.getAll();
		List<Object> listObj = new ArrayList<Object>();
		for (ActivityVO ActivityVO : list) {
			listObj.add((Object) ActivityVO);
		}
		return listObj;
	}

	@Override
	public void export(Object vo) throws IOException {
		ActivityVO ActivityVO = (ActivityVO) vo;
//      String realPath = getServletContext().getRealPath(dir);
		File fsaveDir = checkSaveDir();
		File file = new File(fsaveDir, ActivityVO.getActivity_no() + ".ser");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ActivityVO);
		System.out.println("ActivityVOSer export : " + file.toString());
		oos.close();
		fos.close();
	}

	@Override
	public void export(List<Object> list) throws IOException {
		System.out.println("ActivityVOSer export(List<Object> list)");
		for (Object vo : list) {
			export(vo);
		}
	}

	@Override
	public void importUpdateAll() throws Exception {
		System.out.println("ActivityVOSer importUpdateAll");
		File fsaveDir = checkSaveDir();
		for (File f : fsaveDir.listFiles()) {
			System.out.println(f.getName());
			String filename = f.getName();
			String Activity_no = filename.substring(0, filename.lastIndexOf("."));
			// System.out.println("Activity_no : " + Activity_no);
			String action = "update";
			importOne(action, Activity_no);
		}
	}

}
