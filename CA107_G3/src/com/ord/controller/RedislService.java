package com.ord.controller;

import javax.mail.MessagingException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class RedislService {
	
	public void insetshare(String sharetotal,String share_amount1,String share_amount2) {
	
		//建立連線
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		
//		//放分攤金額及分攤會員編號
//		jedis.set(share_mem_no1, share_amount1);
//		jedis.lpush("customers", "David", "James", "Vincent", "Ben", "Ron", "George", "Howard");
		jedis.rpush(sharetotal,share_amount1, share_amount2);
		jedis.close();
	}
	
	public boolean forcheck(String pay_name,String pay_amount ) {
		//建立連線
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		
		//檢查金額
		String  share_amount = jedis.get(pay_name);
		if(share_amount.equals(pay_amount)) {
			return true;
		}
		
		return false;
		
	}
	
	
	
	
	// 建立連線
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
	
		jedis.rpush("1000", "500","500");
		
		
		
		
		jedis.close();
	}
	

}

