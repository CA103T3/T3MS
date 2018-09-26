package com.activity.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ActivityJNDIDAO implements ActivityDAO_interface {

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

	// private String activity_no;
	// private String activity_name;
	// private String activity_desc;
	// private String backstage_no;
	// private Timestamp created_at;
	// private Timestamp updated_at;
	// private Integer active;
	// private byte[] img_path;
	// private String activity_url;

	private static final String INSERT = "INSERT INTO ACTIVITY (ACTIVITY_NO,ACTIVITY_NAME,ACTIVITY_DESC,BACKSTAGE_NO,CREATED_AT,UPDATED_AT,ACTIVE,IMG_PATH,ACTIVITY_URL)"
			+ "VALUES ('AC'||LPAD(ACTIVITY_SEQ.NEXTVAL,4,'0'),?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?)";
	private static final String UPDATE = "UPDATE ACTIVITY SET ACTIVITY_NAME=?, ACTIVITY_DESC=?,BACKSTAGE_NO=?,UPDATED_AT=CURRENT_TIMESTAMP,ACTIVE=?,IMG_PATH=?,ACTIVITY_URL=? "
			+ "WHERE ACTIVITY_NO=?";
	private static final String DELETE = "DELETE FROM ACTIVITY WHERE ACTIVITY_NO=?";
	private static final String GET_ONE_STMT = "SELECT * FROM ACTIVITY WHERE ACTIVITY_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM ACTIVITY ORDER BY ACTIVITY_NO";

	public void insert(ActivityVO actVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);

			pstmt.setString(1, actVO.getActivity_name());
			pstmt.setString(2, actVO.getActivity_desc());
			pstmt.setString(3, actVO.getBackstage_no());
			pstmt.setInt(4, actVO.getActive());
			pstmt.setBytes(5, actVO.getImg_path());
			pstmt.setString(6, actVO.getActivity_url());

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// UPDATE ACTIVITY SET
	// ACTIVITY_NAME=?,
	// ACTIVITY_DESC=?,
	// BACKSTAGE_NO=?,
	// UPDATED_AT=CURRENT_TIMESTAMP,
	// ACTIVE=?,
	// IMG_PATH=?,
	// ACTIVITY_URL=? "
	// WHERE ACTIVITY_NO=?";
	@Override
	public void update(ActivityVO actVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, actVO.getActivity_name());
			pstmt.setString(2, actVO.getActivity_desc());
			pstmt.setString(3, actVO.getBackstage_no());
			pstmt.setInt(4, actVO.getActive());
			pstmt.setBytes(5, actVO.getImg_path());
			pstmt.setString(6, actVO.getActivity_url());
			pstmt.setString(7, actVO.getActivity_no());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
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
	public void delete(String activity_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setString(1, activity_no);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
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
	public ActivityVO findByPrimaryKey(String activity_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityVO actVO = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, activity_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActivity_no(rs.getString("activity_no"));
				actVO.setActivity_name(rs.getString("activity_name"));
				actVO.setActivity_desc(rs.getString("activity_desc"));
				actVO.setBackstage_no(rs.getString("backstage_no"));
				actVO.setCreated_at(rs.getTimestamp("created_at"));
				actVO.setUpdated_at(rs.getTimestamp("updated_at"));
				actVO.setActive(rs.getInt("active"));
				actVO.setImg_path(rs.getBytes("img_path"));
				actVO.setActivity_url(rs.getString("activity_url"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
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
		return actVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list =new ArrayList<>();
		ActivityVO actVO =null;
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActivity_no(rs.getString("activity_no"));
				actVO.setActivity_name(rs.getString("activity_name"));
				actVO.setActivity_desc(rs.getString("activity_desc"));
				actVO.setBackstage_no(rs.getString("backstage_no"));
				actVO.setCreated_at(rs.getTimestamp("created_at"));
				actVO.setUpdated_at(rs.getTimestamp("updated_at"));
				actVO.setActive(rs.getInt("active"));
				actVO.setImg_path(rs.getBytes("img_path"));
				actVO.setActivity_url(rs.getString("activity_url"));
				list.add(actVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. "+e.getMessage());
		}finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
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
