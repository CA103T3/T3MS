package com.cinema.model;

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

import com.cinema.model.CinemaVO;

public class CinemaDAO implements CinemaDAO_interface {

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
        "INSERT INTO cinema (CINEMA_NO,CINEMA_NAME,CINEMA_ENGNAME,CINEMA_ADDRESS,CINEMA_TEL,INTRODUCTION,TRAFFIC,PHOTO_TITLE,PHOTO_PATH,PHOTO_SMALL,ACTIVE,STATE) VALUES ('C'||LPAD(to_char(CINEMA_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT CINEMA_NO,CINEMA_NAME,CINEMA_ENGNAME,CINEMA_ADDRESS,CINEMA_TEL,INTRODUCTION,TRAFFIC,PHOTO_TITLE,PHOTO_PATH,PHOTO_SMALL,ACTIVE,STATE FROM cinema order by CINEMA_NO";
    private static final String GET_ONE_STMT =
        "SELECT CINEMA_NO,CINEMA_NAME,CINEMA_ENGNAME,CINEMA_ADDRESS,CINEMA_TEL,INTRODUCTION,TRAFFIC,PHOTO_TITLE,PHOTO_PATH,PHOTO_SMALL,ACTIVE,STATE FROM cinema where CINEMA_NO = ?";
    private static final String DELETE =
        "DELETE FROM cinema where CINEMA_NO = ?";
    private static final String UPDATE =
        "UPDATE cinema set CINEMA_NAME=?, CINEMA_ENGNAME=?, CINEMA_ADDRESS=?, CINEMA_TEL=?, INTRODUCTION=?, TRAFFIC=?, PHOTO_TITLE=?, PHOTO_PATH=?, PHOTO_SMALL=?, ACTIVE=?, STATE=? where CINEMA_NO = ?";

    @Override
    public String insert(CinemaVO cinemaVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String cinema_no = null;
        try {

            con = ds.getConnection();
            String[] cols = { "CINEMA_NO" }; // 或 int cols[] = {1};
            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, cinemaVO.getCinema_name());
            pstmt.setString(2, cinemaVO.getCinema_engname());
            pstmt.setString(3, cinemaVO.getCinema_address());
            pstmt.setString(4, cinemaVO.getCinema_tel());
            pstmt.setString(5, cinemaVO.getIntroduction());
            pstmt.setString(6, cinemaVO.getTraffic());
            pstmt.setString(7, cinemaVO.getPhoto_title());
            pstmt.setString(8, cinemaVO.getPhoto_path());
            pstmt.setString(9, cinemaVO.getPhoto_small());
            pstmt.setInt(10, cinemaVO.getActive());
            pstmt.setInt(11, cinemaVO.getState());

            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        cinema_no = rs.getString(i);
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
        return cinema_no;
    }

    @Override
    public void update(CinemaVO cinemaVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, cinemaVO.getCinema_name());
            pstmt.setString(2, cinemaVO.getCinema_engname());
            pstmt.setString(3, cinemaVO.getCinema_address());
            pstmt.setString(4, cinemaVO.getCinema_tel());
            pstmt.setString(5, cinemaVO.getIntroduction());
            pstmt.setString(6, cinemaVO.getTraffic());
            pstmt.setString(7, cinemaVO.getPhoto_title());
            pstmt.setString(8, cinemaVO.getPhoto_path());
            pstmt.setString(9, cinemaVO.getPhoto_small());
            pstmt.setInt(10, cinemaVO.getActive());
            pstmt.setInt(11, cinemaVO.getState());
            pstmt.setString(12, cinemaVO.getCinema_no());

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
    public void delete(String cinema_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, cinema_no);

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
    public CinemaVO findByPrimaryKey(String cinema_no) {
        CinemaVO cinemaVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, cinema_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // cinemaVO 也稱為 Domain objects
                cinemaVO = new CinemaVO();
                cinemaVO.setCinema_no(rs.getString("CINEMA_NO"));
                cinemaVO.setCinema_name(rs.getString("CINEMA_NAME"));
                cinemaVO.setCinema_engname(rs.getString("CINEMA_ENGNAME"));
                cinemaVO.setCinema_address(rs.getString("CINEMA_ADDRESS"));
                cinemaVO.setCinema_tel(rs.getString("CINEMA_TEL"));
                cinemaVO.setIntroduction(rs.getString("INTRODUCTION"));
                cinemaVO.setTraffic(rs.getString("TRAFFIC"));
                cinemaVO.setPhoto_title(rs.getString("PHOTO_TITLE"));
                cinemaVO.setPhoto_path(rs.getString("PHOTO_PATH"));
                cinemaVO.setPhoto_small(rs.getString("PHOTO_SMALL"));
                cinemaVO.setActive(rs.getInt("ACTIVE"));
                cinemaVO.setState(rs.getInt("STATE"));
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
        return cinemaVO;
    }

    @Override
    public List<CinemaVO> getAll() {
        List<CinemaVO> list = new ArrayList<CinemaVO>();
        CinemaVO cinemaVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // cinemaVO 也稱為 Domain objects
                cinemaVO = new CinemaVO();
                cinemaVO.setCinema_no(rs.getString("CINEMA_NO"));
                cinemaVO.setCinema_name(rs.getString("CINEMA_NAME"));
                cinemaVO.setCinema_engname(rs.getString("CINEMA_ENGNAME"));
                cinemaVO.setCinema_address(rs.getString("CINEMA_ADDRESS"));
                cinemaVO.setCinema_tel(rs.getString("CINEMA_TEL"));
                cinemaVO.setIntroduction(rs.getString("INTRODUCTION"));
                cinemaVO.setTraffic(rs.getString("TRAFFIC"));
                cinemaVO.setPhoto_title(rs.getString("PHOTO_TITLE"));
                cinemaVO.setPhoto_path(rs.getString("PHOTO_PATH"));
                cinemaVO.setPhoto_small(rs.getString("PHOTO_SMALL"));
                cinemaVO.setActive(rs.getInt("ACTIVE"));
                cinemaVO.setState(rs.getInt("STATE"));
                list.add(cinemaVO); // Store the row in the list
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
