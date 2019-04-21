package com.ord.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;



public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("Please insert ord_no");
					System.out.println("13");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/ord/select_page.jsp");
					
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				String ord_no = null;
				try {
					ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("error ord_no");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
				if (ordVO == null) {
					errorMsgs.add("invaild ord_no");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫取出的empVO物件,存入req
				String url = "/ord/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("can not find ord_no detail:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ord/ord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				System.out.println("9999999999999999");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String ord_no = new String(req.getParameter("ord_no"));
				
				/***************************2.開始查詢資料****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ordVO", ordVO);         // 資料庫取出的empVO物件,存入req
				String url = "/ord/ord/update_ord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/listAllOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("upte");
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ord_no = new String(req.getParameter("ord_no").trim());
				
				String mem_no = req.getParameter("mem_no");
				
				String vendor_no  = req.getParameter("vendor_no");
				String tbl_no ="";
				try {
					tbl_no = new String(req.getParameter("tbl_no").trim());
				} catch (Exception e) {
					
					errorMsgs.add("please insert tbl_no.");
				}
				Integer party_size =new Integer(req.getParameter("party_size"));
				
				
				String share_mem_no1 =req.getParameter("share_mem_no1");
				String share_mem_no2 =req.getParameter("share_mem_no2");
				Integer share_amount =new Integer(req.getParameter("share_amount"));
				java.sql.Timestamp ord_time =java.sql.Timestamp.valueOf(req.getParameter("ord_time"));
				java.sql.Date booking_date=null;
				try {
					booking_date = java.sql.Date.valueOf(req.getParameter("booking_date").trim());
				} catch (IllegalArgumentException e) {
					booking_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("please choose date!");
				}
				String booking_time=new String(req.getParameter("booking_time").trim());
				if (booking_time == null || booking_time.trim().length() == 0) {
					errorMsgs.add("booking_time must be insert");
				}
				String notes=req.getParameter("notes");
				Integer total=null;
				try {
					total = new Integer(req.getParameter("total").trim());
					} catch (NumberFormatException e) {
						total = 0;
						errorMsgs.add("please. insert right total");
					}catch (NullPointerException b ) {
					System.out.println("66666666");
					total = 0;
						errorMsgs.add("please insert total number");}
				
				String arrival_time=req.getParameter("arrival_time");
				String finish_time=req.getParameter("finish_time");
				String verif_code=req.getParameter("verif_code");
				
				Integer status=null;
				try {
				status = new Integer(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
				status = 0;
					errorMsgs.add("please. insert right status number");
				}catch (NullPointerException e) {
				System.out.println("66666666");
					status = 0;
					errorMsgs.add("please insert  status number");
				}
//				Integer status=new Integer(req.getParameter("status"));
				
				
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

				
//					System.out.println(java.sql.Timestamp((System.currentTimeMillis())));
				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_no(ord_no);
				ordVO.setMem_no(mem_no);
				ordVO.setVendor_no(vendor_no);
				ordVO.setTbl_no(tbl_no);
				ordVO.setParty_size(party_size);
				ordVO.setShare_mem_no1(share_mem_no1);
				ordVO.setShare_mem_no2(share_mem_no2);
				ordVO.setShare_amount(share_amount);
				ordVO.setOrd_time(ord_time);
				ordVO.setBooking_date(booking_date);
				ordVO.setBooking_time(booking_time);
				ordVO.setNotes(notes);
				ordVO.setTotal(total);
				ordVO.setArrival_time(arrival_time);
				ordVO.setFinish_time(finish_time);
				ordVO.setVerif_code(verif_code);
				ordVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/ord/update_ord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ord_no, mem_no, vendor_no, tbl_no, party_size, share_mem_no1, share_mem_no2, share_amount, ord_time, booking_date, booking_time, notes, total, arrival_time, finish_time, verif_code, status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/ord/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/update_ord_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("insert");
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no");
				
				String vendor_no  = req.getParameter("vendor_no");
				String tbl_no =req.getParameter("tbl_no");
				Integer party_size =new Integer(req.getParameter("party_size"));
				String share_mem_no1 =req.getParameter("share_mem_no1");
				String share_mem_no2 =req.getParameter("share_mem_no2");
				Integer share_amount =new Integer(req.getParameter("share_amount"));
				java.sql.Timestamp ord_time =java.sql.Timestamp.valueOf(req.getParameter("ord_time"));
				java.sql.Date booking_date=null;
				try {
					booking_date = java.sql.Date.valueOf(req.getParameter("booking_date").trim());
				} catch (IllegalArgumentException e) {
					booking_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("please choose date!");
				}
				String booking_time=req.getParameter("booking_time");
				String notes=req.getParameter("notes");
				Integer total=null;
				try {
					total = new Integer(req.getParameter("total").trim());
					} catch (NumberFormatException e) {
						total = 0;
						errorMsgs.add("please. insert right total");
					}catch (NullPointerException b ) {
					System.out.println("66666666");
					total = 0;
						errorMsgs.add("please insert total number");}
				
				String arrival_time=req.getParameter("arrival_time");
				String finish_time=req.getParameter("finish_time");
				String verif_code=req.getParameter("verif_code");
				Integer status=new Integer(req.getParameter("status"));
				
				

				OrdVO ordVO = new OrdVO();
				
				ordVO.setMem_no(mem_no);
				ordVO.setVendor_no(vendor_no);
				ordVO.setTbl_no(tbl_no);
				ordVO.setParty_size(party_size);
				ordVO.setShare_mem_no1(share_mem_no1);
				ordVO.setShare_mem_no2(share_mem_no2);
				ordVO.setShare_amount(share_amount);
				ordVO.setOrd_time(ord_time);
				ordVO.setBooking_date(booking_date);
				ordVO.setBooking_time(booking_time);
				ordVO.setNotes(notes);
				ordVO.setTotal(total);
				ordVO.setArrival_time(arrival_time);
				ordVO.setFinish_time(finish_time);
				ordVO.setVerif_code(verif_code);
				ordVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/ord/addOrd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd(mem_no, vendor_no, tbl_no, party_size, share_mem_no1, share_mem_no2, share_amount, ord_time, booking_date, booking_time, notes, total, arrival_time, finish_time, verif_code, status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ord/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/addOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
        
        
        if("selceted".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	String vendor_no = new String(req.getParameter("vendor_no"));
        	
        	VendorService VSvc = new VendorService();
        	
			List<VendorVO> vVO = VSvc.getAll();
			if (vVO == null) {
				errorMsgs.add("invaild ");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ord/ord/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("vVO", vVO); // 資料庫取出的empVO物件,存入req
			String url = "/ord/ord/addOrd2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
//		} catch (Exception e) {
//			errorMsgs.add("can not find ord_no detail:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/ord/ord/select_page.jsp");
//			failureView.forward(req, res);
//		}
        	
        	
        }
        
        
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.接收請求參數***************************************/
				String ord_no = new String(req.getParameter("ord_no"));
				
				/***************************2.開始刪除資料***************************************/
				OrdService ordSvc = new OrdService();
				ordSvc.deleteOrd(ord_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ord/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				System.out.println("url="+url);
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/listAllOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		if("updateDate".equals(action)){
			System.out.println("有近來");

	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
//	try {
		java.sql.Date booking_date=null;
		try {
			booking_date=java.sql.Date.valueOf(req.getParameter("booking_date").trim());
			System.out.println(booking_date+"555");
			
		}catch (IllegalArgumentException e) {
			booking_date=new java.sql.Date(System.currentTimeMillis());
			System.out.println(booking_date+"6666");
			errorMsgs.add("please choose date!");
		}
		
		Calendar  cal =Calendar.getInstance();
		cal.setTime(booking_date);
		cal.add(Calendar.DAY_OF_YEAR,1);
		System.out.println("471");
		java.sql.Date sqlTomorrow = new java.sql.Date(cal.getTimeInMillis());
		System.out.println(sqlTomorrow);
		String vendor_no = new String(req.getParameter("vendor_no"));
		System.out.println("475");
    	VendorService VSvc = new VendorService();
    
		List<VendorVO> vVO = VSvc.getAll();
		if (vVO == null) {
			errorMsgs.add("invaild ");
		}
		req.setAttribute("vVO", vVO);
		
		OrdService OrdSvc = new OrdService();
		List<OrdVO> ordVO = OrdSvc.getAll();
		req.setAttribute("OrdVO", ordVO);
		
		 // 資料庫取出的empVO物件,存入req
		
		String url = "/ord/ord/addOrd2.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
		
		
//	}
	
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
