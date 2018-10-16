package com.movie.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.filmreview.model.FilmreviewVO;

public class MovieDAO implements MovieDAO_interface {

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
	private static final String GET_ONE_STMT_BY_MOVIE_NAME = "SELECT * FROM MOVIE WHERE MOVIE_NAME=?";
	private static final String GET_SEARCH_MOVIE_NAME = "SELECT * FROM MOVIE WHERE MOVIE_NAME LIKE ?";
	private static final String GET_ALL_STMT = "SELECT * FROM MOVIE ORDER BY MOVIE_NO DESC";
	private static final String GET_ALL_NOW = "SELECT * FROM MOVIE WHERE relased <= sysdate and active = 1 ORDER BY MOVIE_NO ";
	private static final String GET_ALL_COMING = "SELECT * FROM MOVIE WHERE relased > sysdate and active = 1 ORDER BY relased ";
	private static final String GET_MOVIE_SESSION_ALL = "SELECT MOVIE_SESSION.SESSION_NO,MOVIE_SESSION.THEATER_NO,CINEMA.CINEMA_NO,MOVIE_SESSION.MOVIE_NO,CINEMA.CINEMA_NAME,THEATER.EQUIPMENT,MOVIE_SESSION.SESSION_TIME "
			+ "FROM MOVIE_SESSION LEFT JOIN THEATER ON MOVIE_SESSION.THEATER_NO = THEATER.THEATER_NO "
			+ "LEFT JOIN CINEMA ON THEATER.CINEMA_NO = CINEMA.CINEMA_NO "
			+ "WHERE MOVIE_SESSION.MOVIE_NO in (select MOVIE.MOVIE_NO from MOVIE where to_char(relased,'yyyy-mm-dd') < to_char(sysdate,'YYYY/MM/DD')) "
			+ "AND SESSION_TIME BETWEEN "
			+ "to_date(to_char(sysdate,'yyyy-mm-dd') || ' 00:00:01','yyyy-mm-dd hh24:mi:ss') AND "
			+ "to_date(to_char(sysdate,'yyyy-mm-dd') || ' 23:59:59','yyyy-mm-dd hh24:mi:ss') "
			+ "AND SESSION_TIME > systimestamp " + "AND MOVIE_SESSION.MOVIE_NO =? "
			+ "order by CINEMA_NO,SESSION_TIME ";

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
	public MovieVO findByMovieName(String movie_name) {

		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MOVIE_NAME);
			pstmt.setString(1, movie_name);

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
	public String insertReturnMovieNo(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String movie_no = null;
		try {

			con = ds.getConnection();
			String[] cols = { "MOVIE_NO" }; // 或 int cols[] = {1};
			pstmt = con.prepareStatement(INSERT_STMT, cols);

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

			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						movie_no = rs.getString(i);
						// System.out.println("自增主鍵值 = " + theater_no);
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}

			rs.close();

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

		return movie_no;
	}

	@Override
	public Set<MovieVO> getsrMovieName(String movie_name) {
		Set<MovieVO> set = new LinkedHashSet<MovieVO>();
		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCH_MOVIE_NAME);
			pstmt.setString(1, "%" + movie_name + "%");

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
				set.add(movieVO);
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

		return set;
	}

	@Override
	public List<Movie_Session_Temp_VO> find_Movie_Session_All(String movie_no) {
		List<Movie_Session_Temp_VO> list = new ArrayList<>();
		Movie_Session_Temp_VO mSession_Temp_VO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_MOVIE_SESSION_ALL);
			System.out.println("====gsdfgsdfg==========");
			pstmt.setString(1, movie_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mSession_Temp_VO = new Movie_Session_Temp_VO();
				mSession_Temp_VO.setSession_no(rs.getString("SESSION_NO"));
				mSession_Temp_VO.setTheater_no(rs.getString("THEATER_NO"));
				mSession_Temp_VO.setCinema_no(rs.getString("CINEMA_NO"));
				mSession_Temp_VO.setMovie_no(rs.getString("MOVIE_NO"));
				mSession_Temp_VO.setCinema_name(rs.getString("CINEMA_NAME"));
				mSession_Temp_VO.setEquipment(rs.getString("EQUIPMENT"));
				mSession_Temp_VO.setSession_time(rs.getTimestamp("SESSION_TIME"));
				list.add(mSession_Temp_VO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
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
		return list;
	}

}
