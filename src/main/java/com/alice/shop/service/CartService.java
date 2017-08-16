package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;

public interface CartService {
	
	boolean addOrder(String userId,String commodityId,int commodityCount);
	
	List<Order> getOrderList(String userId);
	
	boolean delOrder(String userId);
	
	boolean delOrderbyComId(String commodityId);
	
}
