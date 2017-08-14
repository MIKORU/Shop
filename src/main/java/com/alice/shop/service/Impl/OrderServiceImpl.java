package com.alice.shop.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Order;
import com.alice.shop.dao.OrderMapper;
import com.alice.shop.service.OrderService;
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public boolean addForm(int userId, String address, String phone, String totalPrice, String pay, String orderlist) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setAddress(address);
		order.setPhone(phone);
		order.setTotalprice(Integer.parseInt(totalPrice));
		order.setPay(Integer.parseInt(pay));
		order.setOrderlist(orderlist);
		return 0!=orderMapper.insert(order);
	}

	@Override
	public List<Order> getFormList(int userId) {
		// TODO Auto-generated method stub
		
		Order order = new Order();
		order.setUserid(userId);
		List<Order> list = orderMapper.getList(order);
		return list;
	}

	@Override
	public List<Order> getFormAllList() {
		// TODO Auto-generated method stub
		Order order = new Order();
		List<Order> orders =orderMapper.getList(order);
		return orders;
	}

	@Override
	public List<Order> getFormList(int userId, boolean flag) {
		// TODO Auto-generated method stub
		int payType =0;
		if(flag) {
			payType = 1;
		}else {
			payType =0;
		}
		Order order = new Order();
		order.setUserid(userId);
		order.setPay(payType);
		List<Order> list = orderMapper.getList(order);
		return list;
	}

}
