package com.exception_date.model;

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

public class Exception_dateDAO implements Exception_dateDAO_interface {

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WEST";
	String passwd = "800627";
	
	private static final String INSERT_STMT = "INSERT INTO EXCEPTION_DATE VALUES ('ED'||LPAD(to_char(EXCEPTION_DATE_SEQ.NEXTVAL), 8, '0'),?,?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT EXC_NO, VENDOR_NO, EXC_DATE FROM EXCEPTION_DATE ORDER by EXC_NO";
	
	private static final String GET_ONE_STMT = 
			"SELECT EXC_NO, VENDOR_NO, EXC_DATE FROM EXCEPTION_DATE WHERE EXC_NO =?";
	
	private static final String DELETE = 
			"DELETE FROM EXCEPTION_DATE WHERE EXC_NO = ?";
	private static final String UPDATE = 
			"UPDATE EXCEPTION_DATE SET VENDOR_NO=?, EXC_DATE=? where EXC_NO=?";
	
	
	
	@Override
	public void insert(Exception_dateVO exception_dateVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, exception_dateVO.getVendor_no());
//			pstmt.setDate(2, exception_dateVO.getExc_date());
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
	public void update(Exception_dateVO exception_dateVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, exception_dateVO.getVendor_no() );
//			pstmt.setDate(2, exception_dateVO.getExc_date() );
//			pstmt.setString(3, exception_dateVO.getExc_no());
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
	public void delete(String exc_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, exc_no);
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
	public Exception_dateVO findByPrimaryKey(String exc_no) {
		Exception_dateVO exception_dateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, exc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				exception_dateVO = new Exception_dateVO();
				exception_dateVO.setExc_no(rs.getString("exc_no"));
				exception_dateVO.setVendor_no(rs.getString("vendor_no"));
				exception_dateVO.setExc_date(rs. getDate("exc_date"));
				
				
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
		
	return exception_dateVO;
	}

	@Override
	public List<Exception_dateVO> getAll() {
		List<Exception_dateVO> list = new ArrayList<Exception_dateVO>();
		Exception_dateVO excVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				excVO = new Exception_dateVO();
				excVO.setExc_no(rs.getString("exc_no"));
				excVO.setVendor_no(rs.getString("vendor_no"));
				excVO.setExc_date(rs. getDate("exc_date"));
				
				
				list.add(excVO); // Store the row in the list
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
	public List<Exception_dateVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		
		Exception_dateDAO dao = new Exception_dateDAO();
		Exception_dateVO resVO1 = new Exception_dateVO();
		
		//insert
//		resVO1.setVendor_no("V000003");
//		resVO1.setExc_date(java.sql.Date.valueOf("2018-07-25"));
//		
//		
//		System.out.println(resVO1);
//		dao.insert(resVO1);
//		System.out.println("OKK");
		
		
		//update
//		Exception_dateVO resVO2 = new Exception_dateVO();
//		resVO2 .setExc_no("ED00000004");
//		resVO2 .setVendor_no("V000004");
//		resVO2.setExc_date(java.sql.Date.valueOf("2018-07-25"));
//		
//		System.out.println(resVO2);
//		dao.update(resVO2);
		
		//delete
//		dao.delete("ED00000007");
//		System.out.println("OK");
//		
//		//findByPrimaryKey
//		
//		Exception_dateVO resVO3 = dao.findByPrimaryKey("ED00000005");
//		System.out.print(resVO3.getExc_no() + ",");
//		System.out.print(resVO3.getVendor_no() + ",");
//		System.out.print(resVO3.getExc_date() + ",");
//		
////		
//		System.out.println("---------------------");
		
		//findAll
		
		List<Exception_dateVO> list = dao.getAll();
			for (Exception_dateVO exc : list) {
			System.out.print(exc.getExc_no() + ",");
			System.out.print(exc.getVendor_no() + ",");
			System.out.print(exc.getExc_date() + ",");
			
					
			System.out.println("-------------------");
				
				}
		
	}

}
