package com.ticketorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.movie_introduce.model.Movie_IntroduceVO;

public class Ticket_testDAO {
	private static final String GET_ONES_STMT = "SELECT * FROM TICKET_ORDER  WHERE MEM_NO=?";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Ticket_OrderVO> getAll(String memno) {
		List<Ticket_OrderVO> list = new ArrayList<Ticket_OrderVO>();
		Ticket_OrderVO tVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONES_STMT);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Movie_IntroduceVO 也稱為 Domain objects
				tVO = new Ticket_OrderVO();
				tVO.setOrder_no(rs.getString(1));
				tVO.setMem_no(rs.getString(2));
				tVO.setMeal_no(rs.getString(3));
				tVO.setUuid(rs.getString(4));
				tVO.setAmount(rs.getString(5));
				tVO.setOrder_state(rs.getInt(6));
				tVO.setPayment_state(rs.getInt(7));
				tVO.setCreated_at(rs.getTimestamp(8));
				tVO.setUpdated_at(rs.getTimestamp(9));
				tVO.setExchange_state(rs.getInt(10));
				list.add(tVO);
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
