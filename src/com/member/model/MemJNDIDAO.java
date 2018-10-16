package com.member.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.theater.model.TheaterVO;

public class MemJNDIDAO implements MemDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into MEMBER(mem_no,email,mem_pw,lname,fname,birthday,phone,IDNUM,gender,addr,locno,status,type,violation) values('M'||LPAD(to_char(member_seq.NEXTVAL), 3, '0'),?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?,?,?,?,?,0,0,0)";
	private static final String GET_ALL_STMT = "SELECT * FROM member";
	private static final String GET_ONE_STMT = "SELECT * FROM member where email = ?";
	private static final String GET_ONE_STMT_MEMNO = "SELECT * FROM member where mem_no = ?";
	private static final String PASS_REGISTERED = "UPDATE MEMBER SET STATUS=? WHERE EMAIL=?";
	private static final String UPDATE = "UPDATE MEMBER SET lname=?,fname=?,phone=?,addr=?,locno=?,memimg=? WHERE EMAIL=?";
	private static final String CHANGEPASSWORD = "UPDATE MEMBER SET MEM_PW=? WHERE EMAIL=?";
	private static final String BAN = "UPDATE MEMBER SET STATUS=? WHERE MEM_NO=?";
	private static final String FILM_CRITICISM = "UPDATE MEMBER SET TYPE=? WHERE MEM_NO=?";
	private static final String CHECK = "select * from MEMBER where IDNUM=?";
	private static final String INSERT_RETURN = "insert into MEMBER(mem_no,email,mem_pw,lname,fname,birthday,phone,IDNUM,gender,addr,locno,memimg,status,type,violation) values('M'||LPAD(to_char(member_seq.NEXTVAL), 3, '0'),?,?,?,?,TO_DATE(substr(?,0,19),'YYYY-MM-DD   HH24:MI:SS'),?,?,?,?,?,?,?,?,?)";
	private static final String FOULCHECK = "SELECT violation FROM MEMBER WHERE MEM_NO=?";
	private static final String FOUL = "UPDATE MEMBER SET violation=? WHERE MEM_NO=?";
	private static final String MEM_TICKET_SEARCH_STMT = "SELECT MOVIE.MOVIE_NAME,MOVIE.MOVIE_NO,TICKET_ORDER.UUID,MOVIE_SESSION.SESSION_NO,MOVIE_SESSION.SESSION_TIME,TICKETTYPE.PRICE,TICKET_ORDER.AMOUNT "
			+ "FROM TICKET_ORDER " + "LEFT JOIN TICKET_DETAIL " + "ON TICKET_ORDER.ORDER_NO = TICKET_DETAIL.ORDER_NO "
			+ "LEFT JOIN MOVIE_SESSION  " + "ON MOVIE_SESSION.SESSION_NO = TICKET_DETAIL.SESSION_NO "
			+ "LEFT JOIN MOVIE " + "ON MOVIE.MOVIE_NO = MOVIE_SESSION.MOVIE_NO " + "LEFT JOIN TICKETTYPE "
			+ "ON TICKETTYPE.TICKETTYPE_NO = TICKET_DETAIL.TICKETTYPE_NO " + "WHERE TICKET_ORDER.MEM_NO=?";

	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getEmail());
			pstmt.setString(2, memVO.getPaw());
			pstmt.setString(3, memVO.getLastname());
			pstmt.setString(4, memVO.getFirstname());
			pstmt.setString(5, memVO.getBirthday());
			pstmt.setString(6, memVO.getPhone());
			pstmt.setString(7, memVO.getIDNUM());
			pstmt.setInt(8, memVO.getGender());
			pstmt.setString(9, memVO.getAddr());
			pstmt.setInt(10, memVO.getLocno());

			pstmt.executeUpdate();

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
	public boolean check(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				return true;
			} else {
				System.out.println("帳號重複");
				return false;
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}

	@Override
	public boolean checkID(String IDNUM) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK);

			pstmt.setString(1, IDNUM);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				return true;
			} else {
				System.out.println("身分證重複");
				return false;
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}

	@Override
	public boolean isuserlogin(String email, String paw) {
		Connection con = null;
		boolean isValid = false;

		String sql = "select * from MEMBER where EMAIL='" + email + "' and mem_pw='" + paw + "'";
		try {
			con = ds.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			if (rs.next()) {
				isValid = true;
			}
			rs.close();
			stm.close();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (isValid) {
			return true;
		} else
			return false;
	}

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			// lname=?,fname=?,phone=?,addr=?,locno=?,memimg=? WHERE EMAIL=?
			pstmt.setString(1, memVO.getLastname());
			pstmt.setString(2, memVO.getFirstname());
			pstmt.setString(3, memVO.getPhone());
			pstmt.setString(4, memVO.getAddr());
			pstmt.setInt(5, memVO.getLocno());
			pstmt.setBytes(6, memVO.getMemimg());
			pstmt.setString(7, memVO.getEmail());

			pstmt.executeUpdate();
			System.out.println("修改成功");

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
	public void delete(String memno) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemVO findByemail(String email) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setFirstname(rs.getString("FNAME"));
				memVO.setLastname(rs.getString("LNAME"));
				memVO.setBirthday(rs.getString("BIRTHDAY"));
				memVO.setIDNUM(rs.getString("IDNUM"));
				memVO.setGender(rs.getInt("gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setmemno(rs.getString("MEM_NO"));
				memVO.setEmail(rs.getString("EMAIL"));
				memVO.setAddr(rs.getString("ADDR"));
				memVO.setLocno(rs.getInt("LOCNO"));
				memVO.setStatus(rs.getInt("STATUS"));
				memVO.setType(rs.getInt("TYPE"));
				memVO.setViolation(rs.getInt("VIOLATION"));
				memVO.setMemimg(rs.getBytes("MEMIMG"));
				memVO.setExtname(rs.getString("EXTNAME"));
				memVO.setIntroduction(rs.getString("INTRODUCTION"));

			}

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
		return memVO;
	}

	@Override
	public MemVO findBymemno(String memno) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMNO);

			pstmt.setString(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setFirstname(rs.getString("FNAME"));
				memVO.setLastname(rs.getString("LNAME"));
				memVO.setBirthday(rs.getString("BIRTHDAY"));
				memVO.setIDNUM(rs.getString("IDNUM"));
				memVO.setGender(rs.getInt("gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setmemno(rs.getString("MEM_NO"));
				memVO.setEmail(rs.getString("EMAIL"));
				memVO.setAddr(rs.getString("ADDR"));
				memVO.setLocno(rs.getInt("LOCNO"));
				memVO.setStatus(rs.getInt("STATUS"));
				memVO.setType(rs.getInt("TYPE"));
				memVO.setViolation(rs.getInt("VIOLATION"));
				memVO.setMemimg(rs.getBytes("MEMIMG"));
				memVO.setExtname(rs.getString("EXTNAME"));
				memVO.setIntroduction(rs.getString("INTRODUCTION"));

			}

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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// theaterVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemno(rs.getString("MEM_NO"));
				memVO.setPaw(rs.getString("MEM_PW"));
				memVO.setFirstname(rs.getString("FNAME"));
				memVO.setLastname(rs.getString("LNAME"));
				memVO.setBirthday(rs.getString("BIRTHDAY"));
				memVO.setIDNUM(rs.getString("IDNUM"));
				memVO.setGender(rs.getInt("gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setEmail(rs.getString("EMAIL"));
				memVO.setAddr(rs.getString("ADDR"));
				memVO.setLocno(rs.getInt("LOCNO"));
				memVO.setStatus(rs.getInt("STATUS"));
				memVO.setType(rs.getInt("TYPE"));
				memVO.setViolation(rs.getInt("VIOLATION"));
				memVO.setMemimg(rs.getBytes("MEMIMG"));
				memVO.setExtname(rs.getString("EXTNAME"));
				memVO.setIntroduction(rs.getString("INTRODUCTION"));
				list.add(memVO); // Store the row in the list
			}

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

	@Override
	public void passregistered(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(PASS_REGISTERED);
			pstmt.setInt(1, 1);
			pstmt.setString(2, email);
			pstmt.executeUpdate();

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
	public boolean forgotcheck(String email, String idnum) {
		Connection con = null;
		boolean isValid = false;

		String sql = "select * from MEMBER where EMAIL='" + email + "' and IDNUM='" + idnum + "'";
		try {
			con = ds.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			if (rs.next()) {
				isValid = true;
			}
			rs.close();
			stm.close();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (isValid) {
			return true;
		} else
			return false;
	}

	@Override
	public void changepassword(String paw, String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGEPASSWORD);
			pstmt.setString(1, paw);
			pstmt.setString(2, email);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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

	// 後台--封鎖與解封
	@Override
	public void banmember(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BAN);
			pstmt.setInt(1, 2);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();

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
	public void unbanmember(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BAN);
			pstmt.setInt(1, 1);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();

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
	public void wanttobeFC(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FILM_CRITICISM);
			pstmt.setInt(1, 1);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();

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
	public void betheFC(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FILM_CRITICISM);
			pstmt.setInt(1, 2);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();

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
	public String InsertReturnNO(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String[] cols = { "MEM_NO" }; // 或 int cols[] = {1};
		String memno = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_RETURN, cols);

			pstmt.setString(1, memVO.getEmail());
			pstmt.setString(2, memVO.getPaw());
			pstmt.setString(3, memVO.getLastname());
			pstmt.setString(4, memVO.getFirstname());
			pstmt.setString(5, memVO.getBirthday());
			pstmt.setString(6, memVO.getPhone());
			pstmt.setString(7, memVO.getIDNUM());
			pstmt.setInt(8, memVO.getGender());
			pstmt.setString(9, memVO.getAddr());
			pstmt.setInt(10, memVO.getLocno());
			pstmt.setBytes(11, memVO.getMemimg());
			pstmt.setInt(12, memVO.getStatus());
			pstmt.setInt(13, memVO.getType());
			pstmt.setInt(14, memVO.getViolation());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						memno = rs.getString(i);
						// System.out.println("自增主鍵值 = " + memno);
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}

			rs.close();

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
		return memno;
	}

	@Override
	public void foul(String memno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt0 = null;
		ResultSet rs = null;
		int vio;
		try {

			con = ds.getConnection();
			pstmt0 = con.prepareStatement(FOULCHECK);
			pstmt0.setString(1, memno);
			rs = pstmt0.executeQuery();
			rs.next();
			vio = rs.getInt("violation");

			vio++;
			pstmt = con.prepareStatement(FOUL);
			pstmt.setInt(1, vio);
			pstmt.setString(2, memno);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt0.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public Set<Mem_Ticket_SearchVO> MEM_TICKET_SEARCH_STMT(String memno) {
		Set<Mem_Ticket_SearchVO> mem_TS_Set = new TreeSet<>();
		Mem_Ticket_SearchVO mTicket_SearchVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(MEM_TICKET_SEARCH_STMT);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mTicket_SearchVO = new Mem_Ticket_SearchVO();
				mTicket_SearchVO.setMovie_name(rs.getString("MOVIE_NAME"));
				mTicket_SearchVO.setMovie_no(rs.getString("MOVIE_NO"));
				mTicket_SearchVO.setUuid(rs.getString("UUID"));
				mTicket_SearchVO.setSession_no(rs.getString("SESSION_NO"));
				mTicket_SearchVO.setSession_time(rs.getTimestamp("SESSION_TIME"));
				mTicket_SearchVO.setPrice(rs.getInt("PRICE"));
				mTicket_SearchVO.setAmount(rs.getInt("AMOUNT"));
				mem_TS_Set.add(mTicket_SearchVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return mem_TS_Set;
	}

}
