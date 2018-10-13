package com.permission.model;

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



public class PermissionDAO implements PermissionDAO_interface{

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
			"INSERT INTO ROLE_PERMISSION_BACKSTAGE(PERMISSION_NO,ROLE_NO) VALUES (?,?)";

	@Override
	public void insert(PermissionVO pVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pVO.getPermission_no());
			pstmt.setString(2, pVO.getRole_no());
			
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
	public void update(PermissionVO roleVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String permission__no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PermissionVO findByPrimaryKey(String permission_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermissionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
