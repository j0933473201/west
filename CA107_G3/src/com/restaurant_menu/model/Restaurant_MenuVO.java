package com.restaurant_menu.model;

import java.io.Serializable;
import java.util.Arrays;

//MENU_NO           VARCHAR2(10 BYTE) NOT NULL
//VENDOR_NO         VARCHAR2(7 BYTE) 
//MENU_NAME         VARCHAR2(100 BYTE)
//MENU_PRICE        VARCHAR2(5 BYTE) 
//MENU_PIC BLOB     
//MENU_STAT         NUMBER(1)         
//MENU_TEXT         VARCHAR2(500 BYTE) 

public class Restaurant_MenuVO implements Serializable{
	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "Restaurant_MenuVO [menu_no=" + menu_no + ", vendor_no=" + vendor_no + ", menu_name=" + menu_name
				+ ", menu_price=" + menu_price + ", menu_pic=" + Arrays.toString(menu_pic) + ", menu_stat=" + menu_stat
				+ ", menu_text=" + menu_text + ", quantity=" + quantity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menu_name == null) ? 0 : menu_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant_MenuVO other = (Restaurant_MenuVO) obj;
		if (menu_name == null) {
			if (other.menu_name != null)
				return false;
		} else if (!menu_name.equals(other.menu_name))
			return false;
		return true;
	}
	private String menu_no;
	private String vendor_no;
	private String menu_name;
	private String menu_price;
	private byte[] menu_pic;
	private Integer menu_stat;
	private String menu_text;
	private  Integer quantity;
	
	
	public Restaurant_MenuVO() {
		super();
		menu_no = "";
		vendor_no = "";
		menu_name = "";
		menu_price="";
		menu_stat=0;
		menu_text = "";
		quantity=0;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menu_no) {
		this.menu_no = menu_no;
	}
	public String getVendor_no() {
		return vendor_no;
	}
	public void setVendor_no(String vendor_no) {
		this.vendor_no = vendor_no;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(String menu_price) {
		this.menu_price = menu_price;
	}
	public byte[] getMenu_pic() {
		return menu_pic;
	}
	public void setMenu_pic(byte[] menu_pic) {
		this.menu_pic = menu_pic;
	}
	public Integer getMenu_stat() {
		return menu_stat;
	}
	public void setMenu_stat(Integer menu_stat) {
		this.menu_stat = menu_stat;
	}
	public String getMenu_text() {
		return menu_text;
	}
	public void setMenu_text(String menu_text) {
		this.menu_text = menu_text;
	}

	

	
	
	

}
