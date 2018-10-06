package com.movie_trace_list.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Movie_Trace_ListJNDIDAO implements Movie_Trace_ListDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO MOVIE_TRACE_LIST(MEM_NO,MOVIE_NO) VALUES (movie_trace_list_seq.NEXTVAL,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO,MOVIE_NO FROM MOVIE_TRACE_LIST order by MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEM_NO,MOVIE_NO FROM MOVIE_TRACE_LIST where MEM_NO = ?";
	private static final String DELETE = 
		"DELETE FROM MOVIE_TRACE_LIST where MEM_NO = ?";
	private static final String UPDATE = 
		"UPDATE MOVIE_TRACE_LIST set MOVIE_NO=? where MEM_NO = ?";
//	private static final String GET_Acts_ByActno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
	@Override
	public void insert(Movie_Trace_ListVO movie_Trace_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, movie_Trace_listVO.getMovie_no());
			
			
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Movie_Trace_ListVO movie_Trace_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movie_Trace_listVO.getMovie_no());
			pstmt.setString(2, movie_Trace_listVO.getMem_no());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String MEM_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, MEM_NO);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Movie_Trace_ListVO findByPrimaryKey(String mem_no) {

		Movie_Trace_ListVO movie_Trace_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVo 也稱為 Domain objects
				movie_Trace_listVO = new Movie_Trace_ListVO();
				movie_Trace_listVO.setMem_no(rs.getString("mem_no"));
				movie_Trace_listVO.setMovie_no(rs.getString("movie_no"));
				
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return movie_Trace_listVO;
	}

	@Override
	public List<Movie_Trace_ListVO> getAll() {
		List<Movie_Trace_ListVO> list = new ArrayList<Movie_Trace_ListVO>();
		Movie_Trace_ListVO movie_Trace_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			System.out.println("OK");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			System.out.println("OK");
			rs = pstmt.executeQuery();
System.out.println("OK");

			while (rs.next()) {
				// actVO 也稱為 Domain objects
				movie_Trace_listVO = new Movie_Trace_ListVO();
				movie_Trace_listVO.setMem_no(rs.getString("mem_no"));
				movie_Trace_listVO.setMovie_no(rs.getString("movie_no"));
				
				list.add(movie_Trace_listVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	

//	public static void main(String[] args) {
//
//		Movie_Trace_ListJDBCDAO dao = new Movie_Trace_ListJDBCDAO();

//		// 新增
//		Movie_Trace_ListVO movie_Trace_listVO1 = new Movie_Trace_ListVO();
//		movie_Trace_listVO1.setMovie_no("4");
//		
//		
//		dao.insert(movie_Trace_listVO1);

		// 修改
//		Movie_Trace_ListVO movie_Trace_listVO2 = new Movie_Trace_ListVO();
//		movie_Trace_listVO2.setMem_no("1");
//		movie_Trace_listVO2.setMovie_no("鐘天佑");
////		
//		dao.update(movie_Trace_listVO2);
//
//		 刪除
//		dao.delete("1");

//		// 查詢
//		Movie_Trace_ListVO movie_Trace_listVO3 = dao.findByPrimaryKey("3");
//		System.out.print(movie_Trace_listVO3.getMem_no() + ",");
//		System.out.println(movie_Trace_listVO3.getMovie_no() );
////		
////		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Movie_Trace_ListVO> list = dao.getAll();
//		for (Movie_Trace_ListVO amovie_Trace_listVO : list) {
//			System.out.print(amovie_Trace_listVO.getMem_no() + ",");
//			System.out.print(amovie_Trace_listVO.getMovie_no());
//			
//			
//			System.out.println();
//		}

//	}
}
