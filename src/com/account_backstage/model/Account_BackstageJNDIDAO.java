package com.account_backstage.model;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.role_permission_bs.model.Role_Permission_BsJNDIDAO;
import com.role_permission_bs.model.Role_Permission_BsVO;

public class Account_BackstageJNDIDAO implements Account_BackstageDAO_interface{
	// 銝���蝔�葉,�������澈 ,��銝��ataSource��
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
				"INSERT INTO ACCOUNT_BACKSTAGE(BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE) VALUES (account_backstage_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
				"SELECT BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE FROM ACCOUNT_BACKSTAGE order by BS_ACC_NO";
		private static final String GET_ONE_STMT = 
				"SELECT BS_ACC_NO,BS_ACC_NAME,ROLE_NO,CINEMA_NO,BS_ACC_PSW,EMAIL,TEL,LAST_ONLINE_TIME,STATE FROM ACCOUNT_BACKSTAGE where BS_ACC_NO = ?";
		private static final String DELETE = 
				"DELETE FROM ACCOUNT_BACKSTAGE where BS_ACC_NO = ?";
		private static final String UPDATE = 
				"UPDATE ACCOUNT_BACKSTAGE set BS_ACC_NAME=?, ROLE_NO=?, CINEMA_NO=?, BS_ACC_PSW=?, EMAIL=?, TEL=? ,LAST_ONLINE_TIME=? ,STATE=? where BS_ACC_NO = ?";
		
		@Override
		public void insert(Account_BackstageVO account_BackstageVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
			}  catch (SQLException se) {
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

				con = ds.getConnection();
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, bs_acc_no);

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con = ds.getConnection();
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

				con = ds.getConnection();
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
		public void insertWithRole_Permission_Bs(Account_BackstageVO account_BackstageVO,
				List<Role_Permission_BsVO> list) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

								
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
				ResultSet rs1 = pstmt.getGeneratedKeys();
				if (rs1.next()) {
					next_bs_acc_no = rs1.getString(1);
					System.out.println("自增主鍵值= " + next_bs_acc_no +"(剛新增成功的部門編號)");
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs1.close();
				// 再同時新增角色編號
				Role_Permission_BsJNDIDAO dao = new Role_Permission_BsJNDIDAO();
				System.out.println("list.size()-A="+list.size());
				for (Role_Permission_BsVO aRole_Permission_Bs : list) {
					aRole_Permission_Bs.setPermission_no(next_bs_acc_no) ;
					dao.insert2(aRole_Permission_Bs, con);
				}

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("list.size()-B="+list.size());
				System.out.println("新增部門編號" + next_bs_acc_no + "時,共有員工" + list.size()
						+ "人同時被新增");
				
				// Handle any driver errors
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
			
		}
			

		
//	public static void main(String[] args) {
//
//		Account_BackstageJDBCDAO dao = new Account_BackstageJDBCDAO();
//
//		// �憓�
//		Account_BackstageVO account_BackstageVO1 = new Account_BackstageVO();
//		account_BackstageVO1.setBs_acc_name("�����");
//		account_BackstageVO1.setRole_no("敺蝟餌絞蝞∠�OSS");
//		account_BackstageVO1.setCinema_no("12");
//		account_BackstageVO1.setBs_acc_psw("123456");
//		account_BackstageVO1.setEmail("JOJO@gmail.com");
//		account_BackstageVO1.setTel("(02)1234-5678");
//		account_BackstageVO1.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO1.setState(1);
//		dao.insert(account_BackstageVO1);

		// 靽格
//		Account_BackstageVO account_BackstageVO2 = new Account_BackstageVO();
//		account_BackstageVO2.setBs_acc_no("1");
//		account_BackstageVO2.setBs_acc_name("��予雿�");
//		account_BackstageVO2.setRole_no("敺蝟餌絞蝞∠�OSS");
//		account_BackstageVO2.setCinema_no("12");
//		account_BackstageVO2.setBs_acc_psw("123456");
//		account_BackstageVO2.setEmail("JOJO@gmail.com");
//		account_BackstageVO2.setTel("(02)1234-5678");
//		account_BackstageVO2.setLast_online_time(new Timestamp(System.currentTimeMillis()));
//		account_BackstageVO2.setState(0);
//		dao.update(account_BackstageVO2);
	//
		// ��
//		dao.delete("7");

//		// �閰�
//		Account_BackstageVO account_BackstageVO3 = dao.findByPrimaryKey("8");
//		System.out.print(account_BackstageVO3.getBs_acc_no() + ",");
//		System.out.print(account_BackstageVO3.getBs_acc_name() + ",");
//		System.out.print(account_BackstageVO3.getRole_no() + ",");
//		System.out.print(account_BackstageVO3.getCinema_no() + ",");
//		System.out.print(account_BackstageVO3.getBs_acc_psw() + ",");
//		System.out.print(account_BackstageVO3.getEmail() + ",");
//		System.out.print(account_BackstageVO3.getTel() + ",");
//		System.out.print(account_BackstageVO3.getLast_online_time() + ",");
//		System.out.println(account_BackstageVO3.getState());
	//	
//		System.out.println("---------------------");
	//
//		// �閰�
//		List<Account_BackstageVO> list = dao.getAll();
//		for (Account_BackstageVO account_BackstageVO : list) {
//			System.out.print(account_BackstageVO.getBs_acc_no() + ",");
//			System.out.print(account_BackstageVO.getBs_acc_name() + ",");
//			System.out.print(account_BackstageVO.getRole_no() + ",");
//			System.out.print(account_BackstageVO.getCinema_no() + ",");
//			System.out.print(account_BackstageVO.getBs_acc_psw() + ",");
//			System.out.print(account_BackstageVO.getEmail() + ",");
//			System.out.print(account_BackstageVO.getTel() + ",");
//			System.out.print(account_BackstageVO.getLast_online_time() + ",");
//			System.out.println(account_BackstageVO.getState());
//			
//			System.out.println();
//		}
		
//	}	
		
//}
