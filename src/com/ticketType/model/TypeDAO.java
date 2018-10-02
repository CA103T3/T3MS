package com.ticketType.model;

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

import com.theater.model.TheaterVO;


public class TypeDAO implements TypeDAO_interface {

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

    // private static final String INSERT_STMT =
        // "INSERT INTO TICKETTYPE (TICKETTYPE_NO,THEATER_NO,IDENTITY,EQUIPMENT,TIME,PRICE) VALUES ('TT'||LPAD(to_char(TICKETTYPE_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?)";
    // private static final String GET_ALL_STMT =
        // "SELECT TICKETTYPE_NO,THEATER_NO,IDENTITY,EQUIPMENT,TIME,PRICE FROM TICKETTYPE order by TICKETTYPE_NO";
    // private static final String GET_ONE_STMT =
        // "SELECT TICKETTYPE_NO,THEATER_NO,IDENTITY,EQUIPMENT,TIME,PRICE FROM TICKETTYPE where TICKETTYPE_NO = ?";
    // private static final String UPDATE =
        // "UPDATE TICKETTYPE set THEATER_NO=?, IDENTITY=?, EQUIPMENT=?, TIME=?, PRICE=? where TICKETTYPE_NO = ?";

    private static final String INSERT_STMT =
        "INSERT INTO TICKETTYPE (TICKETTYPE_NO,THEATER_NO,IDENTITY,TIME,PRICE) VALUES ('TT'||LPAD(to_char(TICKETTYPE_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT TICKETTYPE_NO,THEATER_NO,IDENTITY,TIME,PRICE FROM TICKETTYPE order by TICKETTYPE_NO";
    private static final String GET_ONE_STMT =
        "SELECT TICKETTYPE_NO,THEATER_NO,IDENTITY,TIME,PRICE FROM TICKETTYPE where TICKETTYPE_NO = ?";
    private static final String DELETE =
        "DELETE FROM TICKETTYPE where TICKETTYPE_NO = ?";
    private static final String UPDATE =
        "UPDATE TICKETTYPE set THEATER_NO=?, IDENTITY=?, TIME=?, PRICE=? where TICKETTYPE_NO = ?";
    private static final String GET_ALL_OF_CINEMA_STMT =
        "SELECT TICKETTYPE_NO,TICKETTYPE.THEATER_NO,IDENTITY,TIME,PRICE,THEATER.THEATER_NAME FROM TICKETTYPE " +
        " left join THEATER on TICKETTYPE.THEATER_NO = THEATER.THEATER_NO " +
        " where TICKETTYPE.THEATER_NO in (select THEATER.THEATER_NO from THEATER where THEATER.CINEMA_NO = ?) order by TICKETTYPE_NO";

    @Override
    public String insert(TypeVO typeVO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String session_no = null;
        try {

            con = ds.getConnection();
            String[] cols = { "TICKETTYPE_NO" }; // 或 int cols[] = {1};
            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, typeVO.getTheater_no());
            pstmt.setString(2, typeVO.getIdentify());
            pstmt.setString(3, typeVO.getTime());
            pstmt.setInt(4, typeVO.getPrice());
            // pstmt.setString(3, typeVO.getEquipment());
            // pstmt.setString(4, typeVO.getTime());
            // pstmt.setInt(5, typeVO.getPrice());

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
    public void update(TypeVO typeVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, typeVO.getTheater_no());
            pstmt.setString(2, typeVO.getIdentify());
            pstmt.setString(3, typeVO.getTime());
            pstmt.setInt(4, typeVO.getPrice());
            pstmt.setString(5, typeVO.getType_no());
            // pstmt.setString(3, typeVO.getEquipment());
            // pstmt.setString(4, typeVO.getTime());
            // pstmt.setInt(5, typeVO.getPrice());
            // pstmt.setString(6, typeVO.getType_no());

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
    public void delete(String type_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, type_no);

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
    public TypeVO findByPrimaryKey(String type_no) {
        TypeVO typeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, type_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // typeVO 也稱為 Domain objects
                typeVO = new TypeVO();
                typeVO.setType_no(rs.getString("TICKETTYPE_NO"));
                typeVO.setTheater_no(rs.getString("THEATER_NO"));
                typeVO.setIdentify(rs.getString("IDENTITY"));
                // typeVO.setEquipment(rs.getString("EQUIPMENT"));
                typeVO.setTime(rs.getString("TIME"));
                typeVO.setPrice(rs.getInt("PRICE"));
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
        return typeVO;
    }

    @Override
    public List<TypeVO> getAll() {
        List<TypeVO> list = new ArrayList<TypeVO>();
        TypeVO typeVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // typeVO 也稱為 Domain objects
                typeVO = new TypeVO();
                typeVO.setType_no(rs.getString("TICKETTYPE_NO"));
                typeVO.setTheater_no(rs.getString("THEATER_NO"));
                typeVO.setIdentify(rs.getString("IDENTITY"));
                // typeVO.setEquipment(rs.getString("EQUIPMENT"));
                typeVO.setTime(rs.getString("TIME"));
                typeVO.setPrice(rs.getInt("PRICE"));
                list.add(typeVO); // Store the row in the list
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
    public List<TypeVO> getAllofCinema(String cinema_no) {
        List<TypeVO> list = new ArrayList<TypeVO>();
        TypeVO typeVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_OF_CINEMA_STMT);
            pstmt.setString(1, cinema_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // typeVO 也稱為 Domain objects
                typeVO = new TypeVO();
                typeVO.setType_no(rs.getString("TICKETTYPE_NO"));
                typeVO.setTheater_no(rs.getString("THEATER_NO"));
                typeVO.setIdentify(rs.getString("IDENTITY"));
                // typeVO.setEquipment(rs.getString("EQUIPMENT"));
                typeVO.setTime(rs.getString("TIME"));
                typeVO.setPrice(rs.getInt("PRICE"));
                TheaterVO theaterVO = new TheaterVO();
                theaterVO.setTheater_name(rs.getString("THEATER_NAME"));
                typeVO.setTheaterVO(theaterVO);
                list.add(typeVO); // Store the row in the list
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
