package com.announcement.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sun.webkit.dom.WheelEventImpl;

import sun.print.resources.serviceui;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AnnouncementJNDIDAO implements AnnouncementDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private String anc_no;
	// private String anc_con;
	// private String backstage_no;
	// private Timestamp created_at;
	// private Timestamp updated_at;
	// private Integer active;

	private static final String INSERT = "INSERT INTO ANNOUNCEMENT (ANC_NO,ANC_CON,BACKSTAGE_NO,CREATED_AT,UPDATED_AT,ACTIVE)"
			+ "VALUES ('A'||LPAD(ANNOUNCEMENT_SEQ.NEXTVAL,4,'0'),?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0)";
	private static final String UPDATE = "UPDATE ANNOUNCEMENT SET " + "ANC_CON=?," + "UPDATED_AT=CURRENT_TIMESTAMP," + "ACTIVE=? "
			+ "WHERE ANC_NO=?";
	private static final String DELETE = "DELETE FROM ANNOUNCEMENT WHERE ANC_NO=?";
	private static final String GET_ALL = "SELECT * FROM ANNOUNCEMENT ORDER BY ANC_NO";
	private static final String GET_ONE = "SELECT * FROM ANNOUNCEMENT WHERE ANC_NO=?";

	@Override
	public void insert(AnnouncementVO newanc) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);

			pstmt.setString(1, newanc.getAnc_con());
			pstmt.setString(2, newanc.getBackstage_no());
			// pstmt.setInt(3, newanc.getActive());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(AnnouncementVO ancmod) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, ancmod.getAnc_con());
			pstmt.setInt(2, ancmod.getActive());
			pstmt.setString(3, ancmod.getAnc_no());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String anc_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setString(1, anc_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occureed. " + e.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public AnnouncementVO findByPrimaryKey(String anc_no) {
		AnnouncementVO announcementVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE);
			pstmt.setString(1, anc_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAnc_no(rs.getString("anc_no"));
				announcementVO.setAnc_con(rs.getString("anc_con"));
				announcementVO.setBackstage_no(rs.getString("backstage_no"));
				announcementVO.setCreated_at(rs.getTimestamp("created_at"));
				announcementVO.setUpdated_at(rs.getTimestamp("updated_at"));
				announcementVO.setActive(rs.getInt("active"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occureed. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

		return announcementVO;
	}

	@Override
	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> list = new ArrayList<>();
		AnnouncementVO announcementVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				announcementVO = new AnnouncementVO();
				announcementVO.setAnc_no(rs.getString("anc_no"));
				announcementVO.setAnc_con(rs.getString("anc_con"));
				announcementVO.setBackstage_no(rs.getString("backstage_no"));
				announcementVO.setCreated_at(rs.getTimestamp("created_at"));
				announcementVO.setUpdated_at(rs.getTimestamp("updated_at"));
				announcementVO.setActive(rs.getInt("active"));
				list.add(announcementVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occureed. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
