package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[\u4E00-\u9FA5]{2,3}$";
				if (mem_name == null || (mem_name.trim()).length() == 0) {
					errorMsgs.add("請輸入姓名");
				} else if (!mem_name.trim().matches(mem_nameReg)) {
					errorMsgs.add("姓名只能填入2~4個中文字");
				}

				String mem_gender = req.getParameter("mem_gender");
				if (mem_gender == null) {
					errorMsgs.add("請選擇稱謂");
				}

				String mem_nickname = req.getParameter("mem_nickname");
				String mem_nicknameReg = "^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,10}$";
				if (mem_nickname == null || (mem_nickname.trim()).length() == 0) {
					errorMsgs.add("請輸入暱稱");
				} else if (!mem_nickname.trim().matches(mem_nicknameReg)) {
					errorMsgs.add("暱稱只能輸入2~10碼英數或中文");
				}

				String mem_mail = req.getParameter("mem_mail");
				if (mem_mail == null || (mem_mail.trim()).length() == 0) {
					errorMsgs.add("請輸入電子信箱");
				}

				String mem_id = req.getParameter("mem_id");
				String mem_idReg = "^[A-Z][12][0-9]{8}$";
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入身分證");
				} else if (!mem_id.trim().matches(mem_idReg)) {
					errorMsgs.add("請輸入有效的身分證");
				}

				String mem_tel = req.getParameter("mem_tel");
				String mem_telReg = "^[0][9][0-9]{8}$";
				if (mem_tel == null || (mem_tel.trim()).length() == 0) {
					errorMsgs.add("請填寫手機號碼");
				} else if (!mem_tel.trim().matches(mem_telReg)) {
					errorMsgs.add("請填寫有效的手機號碼");
				}

				String mem_account = req.getParameter("mem_account");
				String mem_accountReg = "^(?=.*\\d)(?=.*[A-za-z]).{6,10}$";
				if (mem_account == null || (mem_account.trim()).length() == 0) {
					errorMsgs.add("請填寫帳號");
				} else if (!mem_account.trim().matches(mem_accountReg)) {
					errorMsgs.add("請填寫6-10位混和英數字,英文需區分大小寫");
				}

				String mem_pwd = req.getParameter("mem_pwd");
				String mem_pwdReg = "^(?=.*\\d)(?=.*[A-za-z]).{6,10}$";
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請填寫密碼");
				} else if (!mem_pwd.trim().matches(mem_pwdReg)) {
					errorMsgs.add("請填寫6-10位混和英數字,英文需區分大小寫");
				}

				String mem_pwd_confirm = req.getParameter("mem_pwd_confirm");
				if (mem_pwd_confirm == null || (mem_pwd_confirm.trim()).length() == 0) {
					errorMsgs.add("請填寫密碼");
				}

				Collection<Part> parts = req.getParts();

				byte[] mem_pic = null;
				for (Part part : parts) {
					if (part.getName().equals("mem_pic")) {
						System.out.println(part.getSize());

						InputStream in = part.getInputStream();
						ByteArrayOutputStream output = new ByteArrayOutputStream();
						mem_pic = new byte[in.available()];
						for (int length = 0; (length = in.read(mem_pic)) > 0;) {
							output.write(mem_pic, 0, length);
						}

						output.close();
						in.close();
					}
				}
				String mem_status = "1";
				Double mem_balance = new Double(0);
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_name(mem_name);
				memberVO.setMem_account(mem_account);
				memberVO.setMem_pwd(mem_pwd);
				memberVO.setMem_gender(mem_gender);
				memberVO.setMem_mail(mem_mail);
				memberVO.setMem_id(mem_id);
				memberVO.setMem_tel(mem_tel);
				memberVO.setMem_pic(mem_pic);
				memberVO.setMem_status(mem_status);
				memberVO.setMem_balance(mem_balance);
				memberVO.setMem_nickname(mem_nickname);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_name, mem_account, mem_pwd, mem_gender, mem_mail, mem_id, mem_tel,
						mem_status, mem_pic, mem_balance, mem_nickname);
				String url = "/member/loginMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);

			}
		}
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_account = req.getParameter("mem_account");
				String mem_accountReg = "^[0-9a-zA-z]{6,10}$";
				if (mem_account == null || (mem_account.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				} else if (!mem_account.trim().matches(mem_accountReg)) {
					errorMsgs.add("帳號只能輸入數字及大小寫英文");
				}

				String mem_pwd = req.getParameter("mem_pwd");
				String mem_pwdReg = "^[0-9a-zA-z]{6,10}$";
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!mem_pwd.trim().matches(mem_pwdReg)) {
					errorMsgs.add("密碼只能輸入數字及大小寫英文");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/loginMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMemberByAccount(mem_account);

				if (memberVO == null) {
					errorMsgs.add("無此帳號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/loginMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_pwdConfirm = memberVO.getMem_pwd();

				System.out.println(mem_pwdConfirm);

				if (!mem_pwd.equals(mem_pwdConfirm)) {
					errorMsgs.add("密碼錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/loginMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				HttpSession session = req.getSession();
				session.setAttribute("mem_account", req.getParameter("mem_account"));
				session.setAttribute("memberVO", memberVO);

				String url = "/member/updateMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/loginMember.jsp");
				failureView.forward(req, res);

			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_nickname = req.getParameter("mem_nickname");
				String mem_nicknameReg = "^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,10}$";
				if (mem_nickname == null || (mem_nickname.trim()).length() == 0) {
					errorMsgs.add("請輸入暱稱");
				} else if (!mem_nickname.trim().matches(mem_nicknameReg)) {
					errorMsgs.add("暱稱只能輸入2~10碼英數或中文");
				}

				String mem_mail = req.getParameter("mem_mail");
				if (mem_mail == null || (mem_mail.trim()).length() == 0) {
					errorMsgs.add("請輸入電子信箱");
				}

				String mem_tel = req.getParameter("mem_tel");
				String mem_telReg = "^^[0][9][0-9]{8}$";
				if (mem_tel == null || (mem_tel.trim()).length() == 0) {
					errorMsgs.add("請填寫手機號碼");
				} else if (!mem_tel.trim().matches(mem_telReg)) {
					errorMsgs.add("請填寫有效的手機號碼");
				}

				String mem_pwd = req.getParameter("mem_pwd");
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請填寫密碼");
				}

				Collection<Part> parts = req.getParts();

				byte[] mem_pic = null;
				for (Part part : parts) {

					if (part.getName().equals("mem_pic") && part.getSize() != 0) {
						System.out.println(part.getName());
						InputStream in = part.getInputStream();
						ByteArrayOutputStream output = new ByteArrayOutputStream();
						mem_pic = new byte[in.available()];
						for (int length = 0; (length = in.read(mem_pic)) > 0;) {
							output.write(mem_pic, 0, length);
						}
						output.close();
						in.close();
					}
				}

				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

				if (!mem_pwd.equals(memberVO.getMem_pwd())) {
					errorMsgs.add("密碼錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberService memberSvc = new MemberService();

				memberVO = memberSvc.updateMember(memberVO.getMem_name(), mem_pwd, memberVO.getMem_gender(), mem_mail,
						mem_tel, memberVO.getMem_status(), mem_pic, memberVO.getMem_balance(), mem_nickname,
						memberVO.getMem_account());
				System.out.println("----------------update---------------------");
				String url = "/member/updateMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
				failureView.forward(req, res);

			}
		}
		if ("update_mem_pwd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String mem_pwd = req.getParameter("mem_pwd");
				if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請填寫密碼");
				}

				String mem_pwd_new = req.getParameter("mem_pwd_new");
				String mem_pwd_newReg = "^[0-9a-zA-z]{6,10}$";
				if (mem_pwd_new == null || (mem_pwd_new.trim()).length() == 0) {
					errorMsgs.add("請填寫密碼");
				} else if (!mem_pwd_new.trim().matches(mem_pwd_newReg)) {
					errorMsgs.add("密碼只能輸入數字及大小寫英文");
				}

				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

				if (!mem_pwd.equals(memberVO.getMem_pwd())) {
					errorMsgs.add("密碼錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberService memberSvc = new MemberService();

				memberVO = memberSvc.updateMember(memberVO.getMem_name(), mem_pwd_new, memberVO.getMem_gender(),
						memberVO.getMem_mail(), memberVO.getMem_tel(), memberVO.getMem_status(), memberVO.getMem_pic(),
						memberVO.getMem_balance(), memberVO.getMem_name(), memberVO.getMem_account());
				System.out.println("----------------update---------------------");
				String url = "/member/loginMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/updateMember.jsp");
				failureView.forward(req, res);
			}
		}
		if ("selectOneMember".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String mem_account_nickname = req.getParameter("mem_account_nickname");

				if (mem_account_nickname == null || (mem_account_nickname.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號或暱稱");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webWorking/FrontPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMemberByAccount(mem_account_nickname);
				List<MemberVO> memberList = new ArrayList<>();
				if(memberVO==null) {
					memberList = memberSvc.getOneMemberByNickname(mem_account_nickname);
					if(memberList.size()==0) {
						errorMsgs.add("查無此會員");
					}
				}else {
					memberList.add(memberVO);
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webWorking/FrontPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}


				req.setAttribute("memberVO", memberList);
				String url = "/member/serchMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println("-----------------------錯誤-------------------");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webWorking/FrontPage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
