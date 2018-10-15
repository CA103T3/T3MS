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

import com.nav_backstage.model.NavVO;



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
	private static final String GETONES =
			"SELECT PERMISSION_NO FROM ROLE_PERMISSION_BACKSTAGE WHERE ROLE_NO=?";

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
	
	@Override
	public List<String> getOnesOwn(String role_no) {
		List<String> list = new ArrayList<String>();
        

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GETONES);
            pstmt.setString(1, role_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("PERMISSION_NO")); // Store the row in the list
            }

            // Handle any driver errors
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
	

	
	


}
