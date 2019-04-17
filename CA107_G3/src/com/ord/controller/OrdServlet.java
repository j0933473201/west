package com.ord.controller;

import java.io.IOException;
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



public class OrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String ord_no = null;
				try {
					ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�q��s�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
				if (ordVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String ord_no = new String(req.getParameter("ord_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("ordVO", ordVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/ord/update_ord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/listAllOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String ord_no = new String(req.getParameter("ord_no").trim());
				
				String mem_no = req.getParameter("mem_no");
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!mem_no.trim().matches(mem_no)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("000");
	            }
				String vendor_no  = req.getParameter("vendor_no");
				String tbl_no =req.getParameter("tbl_no");
				Integer party_size =new Integer(req.getParameter("party_size"));
				String share_mem_no1 =req.getParameter("share_mem_no1");
				String share_mem_no2 =req.getParameter("share_mem_no2");
				Integer share_amount =new Integer(req.getParameter("share_amount"));
				java.sql.Timestamp ord_time =java.sql.Timestamp.valueOf(req.getParameter("ord_time"));
				java.sql.Date booking_date=java.sql.Date.valueOf(req.getParameter("booking_date"));
				String booking_time=req.getParameter("booking_time");
				String notes=req.getParameter("notes");
				Integer total=new Integer(req.getParameter("total"));
				String arrival_time=req.getParameter("arrival_time");
				String finish_time=req.getParameter("finish_time");
				String verif_code=req.getParameter("verif_code");
				Integer status=new Integer(req.getParameter("status"));
				
				
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
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
					req.setAttribute("ordVO", ordVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/update_ord_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ord_no, mem_no, vendor_no, tbl_no, party_size, share_mem_no1, share_mem_no2, share_amount, ord_time, booking_date, booking_time, notes, total, arrival_time, finish_time, verif_code, status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/update_ord_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String mem_no = req.getParameter("mem_no");
				
				String vendor_no  = req.getParameter("vendor_no");
				String tbl_no =req.getParameter("tbl_no");
				Integer party_size =new Integer(req.getParameter("party_size"));
				String share_mem_no1 =req.getParameter("share_mem_no1");
				String share_mem_no2 =req.getParameter("share_mem_no2");
				Integer share_amount =new Integer(req.getParameter("share_amount"));
				java.sql.Timestamp ord_time =java.sql.Timestamp.valueOf(req.getParameter("ord_time"));
				java.sql.Date booking_date=java.sql.Date.valueOf(req.getParameter("booking_date"));
				String booking_time=req.getParameter("booking_time");
				String notes=req.getParameter("notes");
				Integer total=new Integer(req.getParameter("total"));
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
					req.setAttribute("ordVO", ordVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ord/addOrd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd(mem_no, vendor_no, tbl_no, party_size, share_mem_no1, share_mem_no2, share_amount, ord_time, booking_date, booking_time, notes, total, arrival_time, finish_time, verif_code, status);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/addOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String ord_no = new String(req.getParameter("ord_no"));
				
				/***************************2.�}�l�R�����***************************************/
				OrdService ordSvc = new OrdService();
				ordSvc.deleteOrd(ord_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				System.out.println("url="+url);
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ord/listAllOrd.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
