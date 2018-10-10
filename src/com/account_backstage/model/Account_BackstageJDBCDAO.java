package com.account_backstage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.account_backstage.model.Account_BackstageDAO_interface;
import com.role_permission_bs.model.*;

import java.sql.Timestamp;


public class Account_BackstageJDBCDAO implements Account_BackstageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ACCOUNT_BACKSTAGE(BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE) VALUES ('AB'||LPAD(to_char(account_backstage_seq.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE FROM ACCOUNT_BACKSTAGE order by BS_ACC_NO";
	private static final String GET_ONE_STMT = 
		"SELECT BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE FROM ACCOUNT_BACKSTAGE where BS_ACC_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ACCOUNT_BACKSTAGE where BS_ACC_NO = ?";
	private static final String UPDATE = 
		"UPDATE ACCOUNT_BACKSTAGE set BS_ACC_NAME=?, ROLE_NO=?, CINEMA_NO=?, BS_ACC_PSW=?, EMAIL=?, TEL=? ,LAST_ONLINE_TIME=? ,STATE=? where BS_ACC_NO = ?";
	
	
	
	
	
	
	@Override
	public void insertWithRole_Permission_Bs(Account_BackstageVO account_BackstageVO, List<Role_Permission_BsVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先新增帳號編號
			String cols[] = {"BS_ACC_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, account_BackstageVO.getBs_acc_name());
			pstmt.setString(2, account_BackstageVO.getRole_no());
			pstmt.setString(3, account_BackstageVO.getCinema_no());
			pstmt.setString(4, account_BackstageVO.getBs_acc_psw());
			pstmt.setString(5, account_BackstageVO.getEmail());
			pstmt.setString(6, account_BackstageVO.getTel());
			pstmt.setTimestamp(7, account_BackstageVO.getLast_online_time());
			pstmt.setInt(8, account_BackstageVO.getState());
			
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_bs_acc_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_bs_acc_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_bs_acc_no +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增角色編號
			Role_Permission_BsJNDIDAO dao = new Role_Permission_BsJNDIDAO();
			System.out.println("list.size()-A="+list.size());
			for (Role_Permission_BsVO aRole_Permission_Bs : list) {
				aRole_Permission_Bs.setRole_no(next_bs_acc_no) ;
				dao.insert2(aRole_Permission_Bs, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_bs_acc_no + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public void insert(Account_BackstageVO account_BackstageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, account_BackstageVO.getBs_acc_name());
			pstmt.setString(2, account_BackstageVO.getRole_no());
			pstmt.setString(3, account_BackstageVO.getCinema_no());
			pstmt.setString(4, account_BackstageVO.getBs_acc_psw());
			pstmt.setString(5, account_BackstageVO.getEmail());
			pstmt.setString(6, account_BackstageVO.getTel());
			pstmt.setTimestamp(7, account_BackstageVO.getLast_online_time());
			pstmt.setInt(8, account_BackstageVO.getState());
			
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Account_BackstageVO account_BackstageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, account_BackstageVO.getBs_acc_name());
			pstmt.setString(2, account_BackstageVO.getRole_no());
			pstmt.setString(3, account_BackstageVO.getCinema_no());
			pstmt.setString(4, account_BackstageVO.getBs_acc_psw());
			pstmt.setString(5, account_BackstageVO.getEmail());
			pstmt.setString(6, account_BackstageVO.getTel());
			pstmt.setTimestamp(7, account_BackstageVO.getLast_online_time());
			pstmt.setInt(8, account_BackstageVO.getState());
			pstmt.setString(9, account_BackstageVO.getBs_acc_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String bs_acc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bs_acc_no);

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Account_BackstageVO findByPrimaryKey(String bs_acc_no) {

		Account_BackstageVO account_BackstageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bs_acc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVo 銋迂� Domain objects
				account_BackstageVO = new Account_BackstageVO();
				account_BackstageVO.setBs_acc_no(rs.getString("bs_acc_no"));
				account_BackstageVO.setBs_acc_name(rs.getString("bs_acc_name"));
				account_BackstageVO.setRole_no(rs.getString("role_no"));
				account_BackstageVO.setCinema_no(rs.getString("cinema_no"));
				account_BackstageVO.setBs_acc_psw(rs.getString("bs_acc_psw"));
				account_BackstageVO.setEmail(rs.getString("email"));
				account_BackstageVO.setTel(rs.getString("tel"));
				account_BackstageVO.setLast_online_time(rs.getTimestamp("last_online_time"));
				account_BackstageVO.setState(rs.getInt("state"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return account_BackstageVO;
	}

	@Override
	public List<Account_BackstageVO> getAll() {
		List<Account_BackstageVO> list = new ArrayList<Account_BackstageVO>();
		Account_BackstageVO account_BackstageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// actVO 銋迂� Domain objects
				account_BackstageVO = new Account_BackstageVO();
				account_BackstageVO.setBs_acc_no(rs.getString("bs_acc_no"));
				account_BackstageVO.setBs_acc_name(rs.getString("bs_acc_name"));
				account_BackstageVO.setRole_no(rs.getString("role_no"));
				account_BackstageVO.setCinema_no(rs.getString("cinema_no"));
				account_BackstageVO.setBs_acc_psw(rs.getString("bs_acc_psw"));
				account_BackstageVO.setEmail(rs.getString("email"));
				account_BackstageVO.setTel(rs.getString("tel"));
				account_BackstageVO.setLast_online_time(rs.getTimestamp("last_online_time"));
				account_BackstageVO.setState(rs.getInt("state"));
				list.add(account_BackstageVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		

	
public static void main(String[] args) {

	Account_BackstageJDBCDAO dao = new Account_BackstageJDBCDAO();

	// �憓�
//	Account_BackstageVO account_BackstageVO1 = new Account_BackstageVO();
//	account_BackstageVO1.setBs_acc_name("�����");
//	account_BackstageVO1.setRole_no("敺蝟餌絞蝞∠�OSS");
//	account_BackstageVO1.setCinema_no("12");
//	account_BackstageVO1.setBs_acc_psw("123456");
//	account_BackstageVO1.setEmail("JOJO@gmail.com");
//	account_BackstageVO1.setTel("(02)1234-5678");
//	account_BackstageVO1.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//	account_BackstageVO1.setState(1);
//	dao.insert(account_BackstageVO1);

	// 靽格
//	Account_BackstageVO account_BackstageVO2 = new Account_BackstageVO();
//	account_BackstageVO2.setBs_acc_no("1");
//	account_BackstageVO2.setBs_acc_name("��予雿�");
//	account_BackstageVO2.setRole_no("敺蝟餌絞蝞∠�OSS");
//	account_BackstageVO2.setCinema_no("12");
//	account_BackstageVO2.setBs_acc_psw("123456");
//	account_BackstageVO2.setEmail("JOJO@gmail.com");
//	account_BackstageVO2.setTel("(02)1234-5678");
//	account_BackstageVO2.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//	account_BackstageVO2.setState(0);
//	dao.update(account_BackstageVO2);
//
	// ��
//	dao.delete("7");

//	// �閰�
	Account_BackstageVO account_BackstageVO3 = dao.findByPrimaryKey("5");
	System.out.print(account_BackstageVO3.getBs_acc_no() + ",");
	System.out.print(account_BackstageVO3.getBs_acc_name() + ",");
	System.out.print(account_BackstageVO3.getRole_no() + ",");
	System.out.print(account_BackstageVO3.getCinema_no() + ",");
	System.out.print(account_BackstageVO3.getBs_acc_psw() + ",");
	System.out.print(account_BackstageVO3.getEmail() + ",");
	System.out.print(account_BackstageVO3.getTel() + ",");
	System.out.print(account_BackstageVO3.getLast_online_time() + ",");
	System.out.println(account_BackstageVO3.getState());
	
	System.out.println("---------------------");
//
//	// �閰�
//	List<Account_BackstageVO> list = dao.getAll();
//	for (Account_BackstageVO account_BackstageVO : list) {
//		System.out.print(account_BackstageVO.getBs_acc_no() + ",");
//		System.out.print(account_BackstageVO.getBs_acc_name() + ",");
//		System.out.print(account_BackstageVO.getRole_no() + ",");
//		System.out.print(account_BackstageVO.getCinema_no() + ",");
//		System.out.print(account_BackstageVO.getBs_acc_psw() + ",");
//		System.out.print(account_BackstageVO.getEmail() + ",");
//		System.out.print(account_BackstageVO.getTel() + ",");
//		System.out.print(account_BackstageVO.getLast_online_time() + ",");
//		System.out.println(account_BackstageVO.getState());
//		
//		System.out.println();
//	}
	
	
	Account_BackstageVO account_BackstageVO = new Account_BackstageVO();
	account_BackstageVO.setBs_acc_name("貝吉達");
	account_BackstageVO.setRole_no("10");
	account_BackstageVO.setCinema_no("10");
	account_BackstageVO.setBs_acc_psw("123");
	account_BackstageVO.setEmail("gig@youcom.com");
	account_BackstageVO.setTel("09554474144");
	account_BackstageVO.setLast_online_time(new Timestamp(System.currentTimeMillis()));
	account_BackstageVO.setState(1);
	
	List<Role_Permission_BsVO> testList = new ArrayList<Role_Permission_BsVO>(); // 準備置入員工數人
	Role_Permission_BsVO role_Permission_bsXX = new Role_Permission_BsVO();   // 員工POJO1
	role_Permission_bsXX.setRole_no("1");
//	role_Permission_bsXX.setBs_acc_no("10");
//	role_Permission_bsXX.setCinema_no("10");
//	role_Permission_bsXX.setBs_acc_psw("123");
//	role_Permission_bsXX.setEmail("gig@youcom.com");
//	role_Permission_bsXX.setTel("09554474144");
//	role_Permission_bsXX.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//	role_Permission_bsXX.setState(1);

	Role_Permission_BsVO role_Permission_bsYY = new Role_Permission_BsVO();    // 員工POJO2
	role_Permission_bsXX.setRole_no("2");
//	role_Permission_bsYY.setBs_acc_name("吳y");
//	role_Permission_bsYY.setRole_no("MANAGER");
//	role_Permission_bsYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//	role_Permission_bsYY.setSal(new Double(16000));
//	role_Permission_bsYY.setComm(new Double(160));

	testList.add(role_Permission_bsXX);
	testList.add(role_Permission_bsYY);
	
	dao.insertWithRole_Permission_Bs(account_BackstageVO , testList);
}

//@Override
//public void insertWithRole_Permission_Bs(Account_BackstageVO account_BackstageVO, List<Role_Permission_BsVO> list) {
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	
//	try {
//
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		
//		// 1●設定於 pstm.executeUpdate()之前
//		con.setAutoCommit(false);
//		
//		// 先新增部門
//		String cols[] = {"BS_ACC_NO"};
//		pstmt = con.prepareStatement(INSERT_STMT , cols);			
//		pstmt.setString(1, account_BackstageVO.getBs_acc_name());
//		pstmt.setString(2, account_BackstageVO.getRole_no());
//		pstmt.setString(3, account_BackstageVO.getCinema_no());
//		pstmt.setString(4, account_BackstageVO.getBs_acc_psw());
//		pstmt.setString(5, account_BackstageVO.getEmail());
//		pstmt.setString(6, account_BackstageVO.getTel());
//		pstmt.setTimestamp(7, account_BackstageVO.getLast_online_time());
//		pstmt.setInt(8, account_BackstageVO.getState());
//		
//		pstmt.executeUpdate();
//		//掘取對應的自增主鍵值
//		String next_bs_acc_no = null;
//		ResultSet rs = pstmt.getGeneratedKeys();
//		if (rs.next()) {
//			next_bs_acc_no = rs.getString(1);
//			System.out.println("自增主鍵值= " + next_bs_acc_no +"(剛新增成功的部門編號)");
//		} else {
//			System.out.println("未取得自增主鍵值");
//		}
//		rs.close();
//		// 再同時新增員工
//		Account_BackstageJDBCDAO dao = new Account_BackstageJDBCDAO();
//		System.out.println("list.size()-A="+list.size());
//		for (Account_BackstageVO aAccount_Backstage : list) {
//			aAccount_Backstage.setBs_acc_no(new String(next_bs_acc_no)) ;
//			dao.insert2(aAccount_Backstage,con);
//		}
//
//		// 2●設定於 pstm.executeUpdate()之後
//		con.commit();
//		con.setAutoCommit(true);
//		System.out.println("list.size()-B="+list.size());
//		System.out.println("新增部門編號" + next_bs_acc_no + "時,共有員工" + list.size()
//				+ "人同時被新增");
//		
//		// Handle any driver errors
//	} catch (ClassNotFoundException e) {
//		throw new RuntimeException("Couldn't load database driver. "
//				+ e.getMessage());
//		// Handle any SQL errors
//	} catch (SQLException se) {
//		if (con != null) {
//			try {
//				// 3●設定於當有exception發生時之catch區塊內
//				System.err.print("Transaction is being ");
//				System.err.println("rolled back-由-dept");
//				con.rollback();
//			} catch (SQLException excep) {
//				throw new RuntimeException("rollback error occured. "
//						+ excep.getMessage());
//			}
//		}
//		throw new RuntimeException("A database error occured. "
//				+ se.getMessage());
//		// Clean up JDBC resources
//	} finally {
//		if (pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if (con != null) {
//			try {
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//	
//		}
//
//
//	}
//  }
}	


