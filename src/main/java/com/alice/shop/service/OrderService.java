package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;

public interface OrderService {
	
	boolean addForm(int userId,String address,String phone,float totalPrice,String pay,String orderlist);
    
    List<Order> getFormList(int userId);
    
    List<Order> getFormAllList();
    
    List<Order> getFormList(int userId,boolean flag);
}
