package com.movie.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieJNDIDAO implements MovieDAO_interface {

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
	
	private static final String INSERT_STMT = "INSERT INTO MOVIE (movie_no,movie_type,movie_name,eng_name,movie_pic,relased,distributed,length,language,madein,imdb,tomato,rating,trailer_url,brief_intro,active,director,starring)"
			+ "VALUES ('MV'||LPAD(movie_seq.NEXTVAL,4,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE MOVIE set " + "movie_type=?,movie_name=?,eng_name=?,"
			+ "movie_pic=?,relased=?,distributed=?," + "length=?,language=?,madein=?,imdb=?,"
			+ "tomato=?,rating=?,trailer_url=?,brief_intro=?," + "active=?,director=?,starring=? " + "where movie_no=?";
	private static final String DELETE = "DELETE FROM movie where movie_no=?";
	private static final String GET_ONE_STMT = "SELECT * FROM MOVIE WHERE MOVIE_NO=?";
	private static final String GET_SEARCH_MOVIE_NAME = "SELECT * FROM MOVIE WHERE MOVIE_NAME LIKE ?";
	private static final String GET_ALL_STMT = "SELECT * FROM MOVIE ORDER BY MOVIE_NO DESC";
	private static final String GET_ALL_NOW = "SELECT * FROM MOVIE WHERE to_char(relased,'yyyy-mm-dd') <= to_char(sysdate,'yyyy-mm-dd') ";
	private static final String GET_ALL_COMING = "SELECT * FROM MOVIE WHERE to_char(relased,'yyyy-mm-dd')> to_char(sysdate,'yyyy-mm-dd')";

	@Override
	public void insert(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movieVO.getMovie_type());
			pstmt.setString(2, movieVO.getMovie_name());
			pstmt.setString(3, movieVO.getEng_name());
			pstmt.setBytes(4, movieVO.getMovie_pic());
			pstmt.setDate(5, movieVO.getRelased());
			pstmt.setString(6, movieVO.getDistributed());
			pstmt.setInt(7, movieVO.getLength());
			pstmt.setString(8, movieVO.getLanguage());
			pstmt.setString(9, movieVO.getMadein());
			pstmt.setDouble(10, movieVO.getImdb());
			pstmt.setString(11, movieVO.getTomato());
			pstmt.setString(12, movieVO.getRating());
			pstmt.setString(13, movieVO.getTrailer_url());
			pstmt.setString(14, movieVO.getBrief_intro());
			pstmt.setInt(15, movieVO.getActive());
			pstmt.setString(16, movieVO.getDirector());
			pstmt.setString(17, movieVO.getStarring());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movieVO.getMovie_type());
			pstmt.setString(2, movieVO.getMovie_name());
			pstmt.setString(3, movieVO.getEng_name());
			pstmt.setBytes(4, movieVO.getMovie_pic());
			pstmt.setDate(5, movieVO.getRelased());
			pstmt.setString(6, movieVO.getDistributed());
			pstmt.setInt(7, movieVO.getLength());
			pstmt.setString(8, movieVO.getLanguage());
			pstmt.setString(9, movieVO.getMadein());
			pstmt.setDouble(10, movieVO.getImdb());
			pstmt.setString(11, movieVO.getTomato());
			pstmt.setString(12, movieVO.getRating());
			pstmt.setString(13, movieVO.getTrailer_url());
			pstmt.setString(14, movieVO.getBrief_intro());
			pstmt.setInt(15, movieVO.getActive());
			pstmt.setString(16, movieVO.getDirector());
			pstmt.setString(17, movieVO.getStarring());
			pstmt.setString(18, movieVO.getMovie_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String movie_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, movie_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MovieVO findByPrimaryKey(String movie_no) {

		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, movie_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MovieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovie_no(rs.getString("movie_no"));
				movieVO.setMovie_type(rs.getString("movie_type"));
				movieVO.setMovie_name(rs.getString("movie_name"));
				movieVO.setEng_name(rs.getString("eng_name"));
				movieVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieVO.setRelased(rs.getDate("relased"));
				movieVO.setDistributed(rs.getString("distributed"));
				movieVO.setLength(rs.getInt("length"));
				movieVO.setLanguage(rs.getString("language"));
				movieVO.setMadein(rs.getString("madein"));
				movieVO.setImdb(rs.getDouble("imdb"));
				movieVO.setTomato(rs.getString("tomato"));
				movieVO.setRating(rs.getString("rating"));
				movieVO.setTrailer_url(rs.getString("trailer_url"));
				movieVO.setBrief_intro(rs.getString("brief_intro"));
				movieVO.setActive(rs.getInt("active"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setStarring(rs.getString("starring"));

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

		return movieVO;
	}

	@Override
	public List<MovieVO> getAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MovieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovie_no(rs.getString("movie_no"));
				movieVO.setMovie_type(rs.getString("movie_type"));
				movieVO.setMovie_name(rs.getString("movie_name"));
				movieVO.setEng_name(rs.getString("eng_name"));
				movieVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieVO.setRelased(rs.getDate("relased"));
				movieVO.setDistributed(rs.getString("distributed"));
				movieVO.setLength(rs.getInt("length"));
				movieVO.setLanguage(rs.getString("language"));
				movieVO.setMadein(rs.getString("madein"));
				movieVO.setImdb(rs.getDouble("imdb"));
				movieVO.setTomato(rs.getString("tomato"));
				movieVO.setRating(rs.getString("rating"));
				movieVO.setTrailer_url(rs.getString("trailer_url"));
				movieVO.setBrief_intro(rs.getString("brief_intro"));
				movieVO.setActive(rs.getInt("active"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setStarring(rs.getString("starring"));
				list.add(movieVO);
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
	
	@Override
	public List<MovieVO> getNow() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NOW);
			rs = pstmt.executeQuery();
		

			while (rs.next()) {
				// MovieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovie_no(rs.getString("movie_no"));
				movieVO.setMovie_type(rs.getString("movie_type"));
				movieVO.setMovie_name(rs.getString("movie_name"));
				movieVO.setEng_name(rs.getString("eng_name"));
				movieVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieVO.setRelased(rs.getDate("relased"));
				movieVO.setDistributed(rs.getString("distributed"));
				movieVO.setLength(rs.getInt("length"));
				movieVO.setLanguage(rs.getString("language"));
				movieVO.setMadein(rs.getString("madein"));
				movieVO.setImdb(rs.getDouble("imdb"));
				movieVO.setTomato(rs.getString("tomato"));
				movieVO.setRating(rs.getString("rating"));
				movieVO.setTrailer_url(rs.getString("trailer_url"));
				movieVO.setBrief_intro(rs.getString("brief_intro"));
				movieVO.setActive(rs.getInt("active"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setStarring(rs.getString("starring"));
				list.add(movieVO);
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
	
	@Override
	public List<MovieVO> getComingsoon() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_COMING);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// MovieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovie_no(rs.getString("movie_no"));
				movieVO.setMovie_type(rs.getString("movie_type"));
				movieVO.setMovie_name(rs.getString("movie_name"));
				movieVO.setEng_name(rs.getString("eng_name"));
				movieVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieVO.setRelased(rs.getDate("relased"));
				movieVO.setDistributed(rs.getString("distributed"));
				movieVO.setLength(rs.getInt("length"));
				movieVO.setLanguage(rs.getString("language"));
				movieVO.setMadein(rs.getString("madein"));
				movieVO.setImdb(rs.getDouble("imdb"));
				movieVO.setTomato(rs.getString("tomato"));
				movieVO.setRating(rs.getString("rating"));
				movieVO.setTrailer_url(rs.getString("trailer_url"));
				movieVO.setBrief_intro(rs.getString("brief_intro"));
				movieVO.setActive(rs.getInt("active"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setStarring(rs.getString("starring"));
				list.add(movieVO);
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

    @Override
    public MovieVO findByMovieName(String movie_name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String insertReturnMovieNo(MovieVO movieVO) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Set<MovieVO> getsrMovieName(String movie_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie_Session_Temp_VO> find_Movie_Session_All(String movie_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
