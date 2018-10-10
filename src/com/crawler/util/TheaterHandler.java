package com.crawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cinema.model.*;
import com.common.ser.TheaterVOSer;
import com.common.ser.MovieVOSer;
import com.common.ser.SerUtil;
import com.theater.model.*;
import java.io.StringReader;

public class TheaterHandler {
    private List<HashMap> movieSessionList;
    private Set<String> cinemaSet;
    //private String dir = "WebContent/resources/crawler/movieinfo"; //workable ?!
    private String dir = "resources/crawler/movieinfo"; //for Java EE Environment
    private String servletContextRealPath;

    public void setServletContextRealPath(String servletContextRealPath) {
        this.servletContextRealPath = servletContextRealPath;
    }

    public TheaterHandler() {
        // TODO Auto-generated constructor stub
    }

	public TheaterHandler(List<HashMap> movieSessionList) {
		this.movieSessionList = movieSessionList;
	}

    public TheaterHandler(Set<String> cinemaSet) {
        this.cinemaSet = cinemaSet;
    }

    public List<String> importDB() {
        System.out.println("TheaterHandler importDB");
        List<String> list = new ArrayList<String>();
//        for(int i = 0; i < movieSessionList.size(); i++) {
//            String cinema_name = (String) movieSessionList.get(i).get("cinemaName");
//            System.out.println("TheaterHandler importDB cinemaName :" + movieSessionList.get(i).get("cinemaName"));
//            TheaterService tSvc = new TheaterService();
//            CinemaService cSvc = new CinemaService();
//            CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
//            if(cinemaVO != null) {
//                List<TheaterVO> tList = tSvc.getAllofCinema(cinemaVO.getCinema_no());
//                String equipment = (String) movieSessionList.get(i).get("version");
//                String sessionDate = (String) movieSessionList.get(i).get("sessionDate");
//                String time = (String) movieSessionList.get(i).get("time");
//                String cinemaName = (String) movieSessionList.get(i).get("cinemaName");
//                if(tList == null) {
//                    for(int j = 0; j < 25; j++) {
//                        //add 25 theaters if no data at first
//                        addTheater(j, cinemaVO.getCinema_no());
//                    }
//                } else {
//                    if(!("數位".equals(movieSessionList.get(i).get("version")))) {
//                        System.out.println("不是數位:  " + cinemaName + " " + equipment + " " + sessionDate + " " + " " + time);
//                        List<TheaterVO> li = tSvc.getAllByCinemaNOEquipment(cinemaVO.getCinema_no(), equipment);
//                        System.out.println(equipment + " li.size() " + li.size());
//                    } else {
//                        System.out.println("是數位:  " + cinemaName + " " + equipment + " " + sessionDate + " " + " " + time);
//                    }
//                }
//                
//            }
//        }
        String theater_no;
        Iterator iterator = cinemaSet.iterator();

        while(iterator.hasNext()) {
			String cinema_name = (String) iterator.next();
			System.out.println("TheaterHandler importDB, cinema : " + cinema_name);
			CinemaService cSvc = new CinemaService();
			TheaterService tSvc = new TheaterService();
            CinemaVO cinemaVO = cSvc.getOneCinemaByCinemaName(cinema_name);
            if(cinemaVO != null) {
            	List<TheaterVO> tList = tSvc.getAllofCinema(cinemaVO.getCinema_no());
            	if(tList.size() == 0) {
					for (int j = 0; j < 25; j++) {
						// add 25 theaters if no data at first
						theater_no = addTheater(j, cinemaVO.getCinema_no());
						list.add(theater_no);
						System.out.println("TheaterHandler importDB theater_no : " + theater_no);
					}
            	}
            }
        }

        return list;
    }

