package com.session.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


import com.movie.model.MovieVO;
import com.theater.model.TheaterVO;
import com.cinema.model.CinemaVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SessionJDBCDAO implements SessionDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";
	
//	private static final String GET_NOW_MOMENT= "SELECT MOVIE_SESSION.SESSION_NO,MOVIE_SESSION.THEATER_NO,CINEMA.CINEMA_NO,MOVIE_SESSION.MOVIE_NO,CINEMA.CINEMA_NAME,THEATER.EQUIPMENT,MOVIE_SESSION.SESSION_TIME "
//    		+ "FROM MOVIE_SESSION LEFT JOIN THEATER ON MOVIE_SESSION.THEATER_NO = THEATER.THEATER_NO "
//    		+ "LEFT JOIN CINEMA ON THEATER.CINEMA_NO = CINEMA.CINEMA_NO "
//    		+ "WHERE MOVIE_SESSION.MOVIE_NO in (select MOVIE.MOVIE_NO from MOVIE where to_char(relased,'yyyy-mm-dd') < to_char(sysdate,'YYYY/MM/DD'))"
//    		+ "order by SESSION_TIME ";
	
	
	 private static final String GET_NOW_MOMENT= "SELECT MOVIE_SESSION.SESSION_NO,MOVIE_SESSION.THEATER_NO,CINEMA.CINEMA_NO,MOVIE_SESSION.MOVIE_NO,CINEMA.CINEMA_NAME,THEATER.EQUIPMENT,MOVIE_SESSION.SESSION_TIME "
	    		+ "FROM MOVIE_SESSION LEFT JOIN THEATER ON MOVIE_SESSION.THEATER_NO = THEATER.THEATER_NO "
	    		+ "LEFT JOIN CINEMA ON THEATER.CINEMA_NO = CINEMA.CINEMA_NO "
	    		+ "WHERE MOVIE_SESSION.MOVIE_NO in (select MOVIE.MOVIE_NO from MOVIE where to_char(relased,'yyyy-mm-dd') < to_char(sysdate,'YYYY/MM/DD')) "
	    		+ "AND SESSION_TIME BETWEEN "
	    		+ "to_date(to_char(sysdate,'yyyy-mm-dd') || ' 00:00:01','yyyy-mm-dd hh24:mi:ss') AND "
	    		+ "to_date(to_char(sysdate,'yyyy-mm-dd') || ' 23:59:59','yyyy-mm-dd hh24:mi:ss') "
	    		+ "order by SESSION_TIME ";
	
	
	@Override
	public String insert(SessionVO sessionVO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(SessionVO sessionVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String session_no) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SessionVO findByPrimaryKey(String session_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SessionVO> findBySessionTimeMovieNo(String sessionTime, String movie_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SessionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SessionVO> getAllofTheater(String theater_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SessionVO> getAllofJoinTheaterMovieWhereTheaterNoCinema(String cinema_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SessionVO getOneofJoinTheaterMovieWhereSessionNo(String session_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateSessionSeat(String seattable, String movie_session_no, Connection conn) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<SessionVO> getNowMoment() {
		List<SessionVO> list = new ArrayList<SessionVO>();
        SessionVO sessionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {


			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NOW_MOMENT);
			rs = pstmt.executeQuery();
			

            while (rs.next()) {
                // sessionVO 也稱為 Domain objects
                sessionVO = new SessionVO();
                sessionVO.setSession_no(rs.getString("SESSION_NO"));
                sessionVO.setTheater_no(rs.getString("THEATER_NO"));
                
                CinemaVO cinemaVO = new CinemaVO();
                cinemaVO.setCinema_no(rs.getString("CINEMA_NO")); 
                sessionVO.setCinemaVO(cinemaVO);
                
                sessionVO.setMovie_no(rs.getString("MOVIE_NO"));
                
                cinemaVO.setCinema_name(rs.getString("CINEMA_NAME"));
                sessionVO.setCinemaVO(cinemaVO);
                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));  
                sessionVO.setTheaterVO(theaterVO);
                
                sessionVO.setSession_time(rs.getTimestamp("SESSION_TIME"));                
                list.add(sessionVO); // Store the row in the list
            }

            
         // Handle any driver errors
         		} catch (ClassNotFoundException e) {
         			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	
	
	
	
	
	
	public static void main(String[] args) {

		SessionJDBCDAO dao = new SessionJDBCDAO();
		
		List<SessionVO> list = dao.getNowMoment();
		for (SessionVO amovie : list) {
			System.out.println(amovie.getSession_no() + ",");
			System.out.println(amovie.getTheater_no() + ",");
			System.out.println(amovie.getCinemaVO().getCinema_no() + ",");
			System.out.println(amovie.getMovie_no() + ",");
			System.out.println(amovie.getCinemaVO().getCinema_name() + ",");
			System.out.println(amovie.getTheaterVO().getEquipment() + ",");
			System.out.println(amovie.getSession_time() + ",");
			System.out.println("---------------------");
		}
}

}
