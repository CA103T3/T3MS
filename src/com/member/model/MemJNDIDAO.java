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
	
	private static final String INSERT_STMT ="insert into MEMBER(mem_no,email,mem_pw,lname,fname,birthday,phone,IDNUM,gender,addr,locno,status,type,violation) values('M'||LPAD(member_seq.NEXTVAL, '3', '0'),?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?,?,?,?,?,0,0,0)";
	private static final String GET_ALL_STMT = "SELECT * FROM member";
	private static final String GET_ONE_STMT = "SELECT * FROM member where email = ?";
	private static final String GET_ONE_STMT_MEMNO = "SELECT * FROM member where mem_no = ?";
	private static final String PASS_REGISTERED ="UPDATE MEMBER SET STATUS=? WHERE EMAIL=?";
	private static final String UPDATE = "UPDATE MEMBER SET lname=?,fname=?,phone=?,addr=?,locno=?,memimg=? WHERE EMAIL=?";
	private static final String CHANGEPASSWORD = "UPDATE MEMBER SET MEM_PW=? WHERE EMAIL=?";
	private static final String BAN ="UPDATE MEMBER SET STATUS=? WHERE MEM_NO=?";
	
	
	@Override
	public void insert(MemVO memVO) {		
		Connection con = null;
		PreparedStatement pstmt = null;		

		try {

			con = ds.getConnection(); 
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,memVO.getEmail());
			pstmt.setString(2,memVO.getPaw());
			pstmt.setString(3,memVO.getLastname());
			pstmt.setString(4,memVO.getFirstname());
			pstmt.setString(5,memVO.getBirthday());
			pstmt.setString(6,memVO.getPhone());
			pstmt.setString(7,memVO.getIDNUM());
			pstmt.setInt(8,memVO.getGender());
			pstmt.setString(9, memVO.getAddr());
			pstmt.setInt(10, memVO.getLocno());
			
			pstmt.executeUpdate();
			

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
	public boolean check(MemVO memVO) {		
		Connection con = null;		
		String CHECK = "select * from MEMBER where EMAIL='"+memVO.getEmail()+"'";
		
		try {
			
			con = ds.getConnection(); 		
			Statement stm = con.createStatement();
	        ResultSet rs = stm.executeQuery(CHECK);
	        
	        if(!rs.next()){
	        	return true;
			}else {
		    	System.out.println("帳號重複");
		    	return false;
		    }   
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}
	
	@Override
	public boolean isuserlogin(String email,String paw) {
		Connection con=null;
		boolean isValid = false;
		 
        String sql="select * from MEMBER where EMAIL='"+email+"' and mem_pw='"+paw+"'";
        try{
        	con = ds.getConnection(); 
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
 
            if(rs.next()){
                isValid = true;
            }
            rs.close();
            stm.close();
            
       
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
        if(isValid){
            return true;
        }
        else return false;
    }
		
	
	
	
	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			//lname=?,fname=?,phone=?,addr=?,locno=?,memimg=? WHERE EMAIL=?
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
	public boolean forgotcheck(String email,String idnum) {
		Connection con=null;
		boolean isValid = false;
		 
        String sql="select * from MEMBER where EMAIL='"+email+"' and IDNUM='"+idnum+"'";
        try{
        	con = ds.getConnection(); 
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
 
            if(rs.next()){
                isValid = true;
            }
            rs.close();
            stm.close();
            
       
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
        if(isValid){
            return true;
        }
        else return false;
    }
	
	@Override
	public void changepassword(String paw,String email) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {

			con = ds.getConnection(); 
			pstmt = con.prepareStatement(CHANGEPASSWORD);
			pstmt.setString(1, paw);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
	
	//後台--封鎖與解封
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
	
	

}
