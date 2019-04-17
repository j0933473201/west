package com.restaurant_transaction_list.model;

import java.sql.Timestamp;

public class RES_Transaction_ListVO implements java.io.Serializable{

	private String trst_no;
	private String vendor_no;
	private Double amount;
	private Timestamp pay_date;
	private String ord_no;
	private Double v_wallet;

	public String getTrst_no() {
		return trst_no;
	}

	public void setTrst_no(String trst_no) {
		this.trst_no = trst_no;
	}

	public String getVendor_no() {
		return vendor_no;
	}

	public void setVendor_no(String vendor_no) {
		this.vendor_no = vendor_no;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getPay_date() {
		return pay_date;
	}

	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public Double getV_wallet() {
		return v_wallet;
	}

	public void setV_wallet(Double v_wallet) {
		this.v_wallet = v_wallet;
	}

	@Override
	public String toString() {
		return "RES_transaction_listVO [trst_no=" + trst_no + ", vendor_no=" + vendor_no + ", amount=" + amount
				+ ", pay_date=" + pay_date + ", ord_no=" + ord_no + ", v_wallet=" + v_wallet + "]";
	}

}
