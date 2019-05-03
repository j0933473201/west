package com.ord.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception_date.model.Exception_DateService;
import com.exception_date.model.Exception_DateVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.ord.model.OrdJDBCDAO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ord_detail.model.Order_DetailVO;
import com.reservation_table_ordered.model.Reservation_Table_OrderedService;
import com.reservation_table_ordered.model.Reservation_Table_OrderedVO;
import com.reservation_time.model.Reservation_TimeService;
import com.reservation_time.model.Reservation_TimeVO;
import com.restaurant_menu.model.Restaurant_MenuService;
import com.restaurant_menu.model.Restaurant_MenuVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;



public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String vendor=req.getParameter("vendor");//廠商中文名稱
		
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

        
		
        
        
        if("selected".equals(action)) {
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
			req.setAttribute("vendor_no",vendor_no);
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
		
		HttpSession session = req.getSession();
			
		if("updateDate".equals(action)){
				System.out.println("有近來");
	
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	//	try {
		String vendor_no = req.getParameter("vendor_no");
		VendorService VSvc = new VendorService();
		List<VendorVO> vVO = VSvc.getAll();
		
		Integer party_size = Integer.valueOf(req.getParameter("party_size"));
		String booking_time =req.getParameter("booking_time");
		System.out.println("booking_time"+booking_time);
		//不能訂位日期------------
		Exception_DateService ESvc= new Exception_DateService();
		List<Exception_DateVO> exclist=ESvc.getdate(vendor_no);
		//開放預訂時間-----------
		Reservation_TimeService RSvc= new Reservation_TimeService();
		List<Reservation_TimeVO> rtlist=RSvc.getVendor(vendor_no);
		
		//訂位日期
		java.sql.Date booking_date=null;
		try {
			booking_date=java.sql.Date.valueOf(req.getParameter("booking_date").trim());
		}catch (IllegalArgumentException e) {
			booking_date=new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("please choose date!");
		}
		//已訂位時段剩餘數量------------
		
		Reservation_Table_OrderedService RtoSvc =new Reservation_Table_OrderedService();
		List<Reservation_Table_OrderedVO> rtolist2= RtoSvc.get2table(vendor_no, booking_date);
		List<Reservation_Table_OrderedVO> rtolist4= RtoSvc.get4table(vendor_no, booking_date);
		List<Reservation_Table_OrderedVO> rtolist6= RtoSvc.get6table(vendor_no, booking_date);
		List<Reservation_Table_OrderedVO> rtolist8= RtoSvc.get8table(vendor_no, booking_date);
		List<Reservation_Table_OrderedVO> rtolist10= RtoSvc.get10table(vendor_no, booking_date);
		
		
		//LinkedHashSet2 存剩餘數量大於零的2人坐時段-----------
		LinkedHashSet lhs=new LinkedHashSet();
		if(party_size==1||party_size==2) {
			 for (Exception_DateVO exc : exclist) {
					if(booking_date!=exc.getExc_date()) {
						for(Reservation_Table_OrderedVO rto : rtolist2) {
						lhs.add(rto);
						}	
					}
			 }
		}
		//3~4party_size
		LinkedHashSet lhs2=new LinkedHashSet();
		if(party_size==3||party_size==4) {
			 for (Exception_DateVO exc : exclist) {
					if(booking_date!=exc.getExc_date()) {
						for(Reservation_Table_OrderedVO rto : rtolist4) {
						lhs.add(rto);
						}	
					}
			 }
		}
		//5~6party_size
				LinkedHashSet lhs3=new LinkedHashSet();
				if(party_size==5||party_size==6) {
					 for (Exception_DateVO exc : exclist) {
							if(booking_date!=exc.getExc_date()) {
								for(Reservation_Table_OrderedVO rto : rtolist6) {
								lhs.add(rto);
								}	
							}
					 }
				}
				//7~8party_size
				LinkedHashSet lhs4=new LinkedHashSet();
				if(party_size==7||party_size==8) {
					 for (Exception_DateVO exc : exclist) {
							if(booking_date!=exc.getExc_date()) {
								for(Reservation_Table_OrderedVO rto : rtolist8) {
								lhs.add(rto);
								}	
							}
					 }
				}
				//9~10party_size
				LinkedHashSet lhs5=new LinkedHashSet();
				if(party_size==9||party_size==10) {
					 for (Exception_DateVO exc : exclist) {
							if(booking_date!=exc.getExc_date()) {
								for(Reservation_Table_OrderedVO rto : rtolist10) {
								lhs.add(rto);
								}	
							}
					 }
				}
		
		 
			Calendar  cal =Calendar.getInstance();
			cal.setTime(booking_date);
			cal.add(Calendar.DAY_OF_YEAR,1);
			System.out.println("471");
			java.sql.Date sqlTomorrow = new java.sql.Date(cal.getTimeInMillis());
			System.out.println(sqlTomorrow);
		
		
		
		if (vVO == null) {
			errorMsgs.add("invaild ");
		}
		
		req.setAttribute("lhs", lhs);
		req.setAttribute("lhs2", lhs2);
		req.setAttribute("lhs3", lhs3);
		req.setAttribute("lhs4", lhs4);
		req.setAttribute("lhs5", lhs5);
		
		req.setAttribute("booking_date", booking_date);
		req.setAttribute("rtlist",rtlist);
		req.setAttribute("vVO", vVO);
		req.setAttribute("exclist", exclist);
		req.setAttribute("rtolist2", rtolist2);
		OrdService OrdSvc = new OrdService();
		List<OrdVO> ordVO = OrdSvc.getAll();
		session.setAttribute("OrdVO", ordVO);
		
		 // 資料庫取出的empVO物件,存入req
		
		String url = "/ord/ord/addOrd2.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
		successView.forward(req, res);
		
		
//	}	
	
		}
		
		
//		HttpSession session = req.getSession();
		if(action.equals("sel_time")) {
			
			String vendor_no=req.getParameter("vendor_no");
			String mem_no =req.getParameter("mem_no");
			System.out.println("mem_no======"+mem_no);
//			String tbl_no = req.getParameter("tbl_no");
			java.sql.Timestamp ord_time =java.sql.Timestamp.valueOf(req.getParameter("ord_time"));
			java.sql.Date booking_date = java.sql.Date.valueOf(req.getParameter("booking_date").trim());
			req.getParameter("booking_time");
			String booking_time=req.getParameter("booking_time");
			System.out.println("selselbooking====="+booking_time);
			String notes=req.getParameter("notes");
			
			
			
			Integer party_size =new Integer(req.getParameter("party_size"));
			String verif_code=req.getParameter("verif_code");
			Integer status=new Integer(req.getParameter("status"));
			
			
			
			session.setAttribute("vendor_no", vendor_no);
			session.setAttribute("mem_no", mem_no);
			System.out.println("vendor3===+++++++"+vendor_no);
			session.setAttribute("booking_date", booking_date);
			session.setAttribute("booking_time", booking_time);
			System.out.println("selecttime"+booking_time);
			session.setAttribute("notes", notes);
			session.setAttribute("party_size", party_size);
			session.setAttribute("status", status);
			
			String url = "/ord/ord/ordfood.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		
		
		
		@SuppressWarnings("unchecked")
		List<Restaurant_MenuVO> buylist = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");
		
		String vendor_no=(String) session.getAttribute("vendor_no");
			// 刪除購物車中的書籍
			if(action.equals("DELETE_menu") || action.equals("ADD_menu")) {
				
				if(action.equals("DELETE_menu")){
					
					String del = req.getParameter("del");
					System.out.println("del======="+del);
					int d = Integer.parseInt(del);
					buylist.remove(d);
				}
				
			// 新增書籍至購物車中
				
				else if (action.equals("ADD_menu")) {
				// 取得後來新增的書籍
				System.out.println("030303030303");
				Restaurant_MenuVO rmenu = getMenu(req);
				System.out.println("rmenu========="+rmenu);
					if (buylist == null) {
						buylist = new Vector<Restaurant_MenuVO>();
						buylist.add(rmenu);
					} else {
						if (buylist.contains(rmenu)) {
							Restaurant_MenuVO  menulist= buylist.get(buylist.indexOf(rmenu));
							menulist.setQuantity(menulist.getQuantity() + rmenu.getQuantity());
							
						} else {
							buylist.add(rmenu);
						}
					}
				
				}
				double amount= 0;
				Integer quantity=0;
				Integer price=0;
				String menu_no=null;
				for (int i = 0; i < buylist.size(); i++) {
					System.out.println("buylist====="+buylist);
					Restaurant_MenuVO menu = buylist.get(i);
					 price = Integer.parseInt(menu.getMenu_price());
					 quantity = menu.getQuantity();
					 menu_no =menu.getMenu_no();
					amount += (price * quantity);
					System.out.println("menu=========="+menu);
					session.setAttribute("menu", menu);
				}
				
			System.out.println("vendor_no888888888"+vendor_no);
			session.setAttribute("vendor_no", vendor_no);
				session.setAttribute("menu_no",menu_no);
				System.out.println("menu_no========"+menu_no);
				
				String total = String.valueOf(amount);
				System.out.println("total======"+total);
				session.setAttribute("total", total);
				session.setAttribute("quantity", quantity);
				System.out.println("quantity========="+quantity);
				session.setAttribute("price", price);
				System.out.println("price==========="+price);
				System.out.println("==============="+session.getAttribute("buylist"));
				session.setAttribute("shoppingcart", buylist);
				System.out.println(session.getAttribute("shoppingcart"));
				session.setAttribute("vendor", vendor);
				
			String vendro_no=(String) session.getAttribute("vendor_no");
			System.out.println(vendro_no);
				String url = "/ord/ord/ordfood.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);

			return;
			
			}	
	
		// 結帳，計算購物車書籍價錢總數

				 if (action.equals("checkout")) {
					double amount = 0;
					for (int i = 0; i < buylist.size(); i++) {
						Restaurant_MenuVO menu = buylist.get(i);
						Integer price = Integer.parseInt(menu.getMenu_price());
						Integer quantity = menu.getQuantity();
						amount += (price * quantity);
					}
					
					String booking_time=(String) session.getAttribute("booking_time");
					System.out.println("check==booking"+booking_time);
					
					String total = String.valueOf(amount);
					session.setAttribute("total", total);
					System.out.println("total000000000"+total);
					String url = "/ord/ord/check.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				 }
				 
				 
				 //選擇好友分攤後會將總金額，以及哪幾位好友存在redis
				 
				 if (action.equals("share_pick")) {
					 String share_amount1=null;
					 String share_amount2=null;
					 String share_mem_no1=null; 
					 String share_mem_no2=null;
					 //分攤名單
					 String[] xxx=req.getParameterValues("share_amount");
					 for(int i =0;i<xxx.length;i++) {
						 if(xxx[i].length()!=0&&xxx[i]!=""){
//						 share_amount1 =Integer.parseInt(xxx[i]);
//						 share_amount2 =Integer.parseInt(xxx[++i]);
						 share_amount1 =(xxx[i]);
						 share_amount2 =(xxx[++i]);
					 }
				 }	 
					 //分攤金額
					 String[]mem=req.getParameterValues("share_mem_no");
					 for(int i=0;i<mem.length;i++) {
						 if(mem[i]!=""&&mem[i].length()!=0) {
							 share_mem_no1=mem[i];
							 share_mem_no2=mem[++i];
						 }
					 }
						System.out.println("分攤好友"+share_mem_no1);
						System.out.println("分攤好友2"+share_mem_no2);
						System.out.println("分攤金額"+share_amount1);
						System.out.println("分攤金額2"+share_amount2);
						//取得email
						MemberService memSvc=new MemberService();
						MemberVO memVO = memSvc.getOneMember(share_mem_no1);//分攤第一位
						MemberVO memVO1 = memSvc.getOneMember(share_mem_no2);//分攤第二位
						String name=memVO.getMem_name();
						String name1=memVO1.getMem_name();
						
						String email1=memVO.getMem_mail();
						String email2=memVO1.getMem_mail();
						
						String email3="ji394z06z06@yahoo.com.tw";
						String email4="j0933473201@icloud.com";
						
						  String  URL= "http://localhost:8082/CA107_G3/ord/ord/share_pay1.jsp?mem_no="+share_mem_no1+"&amount="+share_amount1+"&name="+name;
						  String subject = "請點擊付款";
					      String messageText = "Hello! " + name + 
					    		  " 您的好友已完成訂位及選購餐點 ，您需要支付的金額為"
					    		  +share_amount1+"元整"+"\r\n"
					    			+ "請點擊以下連結完成付款以便於完成訂購手續"
					    		  +"\r\n"
					    		  +URL+
					    		  "\r\n"+ 
					    		  "請在2小時內完成付款，否則訂單不成立"; 
					      
					      String  URL1= "http://localhost:8082/CA107_G3/ord/ord/share_pay1.jsp?mem_no="+share_mem_no2+"&amount="+share_amount2+"&name1="+name1;
						  String subject1 = "請點擊付款";
					      String messageText1= "Hello! " + name1 + 
					    		  " 您的好友已完成訂位及選購餐點 ，您需要支付的金額為"
					    		  +share_amount2+"元整"+"\r\n"
					    			+ "請點擊以下連結完成付款以便於完成訂購手續"
					    		  +"\r\n"
					    		  +URL1+
					    		  "\r\n"+ 
					    		  "請在2小時內完成付款，否則訂單不成立"; 
					      
					      //拿到總數,以及兩個朋友應該分攤的金額redis
					    String  total= (String) session.getAttribute("total");
					    String amount= Integer.toString(0);
					    
					      RedislService redisService =new RedislService();
					      //存入redis
					      redisService.insettotal("total", total);
					      redisService.insertshare(share_mem_no1,amount );
					      redisService.insertshare(share_mem_no2, amount);
					      session.setAttribute("share_mem_no1", share_mem_no1);
					      session.setAttribute("share_mem_no2", share_mem_no2);
					      
//					      memSvc.insetshare(share_mem_no1, share_amount1);
					     //寄信
					      MailService mailService = new MailService();
					      mailService.sendMail(email3, subject, messageText);
					      mailService.sendMail(email4, subject1, messageText1);			
			 }
				 
				 
				 //個人付款
				 if (action.equals("tocredit")) {
					 
					 double amount = 0;
						for (int i = 0; i < buylist.size(); i++) {
							Restaurant_MenuVO menu = buylist.get(i);
							Integer price = Integer.parseInt(menu.getMenu_price());
							Integer quantity = menu.getQuantity();
							amount += (price * quantity);
						}
						
						String booking_time=(String) session.getAttribute("booking_time");
						System.out.println("creditbooking===="+booking_time);
						Integer total =(int)amount;
						System.out.println("total55555"+total);
						session.setAttribute("total", total);
						String url = "/ord/ord/credit.jsp";
						RequestDispatcher rd = req.getRequestDispatcher(url);
						rd.forward(req, res);
					 }
				 
				 
				 	//繳費後檢查是否分攤好友皆有完成
				 if("share_pay".equals(action)) {
//					 String share_mem_no1=(String) session.getAttribute("share_mem_no1");
					 
					//取得付款的人以及金額並且存進redis
					String amount=req.getParameter("AMOUNT");
					String share_mem_no1=req.getParameter("share_mem_no");
					 RedislService redisService =new RedislService();
					 redisService.insertshare(share_mem_no1, amount);
					 
					 //從redis取出total以及兩個分攤金額
					 //11,22代表要付款的會員編號
					String share_mem_no11= (String) session.getAttribute("share_mem_no1");
					String share_mem_no22=(String) session.getAttribute("share_mem_no2");
					//總共要付的金額
					 String total1=(redisService.gettotal("total"));
					 Integer total=(int)(Double.parseDouble(total1));
					 System.out.println("===="+total);
					 //取出share_amount1已經付的錢
					 //取出share_amount2已經付的錢
					 Integer share_amount1=0;
					 Integer share_amount2=0;
					 if(redisService.gettotal(share_mem_no11).length()==0) {
						  share_amount1=0;
					 }else {
						 share_amount1=Integer.parseInt((redisService.gettotal(share_mem_no11)));
					 }
					 System.out.println("====share_amount1"+share_amount1);
					 
					 if(redisService.gettotal(share_mem_no22).equals("")) {
						share_amount2=0;
					 }else {
						  share_amount2=Integer.parseInt((redisService.gettotal(share_mem_no22)));
					 }
					 System.out.println("  share_amount2====="+  share_amount2);

					 //判斷兩個付款相加有沒有等於總共要負的款項
					 Integer share_amount=share_amount1+share_amount2;
					 System.out.println(" ====total"+total);
					 if(share_amount<total) {   //如果金額小於
						 String url = "/ord/ord/addOrd2.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); 
							successView.forward(req, res);		
					 }else {
						 String mem_no = (String) session.getAttribute("mem_no");
						 vendor_no  = (String) session.getAttribute("vendor_no");
						String tbl_no =(String) session.getAttribute("tbl_no");
						Integer party_size =(Integer) (session.getAttribute("party_size"));
						share_mem_no1 =(String) session.getAttribute("share_mem_no1");
						String share_mem_no2 =(String) session.getAttribute("share_mem_no2");
						java.sql.Timestamp ord_time =new java.sql.Timestamp(System.currentTimeMillis());
						System.out.println("ordtime======"+ord_time);
						java.sql.Date booking_date=null;
						try {
							booking_date = (Date) session.getAttribute("booking_date");
						} catch (IllegalArgumentException e) {
							booking_date=new java.sql.Date(System.currentTimeMillis());
//							errorMsgs.add("please choose date!");
						}
						String booking_time=(String) session.getAttribute("booking_time");
						System.out.println("booking_time======="+booking_time);
						String notes=(String) session.getAttribute("notes");
//						Integer total=null;
//						try {
							
//							total = (Integer) session.getAttribute("total");
							System.out.println("total======"+total);
//							} catch (NumberFormatException e) {
								
//								errorMsgs.add("please. insert right total");
//							}catch (NullPointerException b ) {
							
//								errorMsgs.add("please insert total number");}
						
						String arrival_time=req.getParameter("arrival_time");
						String finish_time=req.getParameter("finish_time");
						String verif_code=req.getParameter("verif_code");
						Integer status=(Integer) session.getAttribute("status");
						
						
						OrdJDBCDAO dao = new OrdJDBCDAO();
						
						
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
						
						List<Order_DetailVO>testList = new ArrayList<Order_DetailVO>();
						
						List<Restaurant_MenuVO> buy = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");
						
						for(Restaurant_MenuVO RVO:buy ) {
							Order_DetailVO  Order_DetailVO =new Order_DetailVO();
//							String menu_no = (String) session.getAttribute("menu_no");
//							Integer price =(Integer) session.getAttribute("price");
//							Integer qty = (Integer) session.getAttribute("quantity");
							Order_DetailVO.setMenu_no(RVO.getMenu_no());
							Order_DetailVO.setPrice(Integer.parseInt(RVO.getMenu_price()));
							Order_DetailVO.setQty(RVO.getQuantity());
							testList.add(Order_DetailVO);
						}
						
						dao.insertWithOrd_detail(ordVO,testList);
//						System.out.println("cominginging");
//						
//							String menu_no = (String) session.getAttribute("menu_no");
//							Integer price =(Integer) session.getAttribute("price");
//							Integer qty = (Integer) session.getAttribute("quantity");
//							
//							Order_DetailVO.setMenu_no(menu_no);
//							Order_DetailVO.setPrice(price);
//							Order_DetailVO.setQty(qty);
//							testList.add(Order_DetailVO);
						
						//傳送信件
						
						 String to = "yasmile718@yahoo.com.tw";
					      
					      String subject = "成功訂位";
					      
					      String ch_name = "ＪＥＮＮＹ";
					  
					      String messageText = "Hello! " + ch_name + " 恭喜你完成訂位,請洽0933473201偉斯特大帥哥 "; 
					       
					      MailService mailService = new MailService();
					      mailService.sendMail(to, subject, messageText);
						
						
						
						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							req.setAttribute("ordVO", ordVO); 
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/ord/ord/addOrd2.jsp");
//							failureView.forward(req, res);
//							return;
//						}
						
						/***************************2.開始新增資料***************************************/
						
						 
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						String url = "/ord/ord/listAllOrd.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);	
//						 
					 }
					 
					 
					
				 }
				 
				 
				 
				 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
						
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
							System.out.println("insert");
//						try {
							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							String mem_no = (String) session.getAttribute("mem_no");
							
							 vendor_no  = (String) session.getAttribute("vendor_no");
							String tbl_no =(String) session.getAttribute("tbl_no");
							Integer party_size =(Integer) (session.getAttribute("party_size"));
							String share_mem_no1 =req.getParameter("share_mem_no1");
							String share_mem_no2 =req.getParameter("share_mem_no2");
							
						
								Integer share_amount =(Integer) (session.getAttribute("share_amount"));
							
								
							
							
							java.sql.Timestamp ord_time =new java.sql.Timestamp(System.currentTimeMillis());
							System.out.println("ordtime======"+ord_time);
							java.sql.Date booking_date=null;
							try {
								booking_date = (Date) session.getAttribute("booking_date");
							} catch (IllegalArgumentException e) {
								booking_date=new java.sql.Date(System.currentTimeMillis());
								errorMsgs.add("please choose date!");
							}
							String booking_time=(String) session.getAttribute("booking_time");
							System.out.println("booking_time======="+booking_time);
							String notes=(String) session.getAttribute("notes");
							Integer total=null;
							try {
								
								total = (Integer) session.getAttribute("total");
								System.out.println("total======"+total);
								} catch (NumberFormatException e) {
									
									errorMsgs.add("please. insert right total");
								}catch (NullPointerException b ) {
								System.out.println("66666666");
								
									errorMsgs.add("please insert total number");}
							
							String arrival_time=req.getParameter("arrival_time");
							String finish_time=req.getParameter("finish_time");
							String verif_code=req.getParameter("verif_code");
							Integer status=(Integer) session.getAttribute("status");
							
							
							OrdJDBCDAO dao = new OrdJDBCDAO();
							
							
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
							
							List<Order_DetailVO>testList = new ArrayList<Order_DetailVO>();
							
							List<Restaurant_MenuVO> buy = (Vector<Restaurant_MenuVO>) session.getAttribute("shoppingcart");
							
							for(Restaurant_MenuVO RVO:buy ) {
								Order_DetailVO  Order_DetailVO =new Order_DetailVO();
//								String menu_no = (String) session.getAttribute("menu_no");
//								Integer price =(Integer) session.getAttribute("price");
//								Integer qty = (Integer) session.getAttribute("quantity");
								Order_DetailVO.setMenu_no(RVO.getMenu_no());
								Order_DetailVO.setPrice(Integer.parseInt(RVO.getMenu_price()));
								Order_DetailVO.setQty(RVO.getQuantity());
								testList.add(Order_DetailVO);
							}
							
							dao.insertWithOrd_detail(ordVO,testList);
//							System.out.println("cominginging");
//							
//								String menu_no = (String) session.getAttribute("menu_no");
//								Integer price =(Integer) session.getAttribute("price");
//								Integer qty = (Integer) session.getAttribute("quantity");
//								
//								Order_DetailVO.setMenu_no(menu_no);
//								Order_DetailVO.setPrice(price);
//								Order_DetailVO.setQty(qty);
//								testList.add(Order_DetailVO);
							
							//傳送信件
							
							 String to = "yasmile718@yahoo.com.tw";
						      
						      String subject = "成功訂位";
						      
						      String ch_name = "ＪＥＮＮＹ";
						  
						      String messageText = "Hello! " + ch_name + " 恭喜你完成訂位,請洽0933473201偉斯特大帥哥 "; 
						       
						      MailService mailService = new MailService();
						      mailService.sendMail(to, subject, messageText);
							
							
							
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("ordVO", ordVO); 
								RequestDispatcher failureView = req
										.getRequestDispatcher("/ord/ord/addOrd2.jsp");
								failureView.forward(req, res);
								return;
							}
							
							/***************************2.開始新增資料***************************************/
							
							 
							
							/***************************3.新增完成,準備轉交(Send the Success view)***********/
							String url = "/ord/ord/listAllOrd.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
							successView.forward(req, res);				
							
							/***************************其他可能的錯誤處理**********************************/
//						} catch (Exception e) {
//							errorMsgs.add(e.getMessage());
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/ord/addOrd.jsp");
//							failureView.forward(req, res);
//						}
					}
				 }
				 
			

	
	
	


	


	private Restaurant_MenuVO getMenu(HttpServletRequest req) {
		String menu_no = req.getParameter("menu_no");
		String vendor_no = req.getParameter("vendor_no");
		String menu_name = req.getParameter("menu_name");
		String menu_price = req.getParameter("menu_price");
//		Integer menu_stat = Integer.parseInt(req.getParameter("menu_stat"));
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		String menu_text = req.getParameter("menu_text");
		
		Restaurant_MenuService RmSvc=new Restaurant_MenuService();
		Restaurant_MenuVO menu = new Restaurant_MenuVO();
		menu.setMenu_no(menu_no);
		menu.setVendor_no(vendor_no);
		menu.setMenu_name(menu_name);
		menu.setMenu_price(menu_price);
		menu.setQuantity((new Integer(quantity)).intValue());;
		
//		menu.setMenu_stat(menu_stat);
		menu.setMenu_text(menu_text);
		
		return menu;
		
		
	}
	




	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
