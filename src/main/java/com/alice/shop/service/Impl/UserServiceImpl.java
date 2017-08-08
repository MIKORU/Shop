package com.alice.shop.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.alice.shop.dao.UserMapper;
import com.alice.shop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int reg(String name, String password, String defaultAddress, String defaultPhone, String mail) {
		// TODO Auto-generated method stub
		Timestamp time = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setName(name);
		user.setAddress(defaultAddress);
		user.setPassword(password);
		user.setPhone(defaultPhone);
		user.setMail(mail);
		user.setRegtime(time);
		return userMapper.insert(user);
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOrder(int userId, int commodityId, int commodityCount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOrderList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delOrder(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addForm(int userId, String address, String phone, String totalPrice, String pay, String orderlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> getFormList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getFormAllList() {
		// TODO Auto-generated method stub
		userMapper.getAllList();
		return null;
	}

	@Override
	public List<Order> getFormList(int userId, boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPaying(int userId, int orderid) {
		// TODO Auto-generated method stub
		return false;
	}

}
