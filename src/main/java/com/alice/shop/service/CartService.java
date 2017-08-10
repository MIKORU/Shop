package com.alice.shop.service;

public interface CartService {
	
	boolean addOrder(int userId,int commodityId,int commodityCount);
	
	String getOrderList(int userId);
	
	boolean delOrder(int userId);
	
}
