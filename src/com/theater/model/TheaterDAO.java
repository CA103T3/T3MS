package com.theater.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TheaterDAO implements TheaterDAO_interface {

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
        "INSERT INTO theater (THEATER_NO,CINEMA_NO,THEATER_NAME,T_ROWS,T_COLUMNS,SEAT_MODEL,SEATS,EQUIPMENT) VALUES ('T'||LPAD(to_char(THEATER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT THEATER_NO,CINEMA_NO,THEATER_NAME,T_ROWS,T_COLUMNS,SEAT_MODEL,SEATS,EQUIPMENT FROM theater order by THEATER_NO";
    private static final String GET_ALL_OF_CINEMA_STMT =
            "SELECT THEATER_NO,CINEMA_NO,THEATER_NAME,T_ROWS,T_COLUMNS,SEAT_MODEL,SEATS,EQUIPMENT FROM theater where CINEMA_NO = ? order by THEATER_NO";
    private static final String GET_ONE_STMT =
        "SELECT THEATER_NO,CINEMA_NO,THEATER_NAME,T_ROWS,T_COLUMNS,SEAT_MODEL,SEATS,EQUIPMENT FROM theater where THEATER_NO = ?";
    private static final String GET_ALL_BY_CINEMA_NO_EQUIPMENT_STMT =
            "SELECT THEATER_NO,CINEMA_NO,THEATER_NAME,T_ROWS,T_COLUMNS,SEAT_MODEL,SEATS,EQUIPMENT FROM theater where CINEMA_NO = ? and EQUIPMENT = ?";
    private static final String GET_ALL_OF_NO_CONFLICTING_THEATER_STMT =
            "SELECT * from theater where CINEMA_NO = ? and THEATER_NO not in ( SELECT DISTINCT THEATER_NO from MOVIE_SESSION where "
            + "(session_time between to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS') and to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')) and THEATER_NO in (select THEATER.THEATER_NO from THEATER where THEATER.CINEMA_NO = ?) )"
            + " order by THEATER_NO";
    private static final String DELETE =
        "DELETE FROM theater where THEATER_NO = ?";
    private static final String UPDATE =
        "UPDATE theater set CINEMA_NO=?, THEATER_NAME=?, T_ROWS=?, T_COLUMNS=?, SEAT_MODEL=?, SEATS=?, EQUIPMENT=? where THEATER_NO = ?";

    @Override
    public String insert(TheaterVO theaterVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String theater_no = null;
        try {

            con = ds.getConnection();
            String[] cols = { "THEATER_NO" }; // 或 int cols[] = {1};
            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, theaterVO.getCinema_no());
            pstmt.setString(2, theaterVO.getTheater_name());
            pstmt.setInt(3, theaterVO.getT_rows());
            pstmt.setInt(4, theaterVO.getT_columns());
            
            //pstmt.setCharacterStream(5, theaterVO.getSeat_model());
            pstmt.setString(5, theaterVO.getSeat_model());
            pstmt.setInt(6, theaterVO.getSeats());
            pstmt.setString(7, theaterVO.getEquipment());

            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        theater_no = rs.getString(i);
                        //System.out.println("自增主鍵值 = " + theater_no);
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
        return theater_no;
    }

    @Override
    public void update(TheaterVO theaterVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, theaterVO.getCinema_no());
            pstmt.setString(2, theaterVO.getTheater_name());
            pstmt.setInt(3, theaterVO.getT_rows());
            pstmt.setInt(4, theaterVO.getT_columns());
            //pstmt.setCharacterStream(5, theaterVO.getSeat_model());
            pstmt.setString(5, theaterVO.getSeat_model());
            pstmt.setInt(6, theaterVO.getSeats());
            pstmt.setString(7, theaterVO.getEquipment());
            pstmt.setString(8, theaterVO.getTheater_no());

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
    public void delete(String theater_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, theater_no);

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
    public TheaterVO findByPrimaryKey(String theater_no) {
        TheaterVO theaterVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, theater_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // theaterVO 也稱為 Domain objects
                theaterVO = new TheaterVO();
                theaterVO.setTheater_no(rs.getString("THEATER_NO"));
                theaterVO.setCinema_no(rs.getString("CINEMA_NO"));
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                theaterVO.setT_rows(rs.getInt("T_ROWS"));
                theaterVO.setT_columns(rs.getInt("T_COLUMNS"));
                //theaterVO.setSeat_model(rs.getCharacterStream("SEAT_MODEL"));
                theaterVO.setSeat_model(rs.getString("SEAT_MODEL"));
                theaterVO.setSeats(rs.getInt("SEATS"));
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));
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
        return theaterVO;
    }

    @Override
    public List<TheaterVO> getAll() {
        List<TheaterVO> list = new ArrayList<TheaterVO>();
        TheaterVO theaterVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // theaterVO 也稱為 Domain objects
                theaterVO = new TheaterVO();
                theaterVO.setTheater_no(rs.getString("THEATER_NO"));
                theaterVO.setCinema_no(rs.getString("CINEMA_NO"));
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                theaterVO.setT_rows(rs.getInt("T_ROWS"));
                theaterVO.setT_columns(rs.getInt("T_COLUMNS"));
                //theaterVO.setSeat_model(rs.getCharacterStream("SEAT_MODEL"));
                theaterVO.setSeat_model(rs.getString("SEAT_MODEL"));
                theaterVO.setSeats(rs.getInt("SEATS"));
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));
                list.add(theaterVO); // Store the row in the list
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
    public List<TheaterVO> getAllofCinema(String cinema_no) {
        List<TheaterVO> list = new ArrayList<TheaterVO>();
        TheaterVO theaterVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_OF_CINEMA_STMT);
            pstmt.setString(1, cinema_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // theaterVO 也稱為 Domain objects
                theaterVO = new TheaterVO();
                theaterVO.setTheater_no(rs.getString("THEATER_NO"));
                theaterVO.setCinema_no(rs.getString("CINEMA_NO"));
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                theaterVO.setT_rows(rs.getInt("T_ROWS"));
                theaterVO.setT_columns(rs.getInt("T_COLUMNS"));
                //theaterVO.setSeat_model(rs.getCharacterStream("SEAT_MODEL"));
                theaterVO.setSeat_model(rs.getString("SEAT_MODEL"));
                theaterVO.setSeats(rs.getInt("SEATS"));
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));
                list.add(theaterVO); // Store the row in the list
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
    public List<TheaterVO> getAllByCinemaNOEquipment(String cinema_no, String equipment) {
        List<TheaterVO> list = new ArrayList<TheaterVO>();
        TheaterVO theaterVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_BY_CINEMA_NO_EQUIPMENT_STMT);
            pstmt.setString(1, cinema_no);
            pstmt.setString(2, equipment);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // theaterVO 也稱為 Domain objects
                theaterVO = new TheaterVO();
                theaterVO.setTheater_no(rs.getString("THEATER_NO"));
                theaterVO.setCinema_no(rs.getString("CINEMA_NO"));
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                theaterVO.setT_rows(rs.getInt("T_ROWS"));
                theaterVO.setT_columns(rs.getInt("T_COLUMNS"));
                //theaterVO.setSeat_model(rs.getCharacterStream("SEAT_MODEL"));
                theaterVO.setSeat_model(rs.getString("SEAT_MODEL"));
                theaterVO.setSeats(rs.getInt("SEATS"));
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));
                list.add(theaterVO); // Store the row in the list
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
    public List<TheaterVO> getAllofNoConflictingTheater(String cinema_no, String sessionTimeFirst, String sessionTimeSec) {
        List<TheaterVO> list = new ArrayList<TheaterVO>();
        TheaterVO theaterVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_OF_NO_CONFLICTING_THEATER_STMT);
            pstmt.setString(1, cinema_no);
            pstmt.setString(2, sessionTimeFirst);
            pstmt.setString(3, sessionTimeSec);
            pstmt.setString(4, cinema_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // theaterVO 也稱為 Domain objects
                theaterVO = new TheaterVO();
                theaterVO.setTheater_no(rs.getString("THEATER_NO"));
                theaterVO.setCinema_no(rs.getString("CINEMA_NO"));
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                theaterVO.setT_rows(rs.getInt("T_ROWS"));
                theaterVO.setT_columns(rs.getInt("T_COLUMNS"));
                //theaterVO.setSeat_model(rs.getCharacterStream("SEAT_MODEL"));
                theaterVO.setSeat_model(rs.getString("SEAT_MODEL"));
                theaterVO.setSeats(rs.getInt("SEATS"));
                theaterVO.setEquipment(rs.getString("EQUIPMENT"));
                list.add(theaterVO); // Store the row in the list
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
