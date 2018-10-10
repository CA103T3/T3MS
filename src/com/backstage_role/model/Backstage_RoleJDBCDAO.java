package com.backstage_role.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.account_backstage.model.Account_BackstageJDBCDAO;
import com.account_backstage.model.Account_BackstageVO;

public class Backstage_RoleJDBCDAO implements Backstage_RoleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Backstage_Role(br_NO,BR_NAME) VALUES (backstage_role_seq.NEXTVAL,?)";
	private static final String GET_ALL_STMT = "SELECT BR_NO,BR_NAME FROM Backstage_Role order by BR_NO";
	private static final String GET_ONE_STMT = "SELECT BR_NO,BR_NAME FROM Backstage_Role where BR_NO = ?";
	private static final String DELETE = "DELETE FROM Backstage_Role where BR_NO = ?";
	private static final String UPDATE = "UPDATE Backstage_Role set BR_NAME=? where BR_NO = ?";

	@Override
	public void insert(Backstage_RoleVO backstage_RoleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
System.out.println("ok");
			pstmt.setString(1, backstage_RoleVO.getBr_name());
System.out.println("ok");			
//						pstmt.setString(2, backstage_RoleVO.getRole_no());
//						pstmt.setString(3, backstage_RoleVO.getCinema_no());
//						pstmt.setString(4, backstage_RoleVO.getBs_acc_psw());
//						pstmt.setString(5, backstage_RoleVO.getEmail());
//						pstmt.setString(6, backstage_RoleVO.getTel());
//						pstmt.setTimestamp(7, backstage_RoleVO.getLast_online_time());
//						pstmt.setInt(8, backstage_RoleVO.getState());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(Backstage_RoleVO backstage_RoleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, backstage_RoleVO.getBr_no());
			pstmt.setString(2, backstage_RoleVO.getBr_name());
//						pstmt.setString(3, backstage_RoleVO.getCinema_no());
//						pstmt.setString(4, backstage_RoleVO.getBs_acc_psw());
//						pstmt.setString(5, backstage_RoleVO.getEmail());
//						pstmt.setString(6, backstage_RoleVO.getTel());
//						pstmt.setTimestamp(7, backstage_RoleVO.getLast_online_time());
//						pstmt.setInt(8, backstage_RoleVO.getState());
//						pstmt.setString(9, backstage_RoleVO.getBs_acc_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String br_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, br_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public Backstage_RoleVO findByPrimaryKey(String br_no) {

		Backstage_RoleVO backstage_RoleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, br_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVo 銋迂� Domain objects
				backstage_RoleVO = new Backstage_RoleVO();
				backstage_RoleVO.setBr_no(rs.getString("br_no"));
				backstage_RoleVO.setBr_name(rs.getString("br_name"));
//							backstage_RoleVO.setRole_no(rs.getString("role_no"));
//							backstage_RoleVO.setCinema_no(rs.getString("cinema_no"));
//							backstage_RoleVO.setBs_acc_psw(rs.getString("bs_acc_psw"));
//							backstage_RoleVO.setEmail(rs.getString("email"));
//							backstage_RoleVO.setTel(rs.getString("tel"));
//							backstage_RoleVO.setLast_online_time(rs.getTimestamp("last_online_time"));
//							backstage_RoleVO.setState(rs.getInt("state"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
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
		return backstage_RoleVO;
	}

	@Override
	public List<Backstage_RoleVO> getAll() {
		List<Backstage_RoleVO> list = new ArrayList<Backstage_RoleVO>();
		Backstage_RoleVO backstage_RoleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("OK");
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 銋迂� Domain objects
				backstage_RoleVO = new Backstage_RoleVO();
				backstage_RoleVO.setBr_no(rs.getString("br_no"));
				System.out.println("OK");
				backstage_RoleVO.setBr_name(rs.getString("br_name"));
				System.out.println("OK");
//							backstage_RoleVO.setRole_no(rs.getString("role_no"));
//							System.out.println("OK");
//							backstage_RoleVO.setCinema_no(rs.getString("cinema_no"));
//							System.out.println("OK");
//							backstage_RoleVO.setBs_acc_psw(rs.getString("bs_acc_psw"));
//							System.out.println("OK");
//							backstage_RoleVO.setEmail(rs.getString("email"));
//							System.out.println("OK");
//							backstage_RoleVO.setTel(rs.getString("tel"));
//							System.out.println("OK");
//							backstage_RoleVO.setLast_online_time(rs.getTimestamp("last_online_time"));
//							
//							backstage_RoleVO.setState(rs.getInt("state"));
				list.add(backstage_RoleVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
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
		return list;
	}

	public static void main(String[] args) {

		Backstage_RoleJDBCDAO dao = new Backstage_RoleJDBCDAO();

	// �憓�
//		Backstage_RoleVO backstage_RoleVO1 = new Backstage_RoleVO();
//		backstage_RoleVO1.setBr_name("���");
//
//		dao.insert(backstage_RoleVO1);

	// 靽格
//		Backstage_RoleVO backstage_RoleVO2 = new Backstage_RoleVO();
//		backstage_RoleVO2.setRole_no("1");
//		backstage_RoleVO2.setBr_name("敺瑞镼蹂��");
//			
//		dao.update(backstage_RoleVO2);

	// ��
//		dao.delete("2");

	// �閰�
//		Backstage_RoleVO backstage_RoleVO3 = dao.findByPrimaryKey("1");
//		System.out.print(backstage_RoleVO3.getRole_no() + ",");
//		System.out.println(backstage_RoleVO3.getBr_name());
//	
//
//		System.out.println("---------------------");
		
	// �閰�
		List<Backstage_RoleVO> list = dao.getAll();
		for (Backstage_RoleVO backstage_RoleVO : list) {
		System.out.print(backstage_RoleVO.getBr_no() + ",");
		System.out.println(backstage_RoleVO.getBr_name());
						
						
		System.out.println();
		}
	}
}
