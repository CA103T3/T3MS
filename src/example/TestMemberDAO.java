package example;

import java.sql.SQLException;

import com.member.model.MemVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMemberDAO {
	private static final String INSERT_STMT ="insert into MEMBER(mem_no,email,mem_pw,lname,fname,birthday,phone,IDNUM,gender,addr,locno,status,type,violation) values('M'||LPAD(to_char(member_seq.NEXTVAL), '3', '0'),?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?,?,?,?,?,0,0,0)";
	private static final String PASS_REGISTERED ="UPDATE MEMBER SET STATUS=? WHERE EMAIL=?";
	private static final String UPDATE = "UPDATE MEMBER SET lname=?,fname=?,phone=?,addr=?,locno=?,memimg=? WHERE EMAIL=?";
	public static void main(String[] args) throws IOException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "T3MS";
		String passwd = "123456";
		System.out.println("再跑啦幹");
		Connection con = null;
		PreparedStatement pstmt = null;	
			
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, "新垣");
			pstmt.setString(2, "結依");
			pstmt.setString(3, "111111111");
			pstmt.setString(4, "日本日本日本");
			pstmt.setInt(5, 123);
			              
			byte[] pic2 = getPictureByteArray("WebContent/img/kagi.jpg");
			pstmt.setBytes(6, pic2);
			pstmt.setString(7, "aaa@gmail.com");
			
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	

}
