package com.restaurant_menu.model;

import java.util.List;



public interface Restaurant_MenuDAO_interface {
	
	public void insert(Restaurant_MenuVO Restaurant_MenuVO);
	public void update(Restaurant_MenuVO Restaurant_MenuVO);
	public void delete(String menu_no);
	public Restaurant_MenuVO findByPK(String menu_no);
	public List<Restaurant_MenuVO> getVendor(String vendor_no);
	public List<Restaurant_MenuVO> getAll();
	public List<Restaurant_MenuVO>getm_name(String vendor_no);

}
