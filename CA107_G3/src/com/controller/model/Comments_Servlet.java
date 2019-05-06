package com.controller.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentsJDBCDAO;
import com.comment.model.CommentsService;
import com.comment.model.CommentsVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;


public class Comments_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("insert_comments".equals(action)) {
System.out.println("你來");
			try {
				// Retrieve form parameters.
				String ord_no = req.getParameter("ord_no");
//				Integer score=Integer.valueOf(req.getParameter("score"));
//				String cmnt =req.getParameter("cmnt");
//				java.sql.Timestamp time=new java.sql.Timestamp(System.currentTimeMillis());
//				Integer cmnt_status=1;
//				
				CommentsVO C_VO=new CommentsVO();
//				C_VO.setCmnt(cmnt);
//				C_VO.setCmnt_status(cmnt_status);
//				C_VO.setOrd_no(ord_no);
//				C_VO.setScore(score);
//				C_VO.setTime(time);
//				C_VO.setVendor_no(vendor_no);
//				
				OrdService OSvc=new OrdService();
				OrdVO OrdVO=OSvc.getOneOrd(ord_no);
				String vendor_no=OrdVO.getVendor_no();
				CommentsService C_Svc=new CommentsService();
				
				
//				C_Svc.addComments(ord_no, vendor_no, score, cmnt, cmnt_status);

//				req.setAttribute("CommentsVO", C_VO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("ord_no", ord_no);
				req.setAttribute("vendor_no", vendor_no);
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/ord/ord/list_for_mem.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		
		
		
		
		System.out.println("來");
		if ("get_one_insert".equals(action)) {
			
			try {
				// Retrieve form parameters.
				System.out.println("來ㄅ ");
				String vendor_no=req.getParameter("vendor_no");
				String ord_no = req.getParameter("ord_no");
				Integer score=Integer.valueOf(req.getParameter("score"));
				String cmnt =req.getParameter("cmnt");
				java.sql.Timestamp time=new java.sql.Timestamp(System.currentTimeMillis());
				Integer cmnt_status=1;
//				
				CommentsVO C_VO=new CommentsVO();
				C_VO.setCmnt(cmnt);
				C_VO.setCmnt_status(cmnt_status);
				C_VO.setOrd_no(ord_no);
				C_VO.setScore(score);
				C_VO.setTime(time);
				C_VO.setVendor_no(vendor_no);
				
//				OrdService OSvc=new OrdService();
//				OrdVO OrdVO=OSvc.getOneOrd(ord_no);
//				String vendor_no=OrdVO.getVendor_no();
				CommentsService C_Svc=new CommentsService();
				CommentsJDBCDAO C_dao =new CommentsJDBCDAO();
				C_dao.insert(C_VO);
				
//				C_Svc.addComments(ord_no, vendor_no, score, cmnt, cmnt_status);

				req.setAttribute("CommentsVO", C_VO); // 資料庫取出的empVO物件,存入req
//				req.setAttribute("ord_no", ord_no);
//				req.setAttribute("vendor_no", vendor_no);
				//Bootstrap_modal
//				boolean openModal=true;
//				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/ord/ord/list_for_mem.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
