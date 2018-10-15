package com.permission.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			"INSERT INTO ROLE_PERMISSION_BACKSTAGE(RP_NO,PERMISSION_NO,ROLE_NO) VALUES ('RP'||LPAD(to_char(ROLE_PERMISSION_BACKSTAGE_SEQ.NEXTVAL), 4, '0'),?,?)";
	private static final String GETONES =
			"SELECT PERMISSION_NO FROM ROLE_PERMISSION_BACKSTAGE WHERE ROLE_NO=?";
	private static final String FBYPK =
			"SELECT * FROM ROLE_PERMISSION_BACKSTAGE WHERE RP_NO=?";
	private static final String GETALL =
			"SELECT * FROM ROLE_PERMISSION_BACKSTAGE";

	@Override
	public String insert(PermissionVO pVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String rp_no = null;
		try {
			
			con = ds.getConnection();
			String[] cols = { "rp_no" };
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, pVO.getPermission_no());
			pstmt.setString(2, pVO.getRole_no());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                    	rp_no = rs.getString(i);
                        //System.out.println("自增主鍵值 = " + theater_no);
                    }
                } while (rs.next());
            } else {
                System.out.println("NO KEYS WERE GENERATED.");
            }
            
            rs.close();
					
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
		return rp_no;
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
	public PermissionVO findByPrimaryKey(String rp_no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PermissionVO pVO = null;
        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(FBYPK);
            pstmt.setString(1, rp_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pVO.setPermission_no(rs.getString("PERMISSION_NO"));
                pVO.setRole_no(rs.getString("ROLE_NO"));
                pVO.setRp_no(rs.getString("RP_NO"));
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
        return pVO;
	}

	@Override
	public List<PermissionVO> getAll() {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO pVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GETALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pVO = new PermissionVO();
                pVO.setRp_no(rs.getString("rp_no"));
                pVO.setPermission_no(rs.getString("permission_no"));
                pVO.setRole_no(rs.getString("role_no"));


                list.add(pVO); // Store the row in the list
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
