package com.moviesession.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieJDBCDAO implements Movie_SessionDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:XE";
	String user = "T3MS";
	String password = "123456";

	private static final String INSERT = "INSERT INTO MOVIE_SESSION(MOVIE_SESSION_NO,THEATER_NO,MOVIE_NO,SEAT_TABLE) "
			+ "VALUES(MOVIE_SESSION_SEQ.NEXTVAL,?,?,?)";
	private static final String UPDATE = "UPDATE MOVIE_SESSON SET THEATER_NO = ?,MOVIE_NO = ?,SEAT_TABLE = ?"
			+ "WHERE MOVIE_SESSION_NO = ?";
	private static final String DELETE = "DELETE FROM MOVIE_SESSION WHERE MOVIE_SESSION_NO=?";
	private static final String FIND_BY_PRIMARYKEY = "SELECT * FROM MOVIE_SESSION WHERE MOVIE_SESSION_NO = ?";
	private static final String GET_ALL = "SELECT * FROM MOVIE_SESSION ORDER BY MOVIE_SESSION_NO";

	@Override
	public void insert(Movie_SessionVO movie_SessionVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(INSERT);

			pstmt.setString(1, movie_SessionVO.getTheater_no());
			pstmt.setString(2, movie_SessionVO.getMovie_no());
			pstmt.setString(3, movie_SessionVO.getSeat_table());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Movie_SessionVO movie_SessionVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(UPDATE);
			/*
			 * UPDATE 
			 * MOVIE_SESSON SET THEATER_NO = ?, 
			 * MOVIE_NO = ?, 
			 * SEAT_TABLE = ? 
			 * WHERE
			 * MOVIE_SESSION_NO = ?";
			 */
			pstmt.setString(1, movie_SessionVO.getTheater_no());
			pstmt.setString(2, movie_SessionVO.getMovie_no());
			pstmt.setString(3, movie_SessionVO.getSeat_table());
			pstmt.setString(4, movie_SessionVO.getMovie_session_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally {
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String movie_session_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public Movie_SessionVO findByPrimaryKey(String movie_session_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie_SessionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie_SessionVO> getMovieSessionAll(String movie_session_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
