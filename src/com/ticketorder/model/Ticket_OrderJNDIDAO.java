package com.ticketorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.crypto.provider.RSACipher;
import com.sun.javafx.collections.MappingChange.Map;
import com.ticketdetail.model.Ticket_DetailJNDIDAO;
import com.ticketdetail.model.Ticket_DetailVO;

public class Ticket_OrderJNDIDAO implements Ticket_OrderDAO_Interface {

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
	// TICKET_ORDER <- TABLE NAME

	// INSERT INTO TICKET_ORDER
	// (ORDER_NO,MEM_NO,MEAL_NO,UUID,AMOUNT,ORDER_STATE,PAYMENT_STATE,CREATED_AT,UPDATED_AT,EXCHANGE_STATE,CREDIT_CARD,DEADLINE,AUTH_KEY)
	// VALUES('TO'||LPAD(TO_CHAR(TICKET_ORDER_SEQ.NEXTVAL),4,'0'),'M0001','F0001',SYS_GUID(),550,1,1,CURRENT_TIMESTAMP,
	// CURRENT_TIMESTAMP,1,'1234-5678-1234-5678','01/22','724');

	private static final String INSERT_STMT = "INSERT INTO TICKET_ORDER(ORDER_NO,MEM_NO,MEAL_NO,UUID,AMOUNT,ORDER_STATE,PAYMENT_STATE,CREATED_AT,UPDATED_AT,EXCHANGE_STATE,CREDIT_CARD,DEADLINE,AUTH_KEY) "
			+ "VALUES('TOR'||LPAD(TO_CHAR(TICKET_ORDER_SEQ.NEXTVAL),6,'0'),?,?,SYS_GUID(),?,?,?,"
			+ "CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE TICKET_ORDER SET AMOUNT=?, ORDER_STATE=?, PAYMENT_STATE=?, UPDATED_AT=CURRENT_TIMESTAMP, EXCHANGE_STATE=?, WHERE ORDER_NO=?";
	private static final String DELETE_STMT = "DELETE FROM TICKET_ORDER WHERE ORDER_NO=?";
	private static final String GET_ALL_OF_A_MEMNO_STMT = "SELECT * FROM TICKET_ORDER WHERE MEM_NO=? ORDER BY CREATED_AT DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM TICKET_ORDER WHERE ORDER_NO=?";
	private static final String UPDATEAMOUNT = "UPDATE TICKET_ORDER SET AMOUNT=? WHERE UUID=?";
	private static final String GET_ONE_ORDER_BY_UUID = "SELECT * FROM TICKET_ORDER WHERE UUID=?";
	private static final String GET_DETAIL_STMT = "SELECT TICKET_ORDER.UUID, TICKET_DETAIL.ORDER_NO,TICKET_DETAIL.SEAT FROM TICKET_ORDER "
			+ "INNER JOIN TICKET_DETAIL ON TICKET_ORDER.ORDER_NO=TICKET_DETAIL.ORDER_NO WHERE UUID=?";

