package com.filmreview.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class FilmreviewDAO implements FilmreviewDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO Filmreview (FR_NO,MOVIE_NO,CREATED_AT,UPDATED_AT,CONTENT,EVALUATION,TITLE,SOURCE,URL,MEM_NO,AUTHOR) VALUES ('F'||LPAD(to_char(FILMREVIEW_SEQ.NEXTVAL), 5, '0'), ?, current_timestamp, current_timestamp, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Filmreview order by FR_NO";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM Filmreview where FR_NO = ?";
	private static final String DELETE = 
			"DELETE FROM Filmreview where FR_NO = ?";
	private static final String UPDATE = 
			"UPDATE Filmreview set MOVIE_NO=?, UPDATED_AT=current_timestamp, CONTENT=?, EVALUATION=?, TITLE=?, SOURCE=?, URL=?, MEM_NO=?, AUTHOR=?, ACTIVE=? where FR_NO = ?";
	
	@Override
	public void insert(FilmreviewVO filmreviewVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, fr_no);
			
			pstmt.executeQuery();
			
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
			
			con = ds.getConnection();
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
				
				
		}catch (SQLException se) {
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
			
			con = ds.getConnection();
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
				
				
		}catch (SQLException se) {
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
	
	
	
	
	
		
	}