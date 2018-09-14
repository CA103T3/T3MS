package com.movie.model;

import java.util.*;
import java.sql.*;

public class MovieJDBCDAO implements MovieDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MOVIE (movie_no,movie_type,movie_name,eng_name,poster_path,relased,distributed,length,language,madein,imdb,tomato,rating,trailer_url,brief_intro,active,director,starring)"
			+ "VALUES (movie_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE MOVIE set " + "movie_type=?,movie_name=?,eng_name=?,"
			+ "poster_path=?,relased=?,distributed=?," + "length=?,language=?,madein=?,imdb=?,"
			+ "tomato=?,rating=?,trailer_url=?,brief_intro=?," + "active=?,director=?,starring=? " + "where movie_no=?";
	private static final String DELETE = "DELETE FROM movie where movie_no=?";
	private static final String GET_ONE_STMT = "SELECT movie_no,movie_type,movie_name,eng_name,poster_path,to_char(relased,'yyyy-mm-dd'),"
			+ "distributed,length,language,madein,imdb,tomato,rating,trailer_url,"
			+ "brief_intro,active,director,starring FROM movie where movie_no=?";

	@Override
	public void insert(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movieVO.getMovie_type());
			pstmt.setString(2, movieVO.getMovie_name());
			pstmt.setString(3, movieVO.getEng_name());
			pstmt.setString(4, movieVO.getPoster_path());
			pstmt.setDate(5, movieVO.getRelased());
			pstmt.setString(6, movieVO.getDistributed());
			pstmt.setInt(7, movieVO.getLength());
			pstmt.setString(8, movieVO.getLanguage());
			pstmt.setString(9, movieVO.getMadein());
			pstmt.setInt(10, movieVO.getImdb());
			pstmt.setString(11, movieVO.getTomato());
			pstmt.setString(12, movieVO.getRating());
			pstmt.setString(13, movieVO.getTrailer_url());
			pstmt.setString(14, movieVO.getBrief_intro());
			pstmt.setInt(15, movieVO.getActive());
			pstmt.setString(16, movieVO.getDirector());
			pstmt.setString(17, movieVO.getStarring());

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
	public void update(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movieVO.getMovie_type());
			pstmt.setString(2, movieVO.getMovie_name());
			pstmt.setString(3, movieVO.getEng_name());
			pstmt.setString(4, movieVO.getPoster_path());
			pstmt.setDate(5, movieVO.getRelased());
			pstmt.setString(6, movieVO.getDistributed());
			pstmt.setInt(7, movieVO.getLength());
			pstmt.setString(8, movieVO.getLanguage());
			pstmt.setString(9, movieVO.getMadein());
			pstmt.setInt(10, movieVO.getImdb());
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String movie_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, movie_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public MovieVO findByPrimaryKey(String movie_no) {

		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				movieVO.setPoster_path(rs.getString("poster_path"));
				movieVO.setRelased(rs.getDate("relased"));
				movieVO.setDistributed(rs.getString("distributed"));
				movieVO.setLength(rs.getInt("length"));
				movieVO.setLanguage(rs.getString("language"));
				movieVO.setMadein(rs.getString("madein"));
				movieVO.setImdb(rs.getInt("imdb"));
				movieVO.setTomato(rs.getString("tomato"));
				movieVO.setRating(rs.getString("rating"));
				movieVO.setTrailer_url(rs.getString("trailer_url"));
				movieVO.setBrief_intro(rs.getString("brief_intro"));
				movieVO.setActive(rs.getInt("active"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setStarring(rs.getString("starring"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		MovieJDBCDAO dao = new MovieJDBCDAO();

		// ----------------INSERT------------------
//		MovieVO movieVO1 = new MovieVO();
//		movieVO1.setMovie_type("恐怖片");
//		movieVO1.setMovie_name("鬼修女");
//		movieVO1.setEng_name("ShowGirl");
//		movieVO1.setPoster_path("img");
//		movieVO1.setRelased(java.sql.Date.valueOf("2018-09-11"));
//		movieVO1.setDistributed("華納");
//		movieVO1.setLength(120);
//		movieVO1.setLanguage("English");
//		movieVO1.setMadein("USA");
//		movieVO1.setImdb(87);
//		movieVO1.setTomato("80分");
//		movieVO1.setRating("限制級");
//		movieVO1.setTrailer_url("www.yahoo.com.tw");
//		movieVO1.setBrief_intro(
//				"在《鬼修女》中，破紀錄賣座恐怖片《厲陰宅》及《厲陰宅2》的導演溫子仁，著手探索另一個駭人的黑暗角落。這部新片由柯林哈迪（《陰林》）執導，溫子仁及《厲陰宅》全系列製片人彼得沙佛朗製作。\r\n"
//						+ "在羅馬尼亞一間與世隔絕的修道院中，有一個年輕的修女自殺了，梵蒂岡派出一名藏有悲慘過去的神父和一名剛入教的見習修女來調查。他們一起揭開這個教團的邪惡祕密，卻遭遇到一股邪惡的力量，也就是《厲陰宅2》中嚇壞觀眾的那個鬼修女，使修道院變成活人與受詛咒者之間的可怕戰場。他們不只可能會喪命，還可能會失去原本的信仰和自己的靈魂。");
//		movieVO1.setActive(1);
//		movieVO1.setDirector("李安");
//		movieVO1.setStarring("許純美");
//
//		dao.insert(movieVO1);
//		System.out.println("新增成功");
		// ----------------INSERT------------------
		// ----------------UPDATE------------------

//		MovieVO movieVO2 = new MovieVO();
//		movieVO2.setMovie_no("4");
//		movieVO2.setMovie_type("喜劇片");
//		movieVO2.setMovie_name("鬼修感");
//		movieVO2.setEng_name("5566ShowGirl");
//		movieVO2.setPoster_path("img");
//		movieVO2.setRelased(java.sql.Date.valueOf("2018-09-15"));
//		movieVO2.setDistributed("華納2");
//		movieVO2.setLength(220);
//		movieVO2.setLanguage("TAIWAN");
//		movieVO2.setMadein("JAPAN");
//		movieVO2.setImdb(88);
//		movieVO2.setTomato("87分");
//		movieVO2.setRating("普遍級");
//		movieVO2.setTrailer_url("www.youtube.com.tw");
//		movieVO2.setBrief_intro(
//				"在《鬼修女2》中，破紀錄賣座恐怖片《厲陰宅》及《厲陰宅2》的導演溫子仁，著手探索另一個駭人的黑暗角落。這部新片由柯林哈迪（《陰林》）執導，溫子仁及《厲陰宅》全系列製片人彼得沙佛朗製作。\r\n"
//						+ "在羅馬尼亞一間與世隔絕的修道院中。");
//		movieVO2.setActive(1);
//		movieVO2.setDirector("陳水扁");
//		movieVO2.setStarring("馬英九");
//		
//		dao.update(movieVO2);
//		System.out.println("修改成功");

		// ----------------UPDATE------------------
		// ----------------DELETE------------------

//		dao.delete("4");
//		System.out.println("刪除成功");

		// ----------------DELETE------------------
		// ----------------SELECT ONE--------------
		
		System.out.println("---------------------");
		MovieVO movieVO3 = dao.findByPrimaryKey("1");
		System.out.println(movieVO3.getMovie_no() + ",");
		System.out.println(movieVO3.getMovie_type() + ",");
		System.out.println(movieVO3.getMovie_name() + ",");
		System.out.println(movieVO3.getEng_name() + ",");
		System.out.println(movieVO3.getPoster_path() + ",");
		System.out.println(movieVO3.getRelased() + ",");
		System.out.println(movieVO3.getDistributed() + ",");
		System.out.println(movieVO3.getLength() + ",");
		System.out.println(movieVO3.getLanguage() + ",");
		System.out.println(movieVO3.getMadein() + ",");
		System.out.println(movieVO3.getImdb() + ",");
		System.out.println(movieVO3.getTomato() + ",");
		System.out.println(movieVO3.getRating() + ",");
		System.out.println(movieVO3.getTrailer_url() + ",");
		System.out.println(movieVO3.getBrief_intro() + ",");
		System.out.println(movieVO3.getActive() + ",");
		System.out.println(movieVO3.getDirector() + ",");
		System.out.println(movieVO3.getStarring() + ",");
		System.out.println("---------------------");
		
		// ----------------SELECT ONE--------------

	}

}
