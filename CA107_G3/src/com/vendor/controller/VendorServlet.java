package com.vendor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/VendorServlet")

public class VendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VendorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 登入
		if ("login".equals(action)) {

		}

		//註冊
		if ("insert".equals(action)) {

		}

	}

}
