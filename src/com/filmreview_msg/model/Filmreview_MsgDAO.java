package com.filmreview_msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.filmreview.model.FilmreviewVO;

public class Filmreview_MsgDAO implements Filmreview_MsgDAO_interface{
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
			"INSERT INTO FILMREVIEW_MSG (FRM_NO, FR_NO, MEM_NO, CREATED_AT, UPDATED_AT, CONTENT) VALUES ('FM'||LPAD(to_char(FILMREVIEW_MSG_SEQ.NEXTVAL), 4, '0'), ?, ?, current_timestamp, current_timestamp, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM FILMREVIEW_MSG order by UPDATED_AT DESC";
	private static final String DELETE = 
			"DELETE FROM FILMREVIEW_MSG where FRM_NO = ?";
	private static final String GET_ALL_FRM = 
			"SELECT * FROM FILMREVIEW_MSG where FR_NO = ? order by UPDATED_AT DESC";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM FILMREVIEW_MSG where FRM_NO = ?";
	
	
	@Override
	public void insert(Filmreview_MsgVO filmreview_msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, filmreview_msgVO.getFr_no());
			pstmt.setString(2, filmreview_msgVO.getMem_no());
			pstmt.setString(3, filmreview_msgVO.getContent());
			
			
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
	public List<Filmreview_MsgVO> getAll() {
		List<Filmreview_MsgVO> list = new ArrayList<Filmreview_MsgVO>();
		Filmreview_MsgVO filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreview_msgVO = new Filmreview_MsgVO();
				filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				filmreview_msgVO.setFr_no(rs.getString("fr_no"));
				filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				filmreview_msgVO.setCreated_at(rs.getDate("created_at"));
				filmreview_msgVO.setUpdated_at(rs.getDate("updated_at"));
				filmreview_msgVO.setContent(rs.getString("content"));
				
				list.add(filmreview_msgVO);
				
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

	@Override
	public void delete(String frm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, frm_no);
			
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
	public Set<Filmreview_MsgVO> getAllByFrNo(String fr_no) {
		Set<Filmreview_MsgVO> set = new LinkedHashSet<Filmreview_MsgVO>();
		Filmreview_MsgVO filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FRM);
			pstmt.setString(1, fr_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				filmreview_msgVO = new Filmreview_MsgVO();
				filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				filmreview_msgVO.setFr_no(rs.getString("fr_no"));
				filmreview_msgVO.setCreated_at(rs.getDate("created_at"));
				filmreview_msgVO.setUpdated_at(rs.getDate("updated_at"));
				filmreview_msgVO.setContent(rs.getString("content"));
				filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				set.add(filmreview_msgVO);
				
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
		return set;
	}
	
	
	public Filmreview_MsgVO getVO(String fr_no) {
		Filmreview_MsgVO filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fr_no);     
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				filmreview_msgVO = new Filmreview_MsgVO();
				filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				filmreview_msgVO.setFr_no(rs.getString("fr_no"));
				filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				filmreview_msgVO.setCreated_at(rs.getDate("created_at"));
				filmreview_msgVO.setUpdated_at(rs.getDate("updated_at"));
				filmreview_msgVO.setContent(rs.getString("content"));
				
				
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
		return filmreview_msgVO;
	}
	

}
