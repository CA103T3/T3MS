package com.announcement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnnouncementTest {
	// * private String anc_no;
	// * private String anc_con;
	// * private String backstage_no;
	// * private Timestamp created_at;
	// * private Timestamp updated_at;
	// * private Integer active;
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static final String INSERT = "INSERT INTO ANNOUNCEMENT (ANC_NO,ANC_CON,BACKSTAGE_NO,CREATED_AT,UPDATED_AT,ACTIVE)"
			+ "VALUES('A'||LPAD(ANNOUNCEMENT_SEQ.NEXTVAL,4,'0'),?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
	private static final String UPDATE = "UPDATE ANNOUNCEMENT SET ANC_CON=? , UPDATED_AT=CURRENT_TIMESTAMP ,ACTIVE=? WHERE ANC_NO=?";
	private static final String DELETE = "DELETE FROM ANNOUNCEMENT WHERE ANC_NO=?";
	private static final String GETONE = "SELECT * FROM ANNOUNCEMENT WHERE ANC_NO=?";
	private static final String GETALL = "SELECT * FROM ANNOUNCEMENT";

	public static void main(String[] args) throws SQLException {
		AnnouncementVO annVO = new AnnouncementVO();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:XE", "T3MS", "123456");
			System.out.println("Connection OK");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(System.err);
		}

		// *********Test INSERT*********
		// =============================
		// INSERT INTO ANNOUNCEMENT
		// (ANC_NO,ANC_CON,BACKSTAGE_NO,CREATED_AT,UPDATED_AT,ACTIVE)
		// VALUES
		// ('A'||LPAD(ANNOUNCEMENT_SEQ.NEXTVAL,4,'0'),'3','3',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'1');
		// =============================
		//
		//
		// annVO.setAnc_con("測sssss試3333");
		// annVO.setBackstage_no("3");
		// annVO.setActive(0);
		// add(annVO);
		// *********Test INSERT*********

		// *********Test UPDATE*********
		// =============================
		// UPDATE ANNOUNCEMENT SET
		// ANC_CON='測試阿!!!',
		// UPDATED_AT=CURRENT_TIMESTAMP,
		// ACTIVE=2
		// WHERE ANC_NO='A0002';
		// =============================
		//
		//
		// annVO.setAnc_con("測試123！！");
		// annVO.setActive(9);
		// annVO.setAnc_no("A0007");
		// update(annVO);
		// *********Test UPDATE*********

		// *********Test DELETE*********
		// =============================
		// DELETE FROM ANNOUNCEMENT WHERE ANC_NO='A0007';
		// =============================
		// delete("A0006");
		// *********Test DELETE*********

		// *********Test findByPK*********
		// =============================
		// SELECT * FROM ANNOUNCEMENT WHERE ANC_NO='A0003';
		// =============================
		// annVO = findByPK("A0008");
		// System.out.println(annVO.getAnc_no());
		// System.out.println(annVO.getAnc_con());
		// System.out.println(annVO.getBackstage_no());
		// System.out.println(annVO.getCreated_at());
		// System.out.println(annVO.getUpdated_at());
		// System.out.println(annVO.getActive());
		// *********Test findByPK*********

		// *********Test GETALL*********
		// =============================
		// SELECT * FROM ANNOUNCEMENT
		// =============================
		List<AnnouncementVO> list = getAll();
		Iterator<AnnouncementVO> lIterator = list.iterator();
		while (lIterator.hasNext()) {
			annVO = (AnnouncementVO) lIterator.next();
			System.out.println(annVO.getAnc_no());
			System.out.println(annVO.getAnc_con());
			System.out.println(annVO.getBackstage_no());
			System.out.println(annVO.getCreated_at());
			System.out.println(annVO.getUpdated_at());
			System.out.println(annVO.getActive());
		}
		// *********Test GETALL*********
		System.out.println("here!");
	}

	public static void add(AnnouncementVO ann) {

		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, ann.getAnc_con());
			pstmt.setString(2, ann.getBackstage_no());
			pstmt.setInt(3, ann.getActive());

			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace(System.err);
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

	public static void update(AnnouncementVO ann) {
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, ann.getAnc_con());
			pstmt.setInt(2, ann.getActive());
			pstmt.setString(3, ann.getAnc_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
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

	public static void delete(String annId) {
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, annId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public static AnnouncementVO findByPK(String annId) {
		AnnouncementVO annVO = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(GETONE);
			pstmt.setString(1, annId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				annVO = new AnnouncementVO();
				annVO.setAnc_no(rs.getString(1));
				annVO.setAnc_con(rs.getString(2));
				annVO.setBackstage_no(rs.getString(3));
				annVO.setCreated_at(rs.getTimestamp(4));
				annVO.setUpdated_at(rs.getTimestamp(5));
				annVO.setActive(rs.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return annVO;
	}

	public static List<AnnouncementVO> getAll() {
		AnnouncementVO annVO = null;
		ResultSet rs = null;
		List<AnnouncementVO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				annVO = new AnnouncementVO();
				annVO.setAnc_no(rs.getString(1));
				annVO.setAnc_con(rs.getString(2));
				annVO.setBackstage_no(rs.getString(3));
				annVO.setCreated_at(rs.getTimestamp(4));
				annVO.setUpdated_at(rs.getTimestamp(5));
				annVO.setActive(rs.getInt(6));
				list.add(annVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return list;
	}
}
