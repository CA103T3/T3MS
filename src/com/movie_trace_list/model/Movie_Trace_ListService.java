package com.movie_trace_list.model;


//import java.sql.Timestamp;
import java.util.*;



public class Movie_Trace_ListService {
	private Movie_Trace_ListDAO_interface dao;
	public Movie_Trace_ListService() {
		dao = new Movie_Trace_ListDAO();
	}
	
	public Movie_Trace_ListVO addMovie_Trace_List(String movie_no){

		Movie_Trace_ListVO movie_Trace_listVO = new Movie_Trace_ListVO();

//		movie_Trace_listVO.setMem_no(mem_no);
		movie_Trace_listVO.setMovie_no(movie_no);
//		
		dao.insert(movie_Trace_listVO);

		return movie_Trace_listVO;
	}

	public Movie_Trace_ListVO updateMovie_Trace_List(String mem_no,String movie_no) {

		Movie_Trace_ListVO movie_Trace_listVO = new Movie_Trace_ListVO();

		movie_Trace_listVO.setMem_no(mem_no);
		movie_Trace_listVO.setMovie_no(movie_no);
		
		dao.update(movie_Trace_listVO);

		return movie_Trace_listVO;
	}

	public void Movie_Trace_ListVO(String mem_no) {
		dao.delete(mem_no);
	}

	public Movie_Trace_ListVO getOneMovie_Trace_ListVO(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public List<Movie_Trace_ListVO> getAll() {
		System.out.println("OK");
		return dao.getAll();
	}
}
