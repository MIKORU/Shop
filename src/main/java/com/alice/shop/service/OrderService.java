package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;

public interface OrderService {
	
	boolean addForm(String userId,String address,String phone,float totalPrice,String pay,String orderlist);
    
    List<Order> getFormList(String userId);
    
    List<Order> getFormAllList();
    
    List<Order> getFormList(String userId,boolean flag);
    
    boolean delForm(String userId);
}
