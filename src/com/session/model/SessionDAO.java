package com.session.model;

import java.sql.Connection;
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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SessionDAO implements SessionDAO_interface {

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
        "INSERT INTO MOVIE_SESSION (SESSION_NO,THEATER_NO,MOVIE_NO,SESSION_TIME,SEAT_TABLE) VALUES ('SES'||LPAD(to_char(MOVIE_SESSION_SEQ.NEXTVAL), 7, '0'), ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT SESSION_NO,THEATER_NO,MOVIE_NO,SESSION_TIME,SEAT_TABLE FROM MOVIE_SESSION order by SESSION_NO";
    private static final String GET_ALL_OF_THEATER_STMT =
            "SELECT SESSION_NO,THEATER_NO,MOVIE_NO,SESSION_TIME,SEAT_TABLE FROM MOVIE_SESSION where THEATER_NO = ? order by SESSION_NO";
    private static final String GET_ALL_OF_JOIN_THEATER_MOVIE_WHERE_THEATERNO_CINEMA_STMT =
            "select MOVIE_SESSION.SESSION_NO,MOVIE_SESSION.THEATER_NO,MOVIE_SESSION.MOVIE_NO,MOVIE_SESSION.SESSION_TIME,MOVIE_SESSION.SEAT_TABLE, THEATER.THEATER_NAME, MOVIE.MOVIE_NAME "
            + "from MOVIE_SESSION left join THEATER on MOVIE_SESSION.THEATER_NO = THEATER.THEATER_NO left join MOVIE on MOVIE_SESSION.MOVIE_NO = MOVIE.MOVIE_NO "
            + "where MOVIE_SESSION.THEATER_NO in (select THEATER.THEATER_NO from THEATER where THEATER.CINEMA_NO = ?) order by SESSION_NO";
    private static final String GET_ONE_STMT =
        "SELECT SESSION_NO,THEATER_NO,MOVIE_NO,SESSION_TIME,SEAT_TABLE FROM MOVIE_SESSION where SESSION_NO = ?";
    private static final String DELETE =
        "DELETE FROM MOVIE_SESSION where SESSION_NO = ?";
    private static final String UPDATE =
        "UPDATE MOVIE_SESSION set THEATER_NO=?, MOVIE_NO=?, SESSION_TIME=?, SEAT_TABLE=? where SESSION_NO = ?";

    @Override
    public String insert(SessionVO sessionVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String session_no = null;
        try {

            con = ds.getConnection();
            String[] cols = { "SESSION_NO" }; // 或 int cols[] = {1};
            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, sessionVO.getTheater_no());
            pstmt.setString(2, sessionVO.getMovie_no());
//            pstmt.setTimestamp(3, sessionVO.getSession_time());
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
            pstmt.setTimestamp(3, sessionVO.getSession_time(), cal);
            pstmt.setString(4, sessionVO.getSeat_table());

            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        session_no = rs.getString(i);
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
        return session_no;
    }

    @Override
    public void update(SessionVO sessionVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, sessionVO.getTheater_no());
            pstmt.setString(2, sessionVO.getMovie_no());
            pstmt.setTimestamp(3, sessionVO.getSession_time());
            pstmt.setString(4, sessionVO.getSeat_table());
            pstmt.setString(5, sessionVO.getSession_no());

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
    public void delete(String session_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, session_no);

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
    public SessionVO findByPrimaryKey(String session_no) {
        SessionVO sessionVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, session_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // sessionVO 也稱為 Domain objects
                sessionVO = new SessionVO();
                sessionVO.setSession_no(rs.getString("SESSION_NO"));
                sessionVO.setTheater_no(rs.getString("THEATER_NO"));
                sessionVO.setMovie_no(rs.getString("MOVIE_NO"));
                sessionVO.setSession_time(rs.getTimestamp("SESSION_TIME"));
                sessionVO.setSeat_table(rs.getString("SEAT_TABLE"));
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
        return sessionVO;
    }

    @Override
    public List<SessionVO> getAll() {
        List<SessionVO> list = new ArrayList<SessionVO>();
        SessionVO sessionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // sessionVO 也稱為 Domain objects
                sessionVO = new SessionVO();
                sessionVO.setSession_no(rs.getString("SESSION_NO"));
                sessionVO.setTheater_no(rs.getString("THEATER_NO"));
                sessionVO.setMovie_no(rs.getString("MOVIE_NO"));
                sessionVO.setSession_time(rs.getTimestamp("SESSION_TIME"));
                sessionVO.setSeat_table(rs.getString("SEAT_TABLE"));
                list.add(sessionVO); // Store the row in the list
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
    public List<SessionVO> getAllofTheater(String theater_no) {
        List<SessionVO> list = new ArrayList<SessionVO>();
        SessionVO sessionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_OF_THEATER_STMT);
            pstmt.setString(1, theater_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // sessionVO 也稱為 Domain objects
                sessionVO = new SessionVO();
                sessionVO.setSession_no(rs.getString("SESSION_NO"));
                sessionVO.setTheater_no(rs.getString("THEATER_NO"));
                sessionVO.setMovie_no(rs.getString("MOVIE_NO"));
                sessionVO.setSession_time(rs.getTimestamp("SESSION_TIME"));
                sessionVO.setSeat_table(rs.getString("SEAT_TABLE"));
                list.add(sessionVO); // Store the row in the list
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
    public List<SessionVO> getAllofJoinTheaterMovieWhereTheaterNoCinema(String cinema_no) {
        List<SessionVO> list = new ArrayList<SessionVO>();
        SessionVO sessionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_OF_JOIN_THEATER_MOVIE_WHERE_THEATERNO_CINEMA_STMT);
            //System.out.println(cinema_no); //if no parameter, null
            pstmt.setString(1, cinema_no);
            rs = pstmt.executeQuery();
            //System.out.println(GET_ALL_OF_JOIN_THEATER_MOVIE_WHERE_THEATERNO_CINEMA_STMT);
            while (rs.next()) {
                // sessionVO 也稱為 Domain objects
                sessionVO = new SessionVO();
                sessionVO.setSession_no(rs.getString("SESSION_NO"));
                sessionVO.setTheater_no(rs.getString("THEATER_NO"));
                sessionVO.setMovie_no(rs.getString("MOVIE_NO"));
                sessionVO.setSession_time(rs.getTimestamp("SESSION_TIME"));
                sessionVO.setSeat_table(rs.getString("SEAT_TABLE"));
                MovieVO movieVO = new MovieVO();
                movieVO.setMovie_name(rs.getString("MOVIE_NAME"));
                sessionVO.setMovieVO(movieVO);
                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                sessionVO.setTheaterVO(theaterVO);
                list.add(sessionVO); // Store the row in the list
                System.out.println(rs.getString("SESSION_NO"));
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
