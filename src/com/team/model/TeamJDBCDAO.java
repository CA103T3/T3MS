package com.team.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.movie_introduce.model.Movie_IntroduceJDBCDAO;
import com.movie_introduce.model.Movie_IntroduceVO;


public class TeamJDBCDAO implements TeamDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";

	
	private static final String INSERT_STMT = "INSERT INTO TEAM(team_no,mem_no,session_no,team_time,team_name,team_des,team_date,team_pos,team_rev,team_num,state)"
			+ "VALUES ('TM'||LPAD(TEAM_SEQ.NEXTVAL,4,'0'),?,?,current_timestamp,?,?,current_timestamp,?,?,?,?)";
	private static final String UPDATE = "INSERT INTO TEAM(team_no,mem_no,session_no,team_time,team_name,team_des,team_date,team_pos,team_rev,team_num,state)"
			+ "VALUES ('TM'||LPAD(TEAM_SEQ.NEXTVAL,4,'0'),?,?,current_timestamp,?,?,current_timestamp,?,?,?,?)";
	
	
	
	
	
	@Override
	public void insert(TeamVO teamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, teamVO.getMem_no());
			pstmt.setString(2, teamVO.getSession_no());
		
			pstmt.setString(3, teamVO.getTeam_name());
			pstmt.setString(4, teamVO.getTeam_des());
			
			pstmt.setString(5, teamVO.getTeam_pos());
			pstmt.setInt(6, teamVO.getTeam_rev());
			pstmt.setInt(7, teamVO.getTeam_num());
			pstmt.setInt(8, teamVO.getState());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public void update(TeamVO teamVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
	}

	@Override
	public void delete(String team_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TeamVO findByPrimaryKey(String team_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamVO> getAllByMovie(String movie_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TeamVO> getAllByMemNo(String team_no) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {

		TeamJDBCDAO dao = new TeamJDBCDAO();

		// ----------------INSERT-------------------------------

		TeamVO TeamVO1 = new TeamVO();
		TeamVO1.setMem_no("M0005");
		TeamVO1.setSession_no("SES002");
		TeamVO1.setTeam_name("砲兵團");
		TeamVO1.setTeam_des("快來鳩我 都不糾 你們都不糾  鳩我.........");
		TeamVO1.setTeam_pos("香甜園2");
		TeamVO1.setTeam_rev(1);
		TeamVO1.setTeam_num(1);
		TeamVO1.setState(1);
		
		dao.insert(TeamVO1);
		
		System.out.println("新增成功");

		// ----------------INSERT-------------------------------
	}
}