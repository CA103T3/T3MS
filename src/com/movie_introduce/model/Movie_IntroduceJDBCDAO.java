package com.movie_introduce.model;

import java.util.*;
import java.sql.*;

public class Movie_IntroduceJDBCDAO implements Movie_IntroduceDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "T3MS";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MOVIE_INTRODUCE(introd_no,movie_no,source,url,author,title,content,created_at,updated_at,active,photo_path,photo_small)"
			+ "VALUES ('MI'||LPAD(MOVIE_INTRODUCE_SEQ.NEXTVAL,4,'0'),?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?)";

	private static final String UPDATE =
			"UPDATE MOVIE_INTRODUCE SET MOVIE_NO=? ,SOURCE=? ,URL=? ,AUTHOR=? ,TITLE=? ,CONTENT=? ,UPDATED_AT=CURRENT_TIMESTAMP , ACTIVE=? ,PHOTO_PATH=?, PHOTO_SMALL=? WHERE INTROD_NO =?";

	private static final String DELETE =
			"DELETE FROM MOVIE_INTRODUCE WHERE INTROD_NO = ?";

	private static final String GET_ONE_STMT =
			"SELECT * FROM MOVIE_INTRODUCE WHERE INTROD_NO = ?";

	private static final String GET_ALL_STMT =
			"SELECT * FROM MOVIE_INTRODUCE ORDER BY INTROD_NO DESC";

	@Override
	public void insert(Movie_IntroduceVO movie_introduceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movie_introduceVO.getMovie_no());
			pstmt.setString(2, movie_introduceVO.getSource());
			pstmt.setString(3, movie_introduceVO.getUrl());
			pstmt.setString(4, movie_introduceVO.getAuthor());
			pstmt.setString(5, movie_introduceVO.getTitle());
			pstmt.setString(6, movie_introduceVO.getContent());
			pstmt.setInt(7, movie_introduceVO.getActive());
			pstmt.setString(8, movie_introduceVO.getPhoto_path());
			pstmt.setString(9, movie_introduceVO.getPhoto_small());
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
	public void update(Movie_IntroduceVO movie_introduceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, movie_introduceVO.getMovie_no());
			pstmt.setString(2, movie_introduceVO.getSource());
			pstmt.setString(3, movie_introduceVO.getUrl());
			pstmt.setString(4, movie_introduceVO.getAuthor());
			pstmt.setString(5, movie_introduceVO.getTitle());
			pstmt.setString(6, movie_introduceVO.getContent());
			pstmt.setInt(7, movie_introduceVO.getActive());
			pstmt.setString(8, movie_introduceVO.getPhoto_path());
			pstmt.setString(9, movie_introduceVO.getPhoto_small());
			pstmt.setString(10, movie_introduceVO.getIntrod_no());
			
			
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
	public void delete(String introd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, introd_no);

			pstmt.executeQuery();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, introd_no);

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
				movie_introduceVO.setPhoto_path(rs.getString("photo_path"));
				movie_introduceVO.setPhoto_small(rs.getString("photo_small"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				movie_introduceVO.setPhoto_path(rs.getString("photo_path"));
				movie_introduceVO.setPhoto_small(rs.getString("photo_small"));
				list.add(movie_introduceVO);
				// Store the row in the list
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

		return list;
	}

	public static void main(String[] args) {

		Movie_IntroduceJDBCDAO dao = new Movie_IntroduceJDBCDAO();

		// ----------------INSERT-------------------------------
//
		Movie_IntroduceVO movie_introduceVO1 = new Movie_IntroduceVO();
		movie_introduceVO1.setMovie_no("MV0004");
		movie_introduceVO1.setSource("Vogue_Movie2");
		movie_introduceVO1.setUrl("https://www.vogue.com.tw/Movie/content-42980.html");
		movie_introduceVO1.setAuthor("連勝文");
		movie_introduceVO1.setTitle("《一屍到底》當然得看之外，中秋連假有什麼電影推薦？看這5部就對了！");
		movie_introduceVO1.setContent("2018 中秋節好好的三天連假，上映的電影將近20部，在茫茫片海之中，要如何殺出重圍，命中自己最想看的電影？"
				+ "且讓《Vogue》撥開重重迷霧，為大家送上推薦選片明燈，包括最強話題的一屍到底、黃金兄弟、空中急診英雄、電影版空中急診英雄、凸槌特派員，看5部準沒錯！");
		movie_introduceVO1.setActive(0);
		movie_introduceVO1.setPhoto_path("jpg");
		movie_introduceVO1.setPhoto_small("Sjpg");
		dao.insert(movie_introduceVO1);
		
		System.out.println("新增成功");

		// ----------------INSERT-------------------------------
		// ----------------UPDATE-------------------------------

//		Movie_IntroduceVO movie_introduceVO2 = new Movie_IntroduceVO();
//		movie_introduceVO2.setIntrod_no("MI0002");
//		movie_introduceVO2.setMovie_no("MV0002");
//		movie_introduceVO2.setSource("Vogue_Movie2");
//		movie_introduceVO2.setUrl("https://www.vogue.com.tw/Movie/content-42950.html");
//		movie_introduceVO2.setAuthor("連勝文1");
//		movie_introduceVO2.setTitle("《神鬼獵人》團隊打造，「藍魔鬼」寇帝史密麥菲上演極地存亡冒險");
//		movie_introduceVO2.setContent(
//				"還記得《X戰警：天啟》裡的飾演「藍魔鬼」的 Kodi Smit-McPhee寇帝史密麥菲嗎?他在新片《極地之王Alpha》裡飾演2萬年前的人類，為求生存在極地中與受傷的孤狼共同生活，在嚴峻的環境下想盡辦法活下來的畫面，"
//						+ "不禁令人想到李奧納多狄卡皮歐在《神鬼獵人》裡的處境。");
//		movie_introduceVO2.setActive(1);
//		movie_introduceVO2.setPhoto_path("gif");
//		movie_introduceVO2.setPhoto_small("Sgif");
//		dao.update(movie_introduceVO2);
//		System.out.println("修改成功");

		// ----------------UPDATE-------------------------------
		// ----------------DELETE-------------------------------

//		dao.delete("MID0007");
//		System.out.println("刪除成功");

		// ----------------DELETE-------------------------------
		// ----------------SELECT ONE---------------------------

//		System.out.println("---------------------");
//		Movie_IntroduceVO movie_introduceVO3 = dao.findByPrimaryKey("MI0001");
//		System.out.println(movie_introduceVO3.getMovie_no() + ",");
//		System.out.println(movie_introduceVO3.getSource() + ",");
//		System.out.println(movie_introduceVO3.getUrl() + ",");
//		System.out.println(movie_introduceVO3.getAuthor() + ",");
//		System.out.println(movie_introduceVO3.getTitle() + ",");
//		System.out.println(movie_introduceVO3.getContent() + ",");
//		System.out.println(movie_introduceVO3.getCreated_at() + ",");
//		System.out.println(movie_introduceVO3.getUpdated_at() + ",");
//		System.out.println(movie_introduceVO3.getActive() + ",");
//		System.out.println(movie_introduceVO3.getPhoto_path() + ",");
//		System.out.println(movie_introduceVO3.getPhoto_small() + ",");
//		System.out.println("---------------------");

		// ----------------SELECT ONE---------------------------
		// ----------------SELECT ALL---------------------------

//		List<Movie_IntroduceVO> list = dao.getAll();
//		for (Movie_IntroduceVO mitd : list) {
//			System.out.println("---------------------");
//			System.out.println(mitd.getIntrod_no() + ",");
//			System.out.println(mitd.getMovie_no() + ",");
//			System.out.println(mitd.getSource() + ",");
//			System.out.println(mitd.getUrl() + ",");
//			System.out.println(mitd.getAuthor() + ",");
//			System.out.println(mitd.getTitle() + ",");
//			System.out.println(mitd.getContent() + ",");
//			System.out.println(mitd.getCreated_at() + ",");
//			System.out.println(mitd.getUpdated_at() + ",");
//			System.out.println(mitd.getActive() + ",");
//			System.out.println(mitd.getPhoto_path() + ",");
//			System.out.println(mitd.getPhoto_small() + ",");
//			System.out.println("---------------------");
//		}

		// ----------------SELECT ALL---------------------------

	}
}
