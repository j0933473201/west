package com.vendor.model;
import java.util.*;


	
	public interface VendorDAO_interface {

		
		public void insert(VendorVO vendorVO);
	    public void update(VendorVO vendorVO);
	    public void delete(String vendor_no);
	    public VendorVO findByPrimaryKey(String vendor_no);
	    public List<VendorVO> getAll();
	    public List<VendorVO> getAll(Map<String, String[]> map);
	}

