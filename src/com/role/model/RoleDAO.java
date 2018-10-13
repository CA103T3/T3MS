package com.role.model;

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

import com.movie.model.MovieVO;



public class RoleDAO implements RoleDAO_interface{

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
			"INSERT INTO BACKSTAGE_ROLE(BR_NO,BR_NAME) VALUES ('R'||LPAD(to_char(BACKSTAGE_ROLE_SEQ.NEXTVAL), 3, '0'),?)";
	
	private static final String GET_ONE_STMT ="SELECT * FROM BACKSTAGE_ROLE WHERE BR_NAME=?";
	
	
	
	
	@Override
	public void insert(RoleVO roleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, roleVO.getBr_name());
			
			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(RoleVO roleVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String permission__no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleVO findnoByname(String br_name) {
		RoleVO roleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, br_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MovieVo 也稱為 Domain objects
				roleVO = new RoleVO();
				roleVO.setBr_no(rs.getString("br_no"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return roleVO;
	
	}

	@Override
	public List<RoleVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleVO findByPrimaryKey(String permission_no) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
