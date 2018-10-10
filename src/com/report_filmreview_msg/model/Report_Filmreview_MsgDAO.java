package com.report_filmreview_msg.model;

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

public class Report_Filmreview_MsgDAO implements Report_Filmreview_MsgDAO_interface{
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
			"INSERT INTO Report_FILMREVIEW_MSG (RFM_NO, FRM_NO, MEM_NO, STATE, CONTENT) VALUES ('RFM'||LPAD(to_char(Report_FILMREVIEW_MSG_SEQ.NEXTVAL), 6, '0'), ?, ?, 1, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Report_FILMREVIEW_MSG order by RFM_NO DESC";
	private static final String DELETE = 
			"DELETE FROM Report_FILMREVIEW_MSG where RFM_NO = ?";
	private static final String GET_ONE = 
			"SELECT * FROM Report_FILMREVIEW_MSG where RFM_NO = ? ";
	private static final String GET_ALL_FRM = 
			"SELECT * FROM Report_FILMREVIEW_MSG where RFM_NO = ? ";
	private static final String UPDATE =
	        "UPDATE Report_FILMREVIEW_MSG set STATE=? where RFM_NO = ?";
	
	
	
	@Override
	public void insert(Report_Filmreview_MsgVO report_filmreview_msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, report_filmreview_msgVO.getFrm_no());
			pstmt.setString(2, report_filmreview_msgVO.getMem_no());
			pstmt.setString(3, report_filmreview_msgVO.getContent());
			
			
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
	public List<Report_Filmreview_MsgVO> getAll() {
		List<Report_Filmreview_MsgVO> list = new ArrayList<Report_Filmreview_MsgVO>();
		Report_Filmreview_MsgVO report_filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				report_filmreview_msgVO = new Report_Filmreview_MsgVO();
				report_filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				report_filmreview_msgVO.setRfm_no(rs.getString("rfm_no"));
				report_filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				report_filmreview_msgVO.setState(rs.getInt("state"));
				report_filmreview_msgVO.setContent(rs.getString("content"));
				
				list.add(report_filmreview_msgVO);
				
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
	public void delete(String rfm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, rfm_no);
			
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
	public Report_Filmreview_MsgVO getAllByFrmNo(String frm_no) {
	
		Report_Filmreview_MsgVO report_filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FRM);
			pstmt.setString(1, frm_no);;
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				report_filmreview_msgVO = new Report_Filmreview_MsgVO();
				report_filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				report_filmreview_msgVO.setRfm_no(rs.getString("rfm_no"));				
				report_filmreview_msgVO.setState(rs.getInt("state"));				
				report_filmreview_msgVO.setContent(rs.getString("content"));
				report_filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				
				
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
		return report_filmreview_msgVO;
	}

	@Override
	public Report_Filmreview_MsgVO getAllByRfmNo(String rfm_no) {
		Report_Filmreview_MsgVO report_filmreview_msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FRM);
			pstmt.setString(1, rfm_no);;
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				report_filmreview_msgVO = new Report_Filmreview_MsgVO();
				report_filmreview_msgVO.setRfm_no(rs.getString("rfm_no"));				
				report_filmreview_msgVO.setFrm_no(rs.getString("frm_no"));
				report_filmreview_msgVO.setState(rs.getInt("state"));				
				report_filmreview_msgVO.setContent(rs.getString("content"));
				report_filmreview_msgVO.setMem_no(rs.getString("mem_no"));
				
				
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
		return report_filmreview_msgVO;
	}

	@Override
	public void update(Report_Filmreview_MsgVO report_filmreview_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			report_filmreview_msgVO.getState();
			report_filmreview_msgVO.getRfm_no();
		
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

	


}
