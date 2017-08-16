package com.alice.shop.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Commodity;
import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.alice.shop.dao.CommodityMapper;
import com.alice.shop.dao.OrderMapper;
import com.alice.shop.dao.UserMapper;
import com.alice.shop.service.OrderService;
import com.alice.shop.util.utilUUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommodityMapper comMapper;

	@Override
	public boolean addForm(String userId, String address, String phone, float totalPrice, String pay, String orderlist) {
		// TODO Auto-generated method stub
		Order order = new Order();
		String id = utilUUID.getUUID();
		
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(StringUtils.isBlank(address)) address = user.getAddress();
		if(StringUtils.isBlank(phone)) phone = user.getPhone();
		
		order.setId(id);
		order.setUserid(userId);
		order.setAddress(address);
		order.setPhone(phone);
		order.setTotalprice(totalPrice);
		order.setPay(Integer.parseInt(pay));
		order.setOrderlist(orderlist);
		return 0!=orderMapper.insertSelective(order);
	}

	@Override
	public List<Order> getFormList(String userId) {
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
	public List<Order> getFormList(String userId, boolean flag) {
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

	@Override
	public boolean delForm(String userId) {
		// TODO Auto-generated method stub
		String orderlist = orderMapper.selectByPrimaryKey(userId).getOrderlist();
		JSONArray ja = JSONArray.fromObject(orderlist);
		for(int i=0;i<ja.size();i++) {
			String commodityId = (String) ja.getJSONObject(i).get("commodityid");
			int commodityCount = (int)ja.getJSONObject(i).get("commoditycount");
			Commodity com = comMapper.selectByPrimaryKey(commodityId);
			com.setAmount(com.getAmount()+commodityCount);
			comMapper.updateByPrimaryKeySelective(com);
		}
		return 0!=orderMapper.deleteByPrimaryKey(userId);
	}
}
