package com.ord.model;

import java.util.List;

import com.ord_detail.model.Order_DetailVO;



public interface OrdDAO_interface {
	public void insert(OrdVO OrdVO);
    public void update(OrdVO OrdVO);
    public void delete(String ord_no);
    public OrdVO findByPrimaryKey(String ord_no);
    public List<OrdVO> getAll();
    
    
    public void insertWithOrd_detail(OrdVO OrdVO , List<Order_DetailVO> list);
}
