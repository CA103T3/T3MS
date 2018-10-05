package com.filmreview.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;




public class FilmreviewJDBCDAO implements FilmreviewDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Filmreview (FR_NO,MOVIE_NO,CREATED_AT,UPDATED_AT,CONTENT,EVALUATION,TITLE,SOURCE,URL,MEM_NO,AUTHOR) VALUES ('F'||LPAD(to_char(FILMREVIEW_SEQ.NEXTVAL), 5, '0'), ?, current_timestamp, current_timestamp, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Filmreview order by UPDATED_AT DESC";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM Filmreview where FR_NO = ?";
	private static final String GET_MEM_STMT = 
			"SELECT * FROM Filmreview where MEM_NO = ?";
	private static final String DELETE = 
			"DELETE FROM Filmreview where FR_NO = ?";
	private static final String UPDATE = 
			"UPDATE Filmreview set MOVIE_NO=?, UPDATED_AT=current_timestamp, CONTENT=?, EVALUATION=?, TITLE=?, SOURCE=?, URL=?, MEM_NO=?, AUTHOR=? where FR_NO = ?";
	private static final String GET_ALL_MEM = 
			"SELECT * FROM Filmreview where MOVIE_NO LIKE ?";
	@Override
	public void insert(FilmreviewVO filmreviewVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, filmreviewVO.getMovie_no());
			
			pstmt.setString(2, filmreviewVO.getContent());
			pstmt.setDouble(3, filmreviewVO.getEvaluation());
			pstmt.setString(4, filmreviewVO.getTitle());
			pstmt.setString(5, filmreviewVO.getSource());
			pstmt.setString(6, filmreviewVO.getUrl());
			pstmt.setString(7, filmreviewVO.getMem_no());
			pstmt.setString(8, filmreviewVO.getAuthor());
			
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."+se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
			
		}
		
	}
	@Override
	public void update(FilmreviewVO filmreviewVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, filmreviewVO.getMovie_no());
			
			
			pstmt.setString(2, filmreviewVO.getContent());
			pstmt.setDouble(3, filmreviewVO.getEvaluation());
			pstmt.setString(4, filmreviewVO.getTitle());
			pstmt.setString(5, filmreviewVO.getSource());
			pstmt.setString(6, filmreviewVO.getUrl());
			pstmt.setString(7, filmreviewVO.getMem_no());
			pstmt.setString(8, filmreviewVO.getAuthor());
			
			pstmt.setString(9, filmreviewVO.getFr_no());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}finally{
			if(pstmt != null) {
				
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(String fr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, fr_no);
			
			pstmt.executeQuery();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}finally {
			if(pstmt != null) {
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
	public FilmreviewVO findByPrimaryKey(String fr_no) {
		
		FilmreviewVO filmreviewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fr_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreviewVO = new FilmreviewVO();
				filmreviewVO.setFr_no(rs.getString("fr_no"));
				filmreviewVO.setMovie_no(rs.getString("movie_no"));
				filmreviewVO.setCreated_at(rs.getDate("created_at"));
				filmreviewVO.setUpdated_at(rs.getDate("updated_at"));
				filmreviewVO.setContent(rs.getString("content"));
				filmreviewVO.setEvaluation(rs.getDouble("evaluation"));
				filmreviewVO.setTitle(rs.getString("title"));
				filmreviewVO.setSource(rs.getString("source"));
				filmreviewVO.setUrl(rs.getString("url"));
				filmreviewVO.setMem_no(rs.getString("mem_no"));
				filmreviewVO.setAuthor(rs.getString("author"));
				
			
				
			} 
				
				
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
		return filmreviewVO;
	}
	@Override
	public List<FilmreviewVO> getAll() {
		List<FilmreviewVO> list = new ArrayList<FilmreviewVO>();
		FilmreviewVO filmreviewVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreviewVO = new FilmreviewVO();
				filmreviewVO.setFr_no(rs.getString("fr_no"));
				filmreviewVO.setMovie_no(rs.getString("movie_no"));
				filmreviewVO.setCreated_at(rs.getDate("created_at"));
				filmreviewVO.setUpdated_at(rs.getDate("updated_at"));
				filmreviewVO.setContent(rs.getString("content"));
				filmreviewVO.setEvaluation(rs.getDouble("evaluation"));
				filmreviewVO.setTitle(rs.getString("title"));
				filmreviewVO.setSource(rs.getString("source"));
				filmreviewVO.setUrl(rs.getString("url"));
				filmreviewVO.setMem_no(rs.getString("mem_no"));
				filmreviewVO.setAuthor(rs.getString("author"));
				
				list.add(filmreviewVO);
				
			} 
				
				
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	@Override
	public Set<FilmreviewVO> getAllByMemNo(String movie_no) {
		Set<FilmreviewVO> set = new LinkedHashSet<FilmreviewVO>();
		FilmreviewVO filmreviewVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MEM);
			pstmt.setString(1, "%"+movie_no+"%");;
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreviewVO = new FilmreviewVO();
				filmreviewVO.setFr_no(rs.getString("fr_no"));
				filmreviewVO.setMovie_no(rs.getString("movie_no"));
				filmreviewVO.setCreated_at(rs.getDate("created_at"));
				filmreviewVO.setUpdated_at(rs.getDate("updated_at"));
				filmreviewVO.setContent(rs.getString("content"));
				filmreviewVO.setEvaluation(rs.getDouble("evaluation"));
				filmreviewVO.setTitle(rs.getString("title"));
				filmreviewVO.setSource(rs.getString("source"));
				filmreviewVO.setUrl(rs.getString("url"));
				filmreviewVO.setMem_no(rs.getString("mem_no"));
				filmreviewVO.setAuthor(rs.getString("author"));
				set.add(filmreviewVO);
				
			} 
				
				
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			//  Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return set;
	}
	
	
	
	public static void main(String[] args) {
	
		FilmreviewJDBCDAO dao = new FilmreviewJDBCDAO();
		// 新增
		
		FilmreviewVO filmreview1 = new FilmreviewVO();
		
		filmreview1.setMovie_no("666");
		
		
		filmreview1.setContent("好 看 !!");
		filmreview1.setEvaluation(new Double(5.0));
		filmreview1.setTitle("movie1");
		filmreview1.setSource("我自己");
		filmreview1.setUrl("www.yahoo.com.tw");
		filmreview1.setMem_no("M001");
		filmreview1.setAuthor("610");
	
	
		dao.insert(filmreview1);
		
		System.out.println("OK");
	
//		 修改
		
//		FilmreviewVO filmreview2 = new FilmreviewVO();
//		
//		filmreview2.setFr_no("F00002");
//		filmreview2.setMovie_no("45");
//		
//		
//		filmreview2.setContent("步步步");
//		filmreview2.setEvaluation(new Double(5.0));
//		filmreview2.setTitle("movie1");
//		filmreview2.setSource("ez");
//		filmreview2.setUrl("www.");
//		filmreview2.setMem_no("M001");
//		filmreview2.setAuthor("610");
//		dao.update(filmreview2);
//		
//		System.out.println("OK");
	
//      刪除		
//		
//		dao.delete("F0001");
//		System.out.println("刪除成功");
		
//      選一個
		
//		FilmreviewVO filmreview3 = dao.findByPrimaryKey("F00003");
//		
//		System.out.println(filmreview3.getFr_no()+" , ");
//		System.out.println(filmreview3.getMovie_no()+" , ");
//		System.out.println(filmreview3.getCreated_at()+" , ");
//		System.out.println(filmreview3.getUpdated_at()+" , ");
//		System.out.println(filmreview3.getContent()+" , ");
//		System.out.println(filmreview3.getEvaluation()+" , ");
//		System.out.println(filmreview3.getTitle()+" , ");
//		System.out.println(filmreview3.getSource()+" , ");
//		System.out.println(filmreview3.getUrl()+" , ");
//		System.out.println(filmreview3.getMem_no()+" , ");
//		System.out.println(filmreview3.getAuthor()+" , ");
//		
//		
		
		
//		全都要
		
//		List<FilmreviewVO> list = dao.getAll();
//		
//		for(FilmreviewVO fv : list) {
//			System.out.println(fv.getFr_no()+" , ");
//			System.out.println(fv.getMovie_no()+" , ");
//			System.out.println(fv.getCreated_at()+" , ");
//			System.out.println(fv.getUpdated_at()+" , ");
//			System.out.println(fv.getContent()+" , ");
//			System.out.println(fv.getEvaluation()+" , ");
//			System.out.println(fv.getTitle()+" , ");
//			System.out.println(fv.getSource()+" , ");
//			System.out.println(fv.getUrl()+" , ");
//			System.out.println(fv.getMem_no()+" , ");
//			System.out.println(fv.getAuthor()+" , ");
//		}
		
		
//		Set<FilmreviewVO> set = dao.getAllByMemNo("6");
//		
//		for(FilmreviewVO fv : set) {
//			System.out.println(fv.getFr_no()+" , ");
//			System.out.println(fv.getMovie_no()+" , ");
//			System.out.println(fv.getCreated_at()+" , ");
//			System.out.println(fv.getUpdated_at()+" , ");
//			System.out.println(fv.getContent()+" , ");
//			System.out.println(fv.getEvaluation()+" , ");
//			System.out.println(fv.getTitle()+" , ");
//			System.out.println(fv.getSource()+" , ");
//			System.out.println(fv.getUrl()+" , ");
//			System.out.println(fv.getMem_no()+" , ");
//			System.out.println(fv.getAuthor()+" , ");
//		}
////		
		
	}
	@Override
	public List<FilmreviewVO> findByMem(String mem_no) {
		List<FilmreviewVO> set = new ArrayList<FilmreviewVO>();
		FilmreviewVO filmreviewVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_STMT);
			pstmt.setString(1, mem_no);;
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreviewVO = new FilmreviewVO();
				filmreviewVO.setFr_no(rs.getString("fr_no"));
				filmreviewVO.setMovie_no(rs.getString("movie_no"));
				filmreviewVO.setCreated_at(rs.getDate("created_at"));
				filmreviewVO.setUpdated_at(rs.getDate("updated_at"));
				filmreviewVO.setContent(rs.getString("content"));
				filmreviewVO.setEvaluation(rs.getDouble("evaluation"));
				filmreviewVO.setTitle(rs.getString("title"));
				filmreviewVO.setSource(rs.getString("source"));
				filmreviewVO.setUrl(rs.getString("url"));
				filmreviewVO.setMem_no(rs.getString("mem_no"));
				filmreviewVO.setAuthor(rs.getString("author"));
				set.add(filmreviewVO);
				
			} 
				
				
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			//  Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return set;}
	
	
	
}