	@Override
	public String insert_con(Ticket_OrderVO ticket_OrderVO, HashMap<String, String> seat_ticketType,
			String session_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] cols = { "ORDER_NO" };
		String order_no = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, ticket_OrderVO.getMem_no());
			pstmt.setString(2, ticket_OrderVO.getMeal_no());
			pstmt.setString(3, ticket_OrderVO.getAmount());
			pstmt.setInt(4, ticket_OrderVO.getOrder_state());
			pstmt.setInt(5, ticket_OrderVO.getPayment_state());
			pstmt.setInt(6, ticket_OrderVO.getExchange_state());
			pstmt.setString(7, ticket_OrderVO.getCredit_card());
			pstmt.setString(8, ticket_OrderVO.getDeadline());
			pstmt.setString(9, ticket_OrderVO.getAuth_key());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				order_no = rs.getString(1);
				System.out.println("pkey=" + order_no);
			} else {
				System.out.println("pkey error");
			}
			rs.close();

			// 一張訂單產生多個明細
			Ticket_DetailJNDIDAO dao = new Ticket_DetailJNDIDAO();
			Ticket_DetailVO ticket_DetailVO = null;

			for (String key : seat_ticketType.keySet()) {
				ticket_DetailVO = new Ticket_DetailVO();
				ticket_DetailVO.setOrder_no(order_no);
				ticket_DetailVO.setSession_no(session_no);
				ticket_DetailVO.setTicketType_no(seat_ticketType.get(key));
				ticket_DetailVO.setSeat(key);
				dao.insertConn(ticket_DetailVO, conn);
			}

			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.println("rollback is being");
					conn.rollback();
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error occurred. " + e1.getMessage());
				}
			}
			throw new RuntimeException("database error occurred. " + e.getMessage());
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

		return order_no;
	}

	@Override
	public String insert(Ticket_OrderVO ticket_OrderVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] cols = { "ORDER_NO" };
		String order_no = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, ticket_OrderVO.getMem_no());
			pstmt.setString(2, ticket_OrderVO.getMeal_no());
			pstmt.setString(3, ticket_OrderVO.getAmount());
			pstmt.setInt(4, ticket_OrderVO.getOrder_state());
			pstmt.setInt(5, ticket_OrderVO.getPayment_state());
			pstmt.setInt(6, ticket_OrderVO.getExchange_state());
			pstmt.setString(7, ticket_OrderVO.getCredit_card());
			pstmt.setString(8, ticket_OrderVO.getDeadline());
			pstmt.setString(9, ticket_OrderVO.getAuth_key());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						order_no = rs.getString(i);
					}
				} while (rs.next());
			} else {
				System.out.println("order_no:none");
			}
			conn.commit();
			rs.close();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace(System.err);
				}
			}
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
		return order_no;
	}

	@Override
	public void update(String order_no, Integer amount, Integer order_state, Integer payment_state,
			Integer exchange_state, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, amount);
			pstmt.setInt(2, order_state);
			pstmt.setInt(3, payment_state);
			pstmt.setInt(4, exchange_state);
			pstmt.setString(5, order_no);
			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("Transaction failed rollback");
				} catch (SQLException se) {
					throw new RuntimeException("rollback error " + se.getMessage());
				}
			}
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
		}
	}

	@Override
	public void delete(Ticket_OrderVO ticket_OrderVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE_STMT);

			pstmt.setString(1, ticket_OrderVO.getOrder_no());
			pstmt.executeUpdate();
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
	}

	@Override
	public Ticket_OrderVO findByPrimaryKey(String order_no) {
		Ticket_OrderVO ticketVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticketVO = new Ticket_OrderVO();
				ticketVO.setOrder_no(rs.getString("ORDER_NO"));
				ticketVO.setMem_no(rs.getString("MEM_NO"));
				ticketVO.setMeal_no(rs.getString("MEAL_NO"));
				ticketVO.setUuid(rs.getString("UUID"));
				ticketVO.setAmount(rs.getString("AMOUNT"));
				ticketVO.setOrder_state(rs.getInt("ORDER_STATE"));
				ticketVO.setPayment_state(rs.getInt("PAYMENT_STATE"));
				ticketVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticketVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				ticketVO.setExchange_state(rs.getInt("EXCHANGE_STATE"));
				ticketVO.setCredit_card(rs.getString("CREDIT_CARD"));
				ticketVO.setDeadline(rs.getString("DEADLINE"));
				ticketVO.setAuth_key(rs.getString("AUTH_KEY"));
			}
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
		return ticketVO;
	}

	@Override
	public List<Ticket_OrderVO> findAllOrdersByMember(String mem_no) {
		List<Ticket_OrderVO> list = new ArrayList<>();
		Ticket_OrderVO ticketVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_OF_A_MEMNO_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticketVO = new Ticket_OrderVO();
				ticketVO.setOrder_no(rs.getString("ORDER_NO"));
				ticketVO.setMem_no(rs.getString("MEM_NO"));
				ticketVO.setMeal_no(rs.getString("MEAL_NO"));
				ticketVO.setUuid(rs.getString("UUID"));
				ticketVO.setAmount(rs.getString("AMOUNT"));
				ticketVO.setOrder_state(rs.getInt("ORDER_STATE"));
				ticketVO.setPayment_state(rs.getInt("PAYMENT_STATE"));
				ticketVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticketVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				ticketVO.setExchange_state(rs.getInt("EXCHANGE_STATE"));
				ticketVO.setCredit_card(rs.getString("CREDIT_CARD"));
				ticketVO.setDeadline(rs.getString("DEADLINE"));
				ticketVO.setAuth_key(rs.getString("AUTH_KEY"));
				list.add(ticketVO);
			}
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
	public void updateAmount(String uuid, Integer amount, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(UPDATEAMOUNT);
			pstmt.setInt(1, amount);
			pstmt.setString(2, uuid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
					System.err.println("Update Transaction failed... so rollback! ");
				} catch (SQLException e1) {
					throw new RuntimeException("rollback error!" + e1.getMessage());
				}
			}
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
		}
	}

	@Override
	public List<String> search_TicketDetail(String uuid) {
		List<String> seats = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_DETAIL_STMT);
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String seat = rs.getString("SEAT");
				seats.add(seat);
			}

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
		return seats;
	}

	@Override
	public Ticket_OrderVO find_oneOrder_by_uuid(String uuid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ticket_OrderVO ticketVO = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_ORDER_BY_UUID);
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ticketVO = new Ticket_OrderVO();
				ticketVO.setOrder_no(rs.getString("ORDER_NO"));
				ticketVO.setMem_no(rs.getString("MEM_NO"));
				ticketVO.setMeal_no(rs.getString("MEAL_NO"));
				ticketVO.setUuid(rs.getString("UUID"));
				ticketVO.setAmount(rs.getString("AMOUNT"));
				ticketVO.setOrder_state(rs.getInt("ORDER_STATE"));
				ticketVO.setPayment_state(rs.getInt("PAYMENT_STATE"));
				ticketVO.setCreated_at(rs.getTimestamp("CREATED_AT"));
				ticketVO.setUpdated_at(rs.getTimestamp("UPDATED_AT"));
				ticketVO.setExchange_state(rs.getInt("EXCHANGE_STATE"));
				ticketVO.setCredit_card(rs.getString("CREDIT_CARD"));
				ticketVO.setDeadline(rs.getString("DEADLINE"));
				ticketVO.setAuth_key(rs.getString("AUTH_KEY"));

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
		return ticketVO;
	}
}
