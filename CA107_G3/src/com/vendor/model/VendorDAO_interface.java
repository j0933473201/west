package com.vendor.model;

import java.util.List;

public interface VendorDAO_interface {
	
	public int insert(VendorVO vendorVO);

	public int update(VendorVO vendor);

	public int delete(String vendor_no);

	public VendorVO findByPrimaryKey(String vendor_no);

	public List<VendorVO> getAll();

}
