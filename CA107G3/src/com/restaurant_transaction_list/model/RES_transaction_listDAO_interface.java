package com.restaurant_transaction_list.model;

import java.util.*;



public interface RES_transaction_listDAO_interface {

	
	public void insert(RES_transaction_listVO res_transaction_listVO);
    public void update(RES_transaction_listVO res_transaction_listVO);
    public void delete(String trst_no);
    public RES_transaction_listVO findByPrimaryKey(String trst_no);
    public List<RES_transaction_listVO> getAll();
    public List<RES_transaction_listVO> getAll(Map<String, String[]> map);
}
