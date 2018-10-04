package com.ticketdetail.model;

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

import com.ticketorder.model.Ticket_OrderVO;

public class DetailTest {
	private static final String GET_ONES_STMT = 
			"SELECT * FROM TICKET_DETAIL";

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<Ticket_DetailVO> getAll() {
		List<Ticket_DetailVO> list = new ArrayList<Ticket_DetailVO>();
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONES_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Ticket_DetailVO tdVO = new Ticket_DetailVO();
			
				tdVO.setTicket_detail_no(rs.getString(1));
				tdVO.setOrder_no(rs.getString(2));
				tdVO.setSession_no(rs.getString(3));
				tdVO.setTicketType_no(rs.getString(4));
				tdVO.setSeat(rs.getString(5));
				tdVO.setCreated_at(rs.getTimestamp(6));
				tdVO.setUpdated_at(rs.getTimestamp(7));
				list.add(tdVO);
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
