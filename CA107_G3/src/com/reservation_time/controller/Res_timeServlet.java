package com.reservation_time.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exception_date.model.Exception_DateService;
import com.exception_date.model.Exception_DateVO;
import com.reservation_table_number.model.Reservation_Table_NumberService;
import com.reservation_table_number.model.Reservation_Table_NumberVO;
import com.reservation_time.model.Reservation_TimeService;
import com.reservation_time.model.Reservation_TimeVO;


public class Res_timeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String rt_no = new String(req.getParameter("rt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Reservation_TimeService res_timeSvc = new Reservation_TimeService();
				Reservation_TimeVO reservation_TimeVO  = res_timeSvc.getOneReservation_timeDAO(rt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("reservation_TimeVO", reservation_TimeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String rt_no = new String(req.getParameter("rt_no").trim());
				try {
					rt_no = new String(req.getParameter("rt_no").trim());
				} catch (Exception e) {
					
					errorMsgs.add("請輸入正確餐廳時段編號.");
				}

				String vendor_no = req.getParameter("vendor_no");
			
				if (vendor_no == null || vendor_no.trim().length() == 0) {
					errorMsgs.add("餐廳編號: 請勿空白");
				} 
	            
				
				String r_time = req.getParameter("r_time").trim();
				if (r_time == null || r_time.trim().length() == 0) {
					errorMsgs.add("餐廳可訂位時段請勿空白");
				}	
				
				
				
//					System.out.println(java.sql.Timestamp((System.currentTimeMillis())));
				Reservation_TimeVO res_timeVO = new Reservation_TimeVO();
				res_timeVO.setRt_no(rt_no);
				res_timeVO.setVendor_no(vendor_no);
				res_timeVO.setR_time(r_time);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("res_timeVO", res_timeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				Reservation_TimeService res_timeSvc = new Reservation_TimeService();
				res_timeVO = res_timeSvc.updateReservation_timeDAO(rt_no, vendor_no, r_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("res_timeVO", res_timeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String vendor_no = req.getParameter("vendor_no");
				String r_time = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (r_time == null || r_time.trim().length() == 0) {
					errorMsgs.add("可預訂時段: 請勿空白");
				} 
	           
				
				
				Reservation_TimeVO res_timeVO = new Reservation_TimeVO();
				res_timeVO.setVendor_no(vendor_no);
				res_timeVO.setR_time(r_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("res_timeVO", res_timeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Reservation_TimeService res_timeSvc = new Reservation_TimeService();
				res_timeVO = res_timeSvc.addReservation_time(vendor_no, r_time);			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String rt_no = new String(req.getParameter("rt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Reservation_TimeService res_timeSvc = new Reservation_TimeService();
				
				res_timeSvc.deleteReservation_timeDAO(rt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				System.out.println("url="+url);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//新增廠商可預訂時段/不可訂位日期/可訂位桌數
		if ("get_vendor_insert".equals(action)) {
			
//			Reservation_TimeVO res_timeVO = new Reservation_TimeVO();
			Reservation_TimeService res_timeSvc = new Reservation_TimeService();
			
//			Exception_DateVO exception_dateVO = new Exception_DateVO();
			Exception_DateService exception_dateSvs = new Exception_DateService();
			
			Reservation_Table_NumberService res_t_nSvc=new Reservation_Table_NumberService();
			
			String vendor_no=req.getParameter("vendor_no");
		
			//取得廠商輸入的開放訂位時段,並且過濾重複的以及空值
			LinkedHashSet<String> rt_list =new LinkedHashSet<String>();
			String open_hours[]=req.getParameterValues("open_hours");
			
			for(int i= 0;i<open_hours.length;i++) {
				if(open_hours[i].length()!=0&&open_hours[i]!="")
				rt_list.add(open_hours[i]);
			}
			for(String rt_time:rt_list ) {
				res_timeSvc.addReservation_time(vendor_no, rt_time);
			}		
			
			//取得廠商輸入的不開放訂位日期,並且過濾重複的以及空值insert到資料庫
			LinkedHashSet<String> exc_list =new LinkedHashSet<String>();
			String exc_date[]=req.getParameterValues("exc_date");
			for(int i=0;i<exc_date.length;i++) {
				if(exc_date[i].length()!=0&&exc_date[i]!="") {
					exc_list.add(exc_date[i]);
				}
				for(String exc_date1 :exc_list) {
					exception_dateSvs.addException_date(vendor_no,java.sql.Date.valueOf(exc_date1));
				}
				
			}
			
			//取得廠商輸入的桌位數量，並且insert到資料庫
			Integer rtbl_o_num1=Integer.valueOf(req.getParameter("rtbl_o_num1"));
			Integer rtbl_o_num2=Integer.valueOf(req.getParameter("rtbl_o_num2"));
			Integer rtbl_o_num3=Integer.valueOf(req.getParameter("rtbl_o_num3"));
			Integer rtbl_o_num4=Integer.valueOf(req.getParameter("rtbl_o_num4"));
			Integer rtbl_o_num5=Integer.valueOf(req.getParameter("rtbl_o_num5"));
			
			res_t_nSvc.addReservation_Table_Number(vendor_no, rtbl_o_num1, rtbl_o_num2, rtbl_o_num3, rtbl_o_num4, rtbl_o_num5);
			
		}
		
		
		
		//更新廠商可預訂時段/不可訂位日期/可訂位桌數
				if ("get_vendor_update".equals(action)) {
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);

						try {
							/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
							String vendor_no = req.getParameter("vendor_no");
							if (vendor_no == null || (vendor_no.trim()).length() == 0) {
								errorMsgs.add("請輸入廠商編號");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView =
		req.getRequestDispatcher("/emp/select_page.jsp");//設定到首頁
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = 
		req.getRequestDispatcher("/emp/select_page.jsp");//跳轉回首頁
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							/***************************2.開始查詢資料*****************************************/
							//Reservation_Time
							Reservation_TimeService res_timeSvc = new Reservation_TimeService();
							List<Reservation_TimeVO> reservation_TimeVO  = res_timeSvc.finby_v_no(vendor_no);
							if (reservation_TimeVO == null) {
								errorMsgs.add("查無資料");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = 
	req.getRequestDispatcher("/emp/select_page.jsp");//轉回首頁
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							//Exception_Date
							Exception_DateService exception_dateSvc=new Exception_DateService()	;
							List<Exception_DateVO> exception_dateVO  = exception_dateSvc.find_by_vendor_no(vendor_no);
							if (exception_dateVO == null) {
								errorMsgs.add("查無資料");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = 
	req.getRequestDispatcher("/emp/select_page.jsp");//轉回首頁
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							//reservation_Table_Number
							Reservation_Table_NumberService res_t_nSvc=new Reservation_Table_NumberService();
							List<Reservation_Table_NumberVO>reservation_Table_NumberVO = res_t_nSvc.findBy_vendor(vendor_no);
							if (reservation_Table_NumberVO == null) {
								errorMsgs.add("查無資料");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = 
	req.getRequestDispatcher("/emp/select_page.jsp");//轉回首頁
								failureView.forward(req, res);
								return;//程式中斷
							}	
									
									
							/***************************3.查詢完成,準備轉交(Send the Success view)*************/
							req.setAttribute("exception_dateVO", exception_dateVO);
							req.setAttribute("reservation_TimeVO", reservation_TimeVO); // 資料庫取出的empVO物件,存入req
							req.setAttribute("reservation_Table_NumberVO", reservation_Table_NumberVO);
							String url = "/open_time/update_time.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);

							/***************************其他可能的錯誤處理*************************************/
						} catch (Exception e) {
							errorMsgs.add("無法取得資料:" + e.getMessage());
							RequestDispatcher failureView = 
req.getRequestDispatcher("/emp/select_page.jsp");//回首頁
							failureView.forward(req, res);
						}
					}
					
					
				}
				
		

		
		
	


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
