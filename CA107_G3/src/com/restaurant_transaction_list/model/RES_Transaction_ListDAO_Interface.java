package com.restaurant_transaction_list.model;

import java.util.*;



public interface RES_Transaction_ListDAO_Interface {

	
	public void insert(RES_Transaction_ListVO res_transaction_listVO);
    public void update(RES_Transaction_ListVO res_transaction_listVO);
    public void delete(String trst_no);
    public RES_Transaction_ListVO findByPrimaryKey(String trst_no);
    public List<RES_Transaction_ListVO> getAll();
    public List<RES_Transaction_ListVO> getAll(Map<String, String[]> map);
}
