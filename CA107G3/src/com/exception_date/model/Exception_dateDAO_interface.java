package com.exception_date.model;

import java.util.List;
import java.util.Map;

import com.vendor.model.VendorVO;

public interface Exception_dateDAO_interface {

	public void insert(Exception_dateVO exception_dateVO);
    public void update(Exception_dateVO exception_dateVO);
    public void delete(String exc_no);
    public Exception_dateVO findByPrimaryKey(String exc_no);
    public List<Exception_dateVO> getAll();
    public List<Exception_dateVO> getAll(Map<String, String[]> map);
	   
} 
