package com.boxoffice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.movie.model.MovieVO;


public class BoxOfficeDAO implements BoxOfficeDAO_interface {

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
        "INSERT INTO BOX_OFFICE_CHARTS (RANKING_NO,MOVIE_NO,MOVIENAME,STATISTICS,RANK,LOC) VALUES ('BOC'||LPAD(to_char(BOX_OFFICE_CHARTS_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT RANKING_NO,MOVIE_NO,MOVIENAME,STATISTICS,RANK,LOC FROM BOX_OFFICE_CHARTS order by RANKING_NO";
    private static final String GET_ONE_STMT =
        "SELECT RANKING_NO,MOVIE_NO,MOVIENAME,STATISTICS,RANK,LOC FROM BOX_OFFICE_CHARTS where RANKING_NO = ?";
    private static final String DELETE =
        "DELETE FROM BOX_OFFICE_CHARTS where RANKING_NO = ?";
    private static final String UPDATE =
        "UPDATE BOX_OFFICE_CHARTS set MOVIE_NO=?, MOVIENAME=?, STATISTICS=?, RANK=?, LOC=? where RANKING_NO = ?";
    private static final String GET_LATEST_TEN_BY_LOC_STMT =
            "SELECT * FROM " +
            "(SELECT RANKING_NO,BOX_OFFICE_CHARTS.MOVIE_NO,MOVIENAME,STATISTICS,RANK,LOC,MOVIE.MOVIE_NAME,"
            + " ROW_NUMBER() OVER (ORDER BY STATISTICS desc, RANK asc) as rno FROM BOX_OFFICE_CHARTS "
            + " left join MOVIE on BOX_OFFICE_CHARTS.MOVIE_NO = MOVIE.MOVIE_NO  where LOC=? ) "
            + "where rno <= 10  order by STATISTICS desc, RANK asc";

    @Override
    public String insert(BoxOfficeVO boVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String ranking_no = null;
        try {

            con = ds.getConnection();
            String[] cols = { "RANKING_NO" }; // 或 int cols[] = {1};
            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, boVO.getMovie_no());
            pstmt.setString(2, boVO.getMoviename());
            pstmt.setDate(3, boVO.getStatistics());
            pstmt.setInt(4, boVO.getRank());
            pstmt.setInt(5, boVO.getLoc());

            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        ranking_no = rs.getString(i);
                        //System.out.println("自增主鍵值 = " + session_no);
                    }
                } while (rs.next());
            } else {
                System.out.println("NO KEYS WERE GENERATED.");
            }
            
            rs.close();
            
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
        return ranking_no;
    }

    @Override
    public void update(BoxOfficeVO boVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, boVO.getMovie_no());
            pstmt.setString(2, boVO.getMoviename());
            pstmt.setDate(3, boVO.getStatistics());
            pstmt.setInt(4, boVO.getRank());
            pstmt.setInt(5, boVO.getLoc());
            pstmt.setString(6, boVO.getRanking_no());

            pstmt.executeUpdate();

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

    @Override
    public void delete(String ranking_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, ranking_no);

            pstmt.executeUpdate();

            // Handle any driver errors
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

    @Override
    public BoxOfficeVO findByPrimaryKey(String ranking_no) {
        BoxOfficeVO boVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, ranking_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // boVO 也稱為 Domain objects
                boVO = new BoxOfficeVO();
                boVO.setRanking_no(rs.getString("RANKING_NO"));
                boVO.setMovie_no(rs.getString("MOVIE_NO"));
                boVO.setMoviename(rs.getString("MOVIENAME"));
                boVO.setStatistics(rs.getDate("STATISTICS"));
                boVO.setRank(rs.getInt("RANK"));
                boVO.setLoc(rs.getInt("LOC"));
            }

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
        return boVO;
    }

    @Override
    public List<BoxOfficeVO> getAll() {
        List<BoxOfficeVO> list = new ArrayList<BoxOfficeVO>();
        BoxOfficeVO boVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // boVO 也稱為 Domain objects
                boVO = new BoxOfficeVO();
                boVO.setRanking_no(rs.getString("RANKING_NO"));
                boVO.setMovie_no(rs.getString("MOVIE_NO"));
                boVO.setMoviename(rs.getString("MOVIENAME"));
                boVO.setStatistics(rs.getDate("STATISTICS"));
                boVO.setRank(rs.getInt("RANK"));
                boVO.setLoc(rs.getInt("LOC"));
                list.add(boVO); // Store the row in the list
            }

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

    @Override
    public List<BoxOfficeVO> getLatestTenByLoc(Integer loc) {
        List<BoxOfficeVO> list = new ArrayList<BoxOfficeVO>();
        BoxOfficeVO boVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_LATEST_TEN_BY_LOC_STMT);
            pstmt.setInt(1, loc);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // boVO 也稱為 Domain objects
                boVO = new BoxOfficeVO();
                boVO.setRanking_no(rs.getString("RANKING_NO"));
                boVO.setMovie_no(rs.getString("MOVIE_NO"));
                boVO.setMoviename(rs.getString("MOVIENAME"));
                boVO.setStatistics(rs.getDate("STATISTICS"));
                boVO.setRank(rs.getInt("RANK"));
                boVO.setLoc(rs.getInt("LOC"));
                MovieVO movieVO = new MovieVO();
                movieVO.setMovie_name(rs.getString("MOVIE_NAME"));
                boVO.setMovieVO(movieVO);
                list.add(boVO); // Store the row in the list
            }

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

}
