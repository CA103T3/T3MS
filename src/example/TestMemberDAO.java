package example;

import java.sql.SQLException;

import com.member.model.MemVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMemberDAO {
	private static final String INSERT_STMT ="insert into MEMBER(mem_no,email,mem_pw,lname,fname,birthday,phone,IDNUM,gender,addr,locno,status,type,violation) values('M'||LPAD(to_char(member_seq.NEXTVAL), '3', '0'),?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?,?,?,?,?,0,0,0)";
	private static final String PASS_REGISTERED ="UPDATE MEMBER SET STATUS=? WHERE EMAIL=?";
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "CA103";
		String passwd = "123456";
		System.out.println("再跑啦");
		Connection con = null;
		PreparedStatement pstmt = null;	
			
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(PASS_REGISTERED);
			
			pstmt.setInt(1, 0);
			pstmt.setString(2, "andy83839408@gmail.com");
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
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
	

}
