package com.meal.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MealJNDIDAO implements MealDAO_Interface{

	private static DataSource ds = null;

	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/T3MS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	private static final String INSERT_STMT = "INSERT INTO MEAL (Meal_no, Cinema_no, Meal_name, Meal_food, Meal_price, Meal_photo, Extension, Photo_title, Meal_active) VALUES (MEAL_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT * FROM MEAL";
//	private static final String GET_ONE_STMT = "SELECT * FROM MEAL WHERE Meal_no = ?";
//	private static final String DELETE = "DELETE FROM MEAL WHERE Meal_no = ?";
//	private static final String UPDATE = 
//			"UPDATE MEAL SET Cinema_no = ?, Meal_name = ?, Meal_food = ?, Meal_price = ?, Meal_photo = ?, Extension = ?, Photo_title = ?, Meal_active = ? WHERE Meal_no = ?";
	
	private static final String INSERT_STMT = "INSERT INTO MEAL (Meal_no, Cinema_no, Meal_name, Meal_food, Meal_price, Meal_photo, Extension, Photo_title, Meal_active)" 
			+" VALUES ('ML'||LAPD(MEAL_SEQ.NEXTVAL,4,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEAL";
	private static final String GET_ONE_STMT = "SELECT * FROM MEAL WHERE Meal_no = ?";
	private static final String DELETE = "DELETE FROM MEAL WHERE Meal_no = ?";
	private static final String UPDATE = 
			"UPDATE MEAL SET Cinema_no = ?, Meal_name = ?, Meal_food = ?, Meal_price = ?, Meal_photo = ?, Extension = ?, Photo_title = ?, Meal_active = ? WHERE Meal_no = ?";



	@Override
	public void add(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealVO.getCinema_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setString(3, mealVO.getMeal_food());
			pstmt.setInt(4, mealVO.getMeal_price());
			pstmt.setBytes(5, mealVO.getMeal_photo());
			pstmt.setString(6, mealVO.getExtension());
			pstmt.setString(7, mealVO.getPhoto_title());
			pstmt.setInt(8, mealVO.getMeal_active());



			pstmt.executeUpdate();			

		} catch (SQLException se)
		{
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally
		{
			if (pstmt != null)
			{
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if (con != null)
			{
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MealVO mealVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealVO.getCinema_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setString(3, mealVO.getMeal_food());
			pstmt.setInt(4, mealVO.getMeal_price());
			pstmt.setBytes(5, mealVO.getMeal_photo());
			pstmt.setString(6, mealVO.getExtension());
			pstmt.setString(7, mealVO.getPhoto_title());
			pstmt.setInt(8, mealVO.getMeal_active());	
			pstmt.setString(9, mealVO.getMeal_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {

			if (pstmt != null) 
			{
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) 
			{
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String mealNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mealNo);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MealVO findByPK(String mealNo) {

		MealVO mealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mealNo);

			rs = pstmt.executeQuery();

			while (rs.next())
			{
				mealVO = new MealVO();

				mealVO.setMeal_no(rs.getString("meal_no"));

				mealVO.setCinema_no(rs.getString("cinema_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_food(rs.getString("meal_food"));
				mealVO.setMeal_price(rs.getInt("meal_price"));
				mealVO.setMeal_photo(rs.getBytes("meal_photo"));
				mealVO.setExtension(rs.getString("extension"));
				mealVO.setPhoto_title(rs.getString("photo_title"));
				mealVO.setMeal_active(rs.getInt("meal_active"));


			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		return mealVO;
	}

	@Override
	public List<MealVO> getAll() {
		List<MealVO> list = new ArrayList<>();
		MealVO mealVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				mealVO = new MealVO();

				mealVO.setMeal_no(rs.getString("meal_no"));
				mealVO.setCinema_no(rs.getString("cinema_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_food(rs.getString("meal_food"));
				mealVO.setMeal_price(rs.getInt("meal_price"));
				mealVO.setMeal_photo(rs.getBytes("meal_photo"));
				mealVO.setExtension(rs.getString("extension"));
				mealVO.setPhoto_title(rs.getString("photo_title"));
				mealVO.setMeal_active(rs.getInt("meal_active"));

				list.add(mealVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
