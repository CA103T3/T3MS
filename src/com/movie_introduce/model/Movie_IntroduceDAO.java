package com.movie_introduce.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Movie_IntroduceDAO implements Movie_IntroduceDAO_interface {
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
	private static final String INSERT_STMT = 
			"INSERT INTO MOVIE_INTRODUCE(introd_no,movie_no,source,url,author,title,content,created_at,updated_at,active)"
			+ "VALUES ('MID'||LPAD(MOVIE_INTRODUCE_SEQ.NEXTVAL,4,'0'),?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";

	private static final String UPDATE = 
			"UPDATE MOVIE_INTRODUCE SET MOVIE_NO=? ,SOURCE=? ,URL=? ,AUTHOR=? ,TITLE=? ,CONTENT=? ,UPDATED_AT=CURRENT_TIMESTAMP , ACTIVE=? WHERE INTROD_NO =?";

	private static final String DELETE = 
			"DELETE FROM MOVIE_INTRODUCE WHERE INTROD_NO = ?";

	private static final String GET_ONE_STMT = 
			"SELECT * FROM MOVIE_INTRODUCE WHERE INTROD_NO = ?";

	private static final String GET_ALL_STMT = 
			"SELECT * FROM MOVIE_INTRODUCE ORDER BY INTROD_NO";

	@Override
	public void insert(Movie_IntroduceVO movie_introduceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movie_introduceVO.getMovie_no());
			pstmt.setString(2, movie_introduceVO.getSource());
			pstmt.setString(3, movie_introduceVO.getUrl());
			pstmt.setString(4, movie_introduceVO.getAuthor());
			pstmt.setString(5, movie_introduceVO.getTitle());
			pstmt.setString(6, movie_introduceVO.getContent());
			pstmt.setInt(7, movie_introduceVO.getActive());

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

	@Override
	public void update(Movie_IntroduceVO movie_introduceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movie_introduceVO.getMovie_no());
			pstmt.setString(2, movie_introduceVO.getSource());
			pstmt.setString(3, movie_introduceVO.getUrl());
			pstmt.setString(4, movie_introduceVO.getAuthor());
			pstmt.setString(5, movie_introduceVO.getTitle());
			pstmt.setString(6, movie_introduceVO.getContent());
			pstmt.setInt(7, movie_introduceVO.getActive());
			pstmt.setString(8, movie_introduceVO.getIntrod_no());

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
	public void delete(String introd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, introd_no);

			pstmt.executeQuery();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Movie_IntroduceVO findByPrimaryKey(String introd_no) {

		Movie_IntroduceVO movie_introduceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, introd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Movie_IntroduceVO 也稱為 Domain objects
				movie_introduceVO = new Movie_IntroduceVO();
				movie_introduceVO.setMovie_no(rs.getString("movie_no"));
				movie_introduceVO.setSource(rs.getString("source"));
				movie_introduceVO.setUrl(rs.getString("url"));
				movie_introduceVO.setAuthor(rs.getString("author"));
				movie_introduceVO.setTitle(rs.getString("title"));
				movie_introduceVO.setContent(rs.getString("content"));
				movie_introduceVO.setCreated_at(rs.getDate("created_at"));
				movie_introduceVO.setUpdated_at(rs.getDate("updated_at"));
				movie_introduceVO.setActive(rs.getInt("active"));
			}

			// Handle any SQL errors
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

		return movie_introduceVO;
	}

	@Override
	public List<Movie_IntroduceVO> getAll() {
		List<Movie_IntroduceVO> list = new ArrayList<Movie_IntroduceVO>();
		Movie_IntroduceVO movie_introduceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Movie_IntroduceVO 也稱為 Domain objects
				movie_introduceVO = new Movie_IntroduceVO();
				movie_introduceVO.setIntrod_no(rs.getString("introd_no"));
				movie_introduceVO.setMovie_no(rs.getString("movie_no"));
				movie_introduceVO.setSource(rs.getString("source"));
				movie_introduceVO.setUrl(rs.getString("url"));
				movie_introduceVO.setAuthor(rs.getString("author"));
				movie_introduceVO.setTitle(rs.getString("title"));
				movie_introduceVO.setContent(rs.getString("content"));
				movie_introduceVO.setCreated_at(rs.getDate("created_at"));
				movie_introduceVO.setUpdated_at(rs.getDate("updated_at"));
				movie_introduceVO.setActive(rs.getInt("active"));
				list.add(movie_introduceVO);
				// Store the row in the list
			}

			// Handle any SQL errors
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

}
