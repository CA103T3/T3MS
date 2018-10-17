package com.ticketdetail.model;

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

import com.session.model.SessionService;
import com.ticketorder.model.Ticket_OrderService;
import com.ticketorder.model.Ticket_OrderVO;

public class Ticket_DetailJNDIDAO implements Ticket_DetailDAO_Interface {

	public static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO TICKET_DETAIL (TICKET_DETAIL_NO,ORDER_NO,SESSION_NO,TICKETTYPE_NO,SEAT,CREATED_AT,UPDATED_AT) VALUES('TDA'||LPAD(TO_CHAR(TICKET_DETAIL_SEQ.NEXTVAL),6,'0'),?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
	private static final String DELETE_ONE_TICKETDETAIL_STMT = "DELETE FROM TICKET_DETAIL WHERE TICKET_DETAIL_NO=?";
	private static final String DELETE_ONE_TICKETORDER_STMT = "DELETE FROM TICKET_ORDER WHERE UUID=?";
	private static final String GET_ONE_TICKETDETAIL_STMT = "SELECT * FROM TICKET_DETAIL WHERE TICKET_DETAIL_NO=?";
	private static final String GET_ONE_TICKETORDER_STMT = "SELECT TICKET_DETAIL.TICKET_DETAIL_NO,TICKET_DETAIL.ORDER_NO,TICKET_DETAIL.SESSION_NO,TICKET_DETAIL.TICKETTYPE_NO,TICKET_DETAIL.SEAT,TICKET_DETAIL.CREATED_AT,TICKET_DETAIL.UPDATED_AT "
			+ "FROM TICKET_DETAIL " + "LEFT JOIN TICKET_ORDER " + "ON TICKET_ORDER.ORDER_NO=TICKET_DETAIL.ORDER_NO "
			+ "WHERE TICKET_ORDER.UUID=?";
	private static final String GET_ONE_TICKETDETAIL_BY_ORDERNO_STMT = "SELECT * FROM TICKET_DETAIL WHERE ORDER_NO=?";
	private static final String GET_TICKETDETAIL_SESSION_STMT = "SELECT * FROM TICKET_DETAIL WHERE SESSION_NO=?";
	private static final String DELETE_ONE_DETAIL_UPDATE_SEAT_STMT = "DELETE TICKET_DETAIL WHERE EXISTS(SELECT 1 FROM TICKET_ORDER WHERE TICKET_DETAIL.ORDER_NO=TICKET_ORDER.ORDER_NO AND UUID=? AND SEAT=?)";

	@Override
	public String insert(Ticket_DetailVO ticket_DetailVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] cols = { "TICKET_DETAIL_NO" };
		String pkey = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, ticket_DetailVO.getOrder_no());
			pstmt.setString(2, ticket_DetailVO.getSession_no());
			pstmt.setString(3, ticket_DetailVO.getTicketType_no());
			pstmt.setString(4, ticket_DetailVO.getSeat());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						pkey = rs.getString(i);
					}
				} while (rs.next());
			} else {
				System.err.println("no pkey from ticket_detail");
			}
			conn.commit();

			rs.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException("A database error occurred. " + e.getMessage());
			}

		} finally {
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
		return pkey;
	}

	// 回傳刪除幾張票
	@Override
	public Integer deleteOneTicketDetail(String ticket_detail_no) {
		int delCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_ONE_TICKETDETAIL_STMT);
			pstmt.setString(1, ticket_detail_no);
			delCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
		} finally {
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
		return delCount;
	}

	// 若要刪除明細時只剩一筆，就連同訂單一起刪除
	@Override
	public void deleteOneTicketOrder(String uuid, Connection conn) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_ONE_TICKETORDER_STMT);
			pstmt.setString(1, uuid);
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("deleteOneTicketOrder OK");
		} catch (SQLException e) {
			try {
				conn.rollback();
				System.err.println("Delete order failed ... so rollback!");
			} catch (SQLException e1) {
				e1.printStackTrace(System.err);
				System.err.println("rollback error!");
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// 查一張票明細
	@Override
	public Ticket_DetailVO findOneTicketDetail(String ticket_detail_no) {
		Ticket_DetailVO ticket_DetailVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_TICKETDETAIL_STMT);
			pstmt.setString(1, ticket_detail_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticket_DetailVO = new Ticket_DetailVO();
				ticket_DetailVO.setTicket_detail_no(rs.getString("TICKET_DETAIL_NO"));
				ticket_DetailVO.setOrder_no(rs.getString("ORDER_NO"));
				ticket_DetailVO.setSession_no(rs.getString("SESSION_NO"));
				ticket_DetailVO.setTicketType_no(rs.getString("TICKETTYPE_NO"));
				ticket_DetailVO.setSeat(rs.getString("SEAT"));
				ticket_DetailVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticket_DetailVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
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
		return ticket_DetailVO;
	}

	@Override
	public Ticket_DetailVO find_TicketDetail_By_OrderNo(String order_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ticket_DetailVO ticket_DetailVO = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_TICKETDETAIL_BY_ORDERNO_STMT);

			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ticket_DetailVO = new Ticket_DetailVO();
				ticket_DetailVO.setTicket_detail_no(rs.getString("TICKET_DETAIL_NO"));
				ticket_DetailVO.setOrder_no(rs.getString("ORDER_NO"));
				ticket_DetailVO.setSession_no(rs.getString("SESSION_NO"));
				ticket_DetailVO.setTicketType_no(rs.getString("TICKETTYPE_NO"));
				ticket_DetailVO.setSeat(rs.getString("SEAT"));
				ticket_DetailVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticket_DetailVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
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
		return ticket_DetailVO;
	}

	// 查一筆訂單(可能剩一筆明細，若明細刪除必須連訂單一起刪除)
	@Override
	public List<Ticket_DetailVO> find_ticketDetail_list(String uuid) {
		List<Ticket_DetailVO> list = new ArrayList<>();
		Ticket_DetailVO ticket_DetailVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_TICKETORDER_STMT);
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();
			System.out.println("findOneTicketOrder====Start====");
			while (rs.next()) {
				ticket_DetailVO = new Ticket_DetailVO();
				ticket_DetailVO.setTicket_detail_no(rs.getString("TICKET_DETAIL_NO"));
				ticket_DetailVO.setOrder_no(rs.getString("ORDER_NO"));
				ticket_DetailVO.setSession_no(rs.getString("SESSION_NO"));
				ticket_DetailVO.setTicketType_no(rs.getString("TICKETTYPE_NO"));
				ticket_DetailVO.setSeat(rs.getString("SEAT"));
				ticket_DetailVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticket_DetailVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				list.add(ticket_DetailVO);
				System.out.println(list);
			}
			System.out.println("findOneTicketOrder====End======");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
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

	@Override
	public void insertConn(Ticket_DetailVO ticket_DetailVO, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ticket_DetailVO.getOrder_no());
			pstmt.setString(2, ticket_DetailVO.getSession_no());
			pstmt.setString(3, ticket_DetailVO.getTicketType_no());
			pstmt.setString(4, ticket_DetailVO.getSeat());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("transaction failed ... so rollback!");
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error!" + e1.getMessage());
				}
			}
			throw new RuntimeException("transaction add ticket failed" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Ticket_DetailVO> findBySession(String session_no) {
		List<Ticket_DetailVO> list = new ArrayList<>();
		Ticket_DetailVO ticket_DetailVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_TICKETDETAIL_SESSION_STMT);
			pstmt.setString(1, session_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticket_DetailVO = new Ticket_DetailVO();
				ticket_DetailVO.setTicket_detail_no(rs.getString("TICKET_DETAIL_NO"));
				ticket_DetailVO.setOrder_no(rs.getString("ORDER_NO"));
				ticket_DetailVO.setSession_no(rs.getString("SESSION_NO"));
				ticket_DetailVO.setTicketType_no(rs.getString("TICKETTYPE_NO"));
				ticket_DetailVO.setSeat(rs.getString("SEAT"));
				ticket_DetailVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticket_DetailVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				list.add(ticket_DetailVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occurred. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public void delete_UpdateTicketOrder_UpdateMovieSession(String ticket_detail_no, String order_no, Integer amount,
			String session_no, String seat_table) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_TICKETORDER_STMT);
			conn.setAutoCommit(false);

			// Delete ticket
			pstmt = conn.prepareStatement(DELETE_ONE_TICKETDETAIL_STMT);
			pstmt.setString(1, ticket_detail_no);
			pstmt.executeUpdate();

			// Modifed order Amount
			Ticket_OrderService orderSvc = new Ticket_OrderService();
			orderSvc.updateAmount(order_no, amount, conn);

			// Modifed ticket_detail seat
			SessionService sessionSvc = new SessionService();
			sessionSvc.updateSessionSeat(seat_table, session_no, conn);

			// Check order have ticket, if none execute delete
			Ticket_DetailService ticket_DetailSvc = new Ticket_DetailService();
			List<Ticket_DetailVO> ticketList = ticket_DetailSvc.find_ticketDetail_list(order_no);

			if (ticketList.size() == 1) {
				ticket_DetailSvc.deleteOneTicketOrder(order_no, conn);
			}

			conn.commit();
			conn.setAutoCommit(true);

			System.out.println("Update a ticket_order OK");
		} catch (SQLException e) {
			e.printStackTrace(System.err);
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("transaction failed ... so rollback!");
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error! " + e1.getMessage());
				}
			}
			throw new RuntimeException("transaction update ticket failed" + e.getMessage());
		} finally {
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
	}

	// 先刪除訂單中的一筆座位，再修改訂單總金額，再修改該場次座位，再檢查訂單是否還有明細，若無則刪除訂單
	@Override
	public void delete_one_detail_update_seat(String uuid, String seat, Integer amount, String session_no,
			String seat_table) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_ONE_DETAIL_UPDATE_SEAT_STMT);
			pstmt.setString(1, uuid);
			pstmt.setString(2, seat);

			pstmt.executeUpdate();

			Ticket_OrderService ticket_OrderSvc = new Ticket_OrderService();
			ticket_OrderSvc.updateAmount(uuid, amount, conn);
			new SessionService().updateSessionSeat(seat_table, session_no, conn);
			Ticket_OrderVO orderVO = ticket_OrderSvc.find_oneOrder_by_uuid(uuid);
			String order_no = orderVO.getOrder_no();
			System.out.println(order_no);
			Ticket_DetailService tDetailSvc = new Ticket_DetailService();

			List<Ticket_DetailVO> lDetailVOs = tDetailSvc.find_ticketDetail_list(uuid);
			System.out.println("Ticket_DetailVO" + lDetailVOs);
			System.out.println("Ticket_DetailVO" + lDetailVOs.toString());
			System.out.println("Ticket_DetailVO.Size=" + lDetailVOs.size());
			if (orderVO.getAmount() == "0") {
				tDetailSvc.deleteOneTicketOrder(uuid, conn);
			}
			if (lDetailVOs.size() == 1) {
				tDetailSvc.deleteOneTicketOrder(uuid, conn);
				System.out.println("delete Ticket Order,because there is only one ticket left!");
			}
			System.out.println("===========Ticket_Detail_controller==================");
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("Update a ticket_order OK");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("transaction failed ... so rollback!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace(System.err);
					throw new RuntimeException("rollback error! " + e1.getMessage());
				}
				throw new RuntimeException("transaction update ticket failed" + e.getMessage());
			}
		} finally {
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

	}

}