    public void exportSer(List<String> list) {
        SerUtil su = new SerUtil();
        TheaterVOSer tSer = new TheaterVOSer();
        tSer.setServletContextRealPath(servletContextRealPath);
        su.setStrategy(tSer);
        for(String theater_no : list) {
            Object vo = su.findVOByNo(theater_no);
            if(vo != null) {
                try {
                    su.export(vo);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public String addTheater(int i, String no) {
        TheaterService tSvc = new TheaterService();
        String cinema_no = no;
        char letter = (char) (65 + i);
        String theater_name = String.valueOf(letter) + "廳";
        System.out.println("addTheater " + theater_name);
        Integer t_rows = 20;
        Integer t_columns = 20;
        String clobContent = "{\"18_7\":[\"P_6\",\"2\"],\"18_6\":[\"x\",\"0\"],\"18_5\":[\"P_5\",\"2\"],\"18_4\":[\"P_4\",\"2\"],\"18_3\":" +
                             "[\"P_3\",\"2\"],\"18_2\":[\"P_2\",\"2\"],\"18_1\":[\"P_1\",\"2\"],\"17_14\":[\"O_13\",\"2\"],\"17_15\":[\"x\",\"0\"],\"17_16\":[\"O_14\",\"2\"],\r\n" + 
                             "\"17_17\":[\"O_15\",\"2\"],\"17_10\":[\"O_9\",\"2\"],\"17_11\":[\"O_10\",\"2\"],\"17_12\":[\"O_11\",\"2\"],\"17_13\":[\"O_12\",\"2\"],\"9_20\":[\"H_18\",\"2\"],\r\n" + 
                             "\"17_18\":[\"O_16\",\"2\"],\"17_19\":[\"O_17\",\"2\"],\"18_9\":[\"P_8\",\"2\"],\"18_8\":[\"P_7\",\"2\"],\"17_20\":[\"O_18\",\"2\"],\"17_8\":[\"O_7\",\"2\"],\r\n" + 
                             "\"17_7\":[\"O_6\",\"2\"],\"17_6\":[\"x\",\"0\"],\"17_5\":[\"O_5\",\"2\"],\"16_17\":[\"x\",\"0\"],\"17_4\":[\"O_4\",\"2\"],\"16_18\":[\"x\",\"0\"],\r\n" + 
                             "\"17_3\":[\"O_3\",\"2\"],\"16_19\":[\"x\",\"0\"],\"17_2\":[\"O_2\",\"2\"],\"17_1\":[\"O_1\",\"2\"],\"16_20\":[\"x\",\"0\"],\"17_9\":[\"O_8\",\"2\"],\r\n" + 
                             "\"16_1\":[\"x\",\"0\"],\"16_9\":[\"x\",\"0\"],\"16_8\":[\"x\",\"0\"],\"16_7\":[\"x\",\"0\"],\"16_6\":[\"x\",\"0\"],\"16_5\":[\"x\",\"0\"],\"16_4\":[\"x\",\"0\"],\r\n" + 
                             "\"16_3\":[\"x\",\"0\"],\"16_2\":[\"x\",\"0\"],\"16_13\":[\"x\",\"0\"],\"16_14\":[\"x\",\"0\"],\"16_15\":[\"x\",\"0\"],\"16_16\":[\"x\",\"0\"],\"16_10\":[\"x\",\"0\"],\r\n" + 
                             "\"16_11\":[\"x\",\"0\"],\"16_12\":[\"x\",\"0\"],\"15_2\":[\"N_2\",\"2\"],\"15_1\":[\"N_1\",\"2\"],\"15_9\":[\"N_8\",\"2\"],\"15_8\":[\"N_7\",\"2\"],\r\n" + 
                             "\"15_7\":[\"N_6\",\"2\"],\"15_6\":[\"x\",\"0\"],\"15_5\":[\"N_5\",\"2\"],\"15_4\":[\"N_4\",\"2\"],\"15_3\":[\"N_3\",\"2\"],\"15_12\":[\"N_11\",\"2\"],\r\n" + 
                             "\"15_13\":[\"N_12\",\"2\"],\"15_14\":[\"N_13\",\"2\"],\"15_15\":[\"x\",\"0\"],\"15_10\":[\"N_9\",\"2\"],\"15_11\":[\"N_10\",\"2\"],\"1_19\":[\"A_17\",\"2\"],\r\n" + 
                             "\"1_18\":[\"A_16\",\"2\"],\"1_17\":[\"A_15\",\"2\"],\"1_16\":[\"A_14\",\"2\"],\"1_15\":[\"x\",\"0\"],\"1_14\":[\"A_13\",\"2\"],\"1_13\":[\"A_12\",\"2\"],\r\n" + 
                             "\"15_16\":[\"N_14\",\"2\"],\"15_17\":[\"N_15\",\"2\"],\"15_18\":[\"N_16\",\"2\"],\"15_19\":[\"N_17\",\"2\"],\"15_20\":[\"N_18\",\"2\"],\"1_12\":[\"A_11\",\"2\"],\r\n" + 
                             "\"1_11\":[\"A_10\",\"2\"],\"1_10\":[\"A_9\",\"2\"],\"14_3\":[\"M_3\",\"2\"],\"14_2\":[\"M_2\",\"2\"],\"14_1\":[\"M_1\",\"2\"],\"20_20\":[\"R_18\",\"2\"],\r\n" + 
                             "\"14_9\":[\"M_8\",\"2\"],\"14_8\":[\"M_7\",\"2\"],\"14_7\":[\"M_6\",\"2\"],\"14_6\":[\"x\",\"0\"],\"14_5\":[\"M_5\",\"2\"],\"14_4\":[\"M_4\",\"2\"],\r\n" + 
                             "\"20_17\":[\"R_15\",\"2\"],\"20_16\":[\"R_14\",\"2\"],\"20_15\":[\"x\",\"0\"],\"20_14\":[\"R_13\",\"2\"],\"20_13\":[\"R_12\",\"2\"],\"20_12\":[\"R_11\",\"2\"],\r\n" + 
                             "\"20_11\":[\"R_10\",\"2\"],\"20_10\":[\"R_9\",\"2\"],\"1_20\":[\"A_18\",\"2\"],\"20_19\":[\"R_17\",\"2\"],\"20_18\":[\"R_16\",\"2\"],\"13_4\":[\"L_4\",\"2\"],\r\n" + 
                             "\"13_3\":[\"L_3\",\"2\"],\"13_2\":[\"L_2\",\"2\"],\"13_1\":[\"L_1\",\"2\"],\"2_14\":[\"B_13\",\"2\"],\"2_15\":[\"x\",\"0\"],\"2_16\":[\"B_14\",\"2\"],\r\n" + 
                             "\"2_17\":[\"B_15\",\"2\"],\"13_9\":[\"L_8\",\"2\"],\"2_18\":[\"B_16\",\"2\"],\"13_8\":[\"L_7\",\"2\"],\"2_19\":[\"B_17\",\"2\"],\"13_7\":[\"L_6\",\"2\"],\r\n" + 
                             "\"13_6\":[\"x\",\"0\"],\"13_5\":[\"L_5\",\"2\"],\"19_17\":[\"Q_15\",\"2\"],\"19_16\":[\"Q_14\",\"2\"],\"19_19\":[\"Q_17\",\"2\"],\"19_18\":[\"Q_16\",\"2\"],\r\n" + 
                             "\"2_10\":[\"B_9\",\"2\"],\"19_13\":[\"Q_12\",\"2\"],\"2_11\":[\"B_10\",\"2\"],\"19_12\":[\"Q_11\",\"2\"],\"2_12\":[\"B_11\",\"2\"],\"19_15\":[\"x\",\"0\"],\r\n" + 
                             "\"2_13\":[\"B_12\",\"2\"],\"19_14\":[\"Q_13\",\"2\"],\"19_11\":[\"Q_10\",\"2\"],\"19_10\":[\"Q_9\",\"2\"],\"2_20\":[\"B_18\",\"2\"],\"19_20\":[\"Q_18\",\"2\"],\r\n" + 
                             "\"12_5\":[\"K_5\",\"3\"],\"12_4\":[\"K_4\",\"3\"],\"12_3\":[\"K_3\",\"3\"],\"12_2\":[\"K_2\",\"3\"],\"12_1\":[\"K_1\",\"3\"],\"12_9\":[\"K_8\",\"3\"],\r\n" + 
                             "\"12_8\":[\"K_7\",\"3\"],\"18_19\":[\"P_17\",\"2\"],\"12_7\":[\"K_6\",\"3\"],\"12_6\":[\"x\",\"0\"],\"18_20\":[\"P_18\",\"2\"],\"3_15\":[\"x\",\"0\"],\r\n" + 
                             "\"3_16\":[\"C_14\",\"2\"],\"3_17\":[\"C_15\",\"2\"],\"3_18\":[\"C_16\",\"2\"],\"9_1\":[\"H_1\",\"2\"],\"3_19\":[\"C_17\",\"2\"],\"9_2\":[\"H_2\",\"2\"],\r\n" + 
                             "\"9_3\":[\"H_3\",\"2\"],\"9_4\":[\"H_4\",\"2\"],\"9_5\":[\"H_5\",\"2\"],\"9_6\":[\"x\",\"0\"],\"9_7\":[\"H_6\",\"2\"],\"9_8\":[\"H_7\",\"2\"],\"3_10\":[\"C_9\",\"2\"],\r\n" + 
                             "\"9_9\":[\"H_8\",\"2\"],\"3_11\":[\"C_10\",\"2\"],\"3_12\":[\"C_11\",\"2\"],\"3_13\":[\"C_12\",\"2\"],\"3_14\":[\"C_13\",\"2\"],\"11_6\":[\"x\",\"0\"],\r\n" + 
                             "\"11_5\":[\"J_5\",\"3\"],\"11_4\":[\"J_4\",\"3\"],\"11_3\":[\"J_3\",\"3\"],\"11_2\":[\"J_2\",\"3\"],\"11_1\":[\"J_1\",\"3\"],\"11_9\":[\"J_8\",\"3\"],\r\n" + 
                             "\"11_8\":[\"J_7\",\"3\"],\"11_7\":[\"J_6\",\"3\"],\"3_20\":[\"C_18\",\"2\"],\"8_1\":[\"G_1\",\"2\"],\"8_2\":[\"G_2\",\"2\"],\"8_3\":[\"G_3\",\"2\"],\r\n" + 
                             "\"8_4\":[\"G_4\",\"2\"],\"8_5\":[\"G_5\",\"2\"],\"8_6\":[\"x\",\"0\"],\"8_7\":[\"G_6\",\"2\"],\"18_16\":[\"P_14\",\"2\"],\"8_8\":[\"G_7\",\"2\"],\r\n" + 
                             "\"18_15\":[\"x\",\"0\"],\"8_9\":[\"G_8\",\"2\"],\"18_18\":[\"P_16\",\"2\"],\"18_17\":[\"P_15\",\"2\"],\"18_12\":[\"P_11\",\"2\"],\"18_11\":[\"P_10\",\"2\"],\r\n" + 
                             "\"18_14\":[\"P_13\",\"2\"],\"18_13\":[\"P_12\",\"2\"],\"18_10\":[\"P_9\",\"2\"],\"10_7\":[\"I_6\",\"2\"],\"10_6\":[\"x\",\"0\"],\"10_5\":[\"I_5\",\"2\"],\r\n" + 
                             "\"10_4\":[\"I_4\",\"2\"],\"10_3\":[\"I_3\",\"2\"],\"10_2\":[\"I_2\",\"2\"],\"10_1\":[\"I_1\",\"2\"],\"4_17\":[\"D_15\",\"2\"],\"4_16\":[\"D_14\",\"2\"],\r\n" + 
                             "\"4_19\":[\"D_17\",\"2\"],\"4_18\":[\"D_16\",\"2\"],\"10_9\":[\"I_8\",\"2\"],\"10_8\":[\"I_7\",\"2\"],\"4_11\":[\"D_10\",\"2\"],\"4_10\":[\"D_9\",\"2\"],\r\n" + 
                             "\"4_13\":[\"D_12\",\"2\"],\"4_12\":[\"D_11\",\"2\"],\"4_15\":[\"x\",\"0\"],\"4_14\":[\"D_13\",\"2\"],\"7_1\":[\"F_1\",\"2\"],\"7_2\":[\"F_2\",\"2\"],\r\n" + 
                             "\"7_3\":[\"F_3\",\"2\"],\"7_4\":[\"F_4\",\"2\"],\"7_5\":[\"F_5\",\"2\"],\"7_6\":[\"x\",\"0\"],\"7_7\":[\"F_6\",\"2\"],\"4_20\":[\"D_18\",\"2\"],\r\n" + 
                             "\"7_8\":[\"F_7\",\"2\"],\"7_9\":[\"F_8\",\"2\"],\"12_11\":[\"K_10\",\"3\"],\"12_12\":[\"K_11\",\"3\"],\"12_10\":[\"K_9\",\"3\"],\"11_10\":[\"J_9\",\"3\"],\r\n" + 
                             "\"11_11\":[\"J_10\",\"3\"],\"20_8\":[\"R_7\",\"2\"],\"20_7\":[\"R_6\",\"2\"],\"20_6\":[\"x\",\"0\"],\"20_5\":[\"R_5\",\"2\"],\"20_4\":[\"R_4\",\"2\"],\r\n" + 
                             "\"20_3\":[\"R_3\",\"2\"],\"20_2\":[\"R_2\",\"2\"],\"20_1\":[\"R_1\",\"2\"],\"5_18\":[\"E_16\",\"2\"],\"6_1\":[\"x\",\"0\"],\"11_14\":[\"J_13\",\"3\"],\r\n" + 
                             "\"5_17\":[\"E_15\",\"2\"],\"6_2\":[\"x\",\"0\"],\"11_15\":[\"x\",\"0\"],\"6_3\":[\"x\",\"0\"],\"11_12\":[\"J_11\",\"3\"],\"5_19\":[\"E_17\",\"2\"],\r\n" + 
                             "\"6_4\":[\"x\",\"0\"],\"11_13\":[\"J_12\",\"3\"],\"6_5\":[\"x\",\"0\"],\"11_18\":[\"J_16\",\"3\"],\"6_6\":[\"x\",\"0\"],\"11_19\":[\"J_17\",\"3\"],\r\n" + 
                             "\"6_7\":[\"x\",\"0\"],\"11_16\":[\"J_14\",\"3\"],\"6_8\":[\"x\",\"0\"],\"11_17\":[\"J_15\",\"3\"],\"20_9\":[\"R_8\",\"2\"],\"5_10\":[\"E_9\",\"2\"],\r\n" + 
                             "\"6_9\":[\"x\",\"0\"],\"5_12\":[\"E_11\",\"2\"],\"5_11\":[\"E_10\",\"2\"],\"5_14\":[\"E_13\",\"2\"],\"5_13\":[\"E_12\",\"2\"],\"5_16\":[\"E_14\",\"2\"],\r\n" + 
                             "\"5_15\":[\"x\",\"0\"],\"11_20\":[\"J_18\",\"3\"],\"10_19\":[\"I_17\",\"2\"],\"10_12\":[\"I_11\",\"2\"],\"10_11\":[\"I_10\",\"2\"],\"10_14\":[\"I_13\",\"2\"],\r\n" + 
                             "\"10_13\":[\"I_12\",\"2\"],\"10_16\":[\"I_14\",\"2\"],\"10_15\":[\"x\",\"0\"],\"10_18\":[\"I_16\",\"2\"],\"10_17\":[\"I_15\",\"2\"],\"5_20\":[\"E_18\",\"2\"],\r\n" + 
                             "\"10_20\":[\"I_18\",\"2\"],\"5_1\":[\"E_1\",\"2\"],\"5_2\":[\"E_2\",\"2\"],\"5_3\":[\"E_3\",\"2\"],\"5_4\":[\"E_4\",\"2\"],\"5_5\":[\"E_5\",\"2\"],\"5_6\":[\"x\",\"0\"],\r\n" + 
                             "\"5_7\":[\"E_6\",\"2\"],\"5_8\":[\"E_7\",\"2\"],\"5_9\":[\"E_8\",\"2\"],\"6_19\":[\"x\",\"0\"],\"6_18\":[\"x\",\"0\"],\"6_11\":[\"x\",\"0\"],\"6_10\":[\"x\",\"0\"],\r\n" + 
                             "\"6_13\":[\"x\",\"0\"],\"6_12\":[\"x\",\"0\"],\"6_15\":[\"x\",\"0\"],\"6_14\":[\"x\",\"0\"],\"6_17\":[\"x\",\"0\"],\"6_16\":[\"x\",\"0\"],\"4_1\":[\"D_1\",\"2\"],\r\n" + 
                             "\"4_2\":[\"D_2\",\"2\"],\"4_3\":[\"D_3\",\"2\"],\"4_4\":[\"D_4\",\"2\"],\"4_5\":[\"D_5\",\"2\"],\"4_6\":[\"x\",\"0\"],\"4_7\":[\"D_6\",\"2\"],\"4_8\":[\"D_7\",\"2\"],\r\n" + 
                             "\"4_9\":[\"D_8\",\"2\"],\"10_10\":[\"I_9\",\"2\"],\"6_20\":[\"x\",\"0\"],\"14_18\":[\"M_16\",\"2\"],\"14_17\":[\"M_15\",\"2\"],\"14_16\":[\"M_14\",\"2\"],\r\n" + 
                             "\"14_15\":[\"x\",\"0\"],\"14_19\":[\"M_17\",\"2\"],\"14_20\":[\"M_18\",\"2\"],\"3_1\":[\"C_1\",\"2\"],\"3_2\":[\"C_2\",\"2\"],\"3_3\":[\"C_3\",\"2\"],\r\n" + 
                             "\"3_4\":[\"C_4\",\"2\"],\"3_5\":[\"C_5\",\"2\"],\"3_6\":[\"x\",\"0\"],\"7_19\":[\"F_17\",\"2\"],\"3_7\":[\"C_6\",\"2\"],\"3_8\":[\"C_7\",\"2\"],\r\n" + 
                             "\"3_9\":[\"C_8\",\"2\"],\"7_13\":[\"F_12\",\"2\"],\"7_14\":[\"F_13\",\"2\"],\"7_11\":[\"F_10\",\"2\"],\"7_12\":[\"F_11\",\"2\"],\"7_17\":[\"F_15\",\"2\"],\r\n" + 
                             "\"7_18\":[\"F_16\",\"2\"],\"7_15\":[\"x\",\"0\"],\"7_16\":[\"F_14\",\"2\"],\"7_10\":[\"F_9\",\"2\"],\"7_20\":[\"F_18\",\"2\"],\"2_1\":[\"B_1\",\"2\"],\r\n" + 
                             "\"2_2\":[\"B_2\",\"2\"],\"2_3\":[\"B_3\",\"2\"],\"2_4\":[\"B_4\",\"2\"],\"2_5\":[\"B_5\",\"2\"],\"2_6\":[\"x\",\"0\"],\"2_7\":[\"B_6\",\"2\"],\"2_8\":[\"B_7\",\"2\"],\r\n" + 
                             "\"2_9\":[\"B_8\",\"2\"],\"14_10\":[\"M_9\",\"2\"],\"14_14\":[\"M_13\",\"2\"],\"14_13\":[\"M_12\",\"2\"],\"14_12\":[\"M_11\",\"2\"],\"14_11\":[\"M_10\",\"2\"],\r\n" + 
                             "\"8_14\":[\"G_13\",\"2\"],\"8_15\":[\"x\",\"0\"],\"8_12\":[\"G_11\",\"2\"],\"8_13\":[\"G_12\",\"2\"],\"8_18\":[\"G_16\",\"2\"],\"13_13\":[\"L_12\",\"2\"],\r\n" + 
                             "\"8_19\":[\"G_17\",\"2\"],\"13_12\":[\"L_11\",\"2\"],\"8_16\":[\"G_14\",\"2\"],\"13_11\":[\"L_10\",\"2\"],\"8_17\":[\"G_15\",\"2\"],\"13_10\":[\"L_9\",\"2\"],\r\n" + 
                             "\"8_10\":[\"G_9\",\"2\"],\"8_11\":[\"G_10\",\"2\"],\"1_1\":[\"A_1\",\"2\"],\"1_2\":[\"A_2\",\"2\"],\"1_3\":[\"A_3\",\"2\"],\"1_4\":[\"A_4\",\"2\"],\r\n" + 
                             "\"1_5\":[\"A_5\",\"2\"],\"1_6\":[\"x\",\"0\"],\"13_17\":[\"L_15\",\"2\"],\"1_7\":[\"A_6\",\"2\"],\"13_16\":[\"L_14\",\"2\"],\"1_8\":[\"A_7\",\"2\"],\r\n" + 
                             "\"13_15\":[\"x\",\"0\"],\"1_9\":[\"A_8\",\"2\"],\"13_14\":[\"L_13\",\"2\"],\"13_19\":[\"L_17\",\"2\"],\"13_18\":[\"L_16\",\"2\"],\"13_20\":[\"L_18\",\"2\"],\r\n" + 
                             "\"8_20\":[\"G_18\",\"2\"],\"12_15\":[\"x\",\"0\"],\"19_6\":[\"x\",\"0\"],\"12_16\":[\"K_14\",\"3\"],\"19_5\":[\"Q_5\",\"2\"],\"12_13\":[\"K_12\",\"3\"],\r\n" + 
                             "\"19_4\":[\"Q_4\",\"2\"],\"12_14\":[\"K_13\",\"3\"],\"19_3\":[\"Q_3\",\"2\"],\"12_19\":[\"K_17\",\"3\"],\"19_2\":[\"Q_2\",\"2\"],\"19_1\":[\"Q_1\",\"2\"],\r\n" + 
                             "\"12_17\":[\"K_15\",\"3\"],\"12_18\":[\"K_16\",\"3\"],\"12_20\":[\"K_18\",\"3\"],\"9_16\":[\"H_14\",\"2\"],\"9_15\":[\"x\",\"0\"],\"9_14\":[\"H_13\",\"2\"],\r\n" + 
                             "\"9_13\":[\"H_12\",\"2\"],\"9_19\":[\"H_17\",\"2\"],\"19_9\":[\"Q_8\",\"2\"],\"9_18\":[\"H_16\",\"2\"],\"19_8\":[\"Q_7\",\"2\"],\"9_17\":[\"H_15\",\"2\"],\r\n" + 
                             "\"19_7\":[\"Q_6\",\"2\"],\"9_12\":[\"H_11\",\"2\"],\"9_11\":[\"H_10\",\"2\"],\"9_10\":[\"H_9\",\"2\"]}";

        StringReader seat_model = new StringReader(clobContent); 
        Integer seats = 324;
        String equipment = "數位";

        String theater_no = tSvc.addTheater(cinema_no, theater_name, t_rows, t_columns, seat_model, seats, equipment);

        return theater_no;
    }
    
}
