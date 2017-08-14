package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;

public interface CartService {
	
	boolean addOrder(int userId,int commodityId,int commodityCount);
	
	List<Order> getOrderList(int userId);
	
	boolean delOrder(int userId);
	
}
