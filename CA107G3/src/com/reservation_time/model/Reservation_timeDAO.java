package com.reservation_time.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.restaurant_transaction_list.model.RES_transaction_listDAO;
import com.restaurant_transaction_list.model.RES_transaction_listVO;

public class Reservation_timeDAO implements Reservation_timeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WEST";
	String passwd = "800627";
	
	
	private static final String INSERT_STMT = "INSERT INTO RESERVATION_TIME VALUES('RT'||LPAD(to_char(RESERVATION_T_SEQ.NEXTVAL), 8, '0'),?,?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT RT_NO, VENDOR_NO, R_TIME FROM RESERVATION_TIME ORDER by RT_NO";
	
	private static final String GET_ONE_STMT = 
			"SELECT RT_NO, VENDOR_NO, R_TIME FROM RESERVATION_TIME WHERE RT_NO =?";
	
	private static final String DELETE = 
			"DELETE FROM RESERVATION_TIME WHERE RT_NO = ?";
	private static final String UPDATE = 
			"UPDATE RESERVATION_TIME SET VENDOR_NO=?, R_TIME=? where RT_NO=?";
	
	@Override
	public void insert(Reservation_TimeVO reservation_timeVO) {
		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, reservation_timeVO.getVendor_no());
//			pstmt.setString(2, reservation_timeVO.getR_time());
//			
//
//			pstmt.executeUpdate();
//				System.out.println("OK1");
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}


	}

	@Override
	public void update(Reservation_TimeVO reservation_timeVO) {
		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, reservation_timeVO.getVendor_no() );
//			pstmt.setString(2, reservation_timeVO.getR_time() );
//			pstmt.setString(3, reservation_timeVO.getRt_no() );
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}


	}

	@Override
	public void delete(String rt_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, rt_no);
//
//			pstmt.executeUpdate();
//				System.out.println("OKOK");
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}


	


	}

	@Override
	public Reservation_TimeVO findByPrimaryKey(String rt_no) {
		Reservation_TimeVO reservation_timeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reservation_timeVO = new Reservation_TimeVO();
				reservation_timeVO.setRt_no(rs.getString("rt_no"));
				reservation_timeVO.setVendor_no(rs.getString("vendor_no"));
				reservation_timeVO.setR_time(rs. getString("r_time"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		
	return reservation_timeVO;
		
	}

	@Override
	public List<Reservation_TimeVO> getAll() {
		List<Reservation_TimeVO> list = new ArrayList<Reservation_TimeVO>();
		Reservation_TimeVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				resVO = new Reservation_TimeVO();
				resVO.setRt_no(rs.getString("rt_no"));
				resVO.setVendor_no(rs.getString("vendor_no"));
				resVO.setR_time(rs. getString("r_time"));
				
				
				list.add(resVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public List<Reservation_TimeVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		
		Reservation_timeDAO dao = new Reservation_timeDAO();
		
		Reservation_TimeVO resVO1 = new Reservation_TimeVO();
		
		//insert
//		resVO1.setVendor_no("V000003");
//		resVO1.setR_time("2100");
//	
//		dao.insert(resVO1);
//		System.out.println(resVO1);
		
		
		//update
//		Reservation_TimeVO resVO2 = new Reservation_TimeVO();
//		resVO2 .setRt_no("RT00000009");
//		resVO2 .setVendor_no("V000004");
//		resVO2.setR_time("1100");
//		
//		System.out.println(resVO2);
//		dao.update(resVO2);
//		System.out.println("OK");
		
		
		//delete
//		dao.delete("RT00000007");
//		System.out.println("OK");
		
		//findByPrimaryKey
		
//		Reservation_TimeVO resVO3 = dao.findByPrimaryKey("RT00000001");
//		System.out.print(resVO3.getRt_no() + ",");
//		System.out.print(resVO3.getVendor_no() + ",");
//		System.out.print(resVO3.getR_time() + ",");
//		
//		
//		System.out.println("---------------------");
		
		//findAll
		List<Reservation_TimeVO> list = dao.getAll();
		for (Reservation_TimeVO res : list) {
			System.out.print(res.getRt_no() + ",");
			System.out.print(res.getVendor_no() + ",");
			System.out.print(res.getR_time() + ",");
			
			
			System.out.println("-------------------");
		
		}
	}

}
