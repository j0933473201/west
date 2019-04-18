package com.vendor.model;

import java.util.List;

public class VendorService {
	
	private VendorDAO_interface dao;
	
	public VendorService() {
		dao = new VendorJDBCDAO();
	}
	
//	public VendorVO addV() {
//		VendorVO vVO = new VendorVO();
//		
//		return vVO;
//	}
	
	public VendorVO findByPK(String vendor_no) {
		return dao.findByPrimaryKey(vendor_no);
	}
	
	public List<VendorVO> getAll() {
		return dao.getAll();
	}
}
