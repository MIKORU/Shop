package com.alice.shop.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Cart;
import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.alice.shop.dao.CartMapper;
import com.alice.shop.dao.OrderMapper;
import com.alice.shop.dao.UserMapper;
import com.alice.shop.service.UserService;

import net.sf.json.JSONArray;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
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
		return userMapper.insertSelective(user);
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforUser(name);
		for(Map<String ,Object> k : list) {
			if(name.equals(k.get("name"))) {
				return password.equals((String)k.get("password"));
			}
		}
		return false;
	}

	@Override
	public boolean setPaying(int userId, int orderid) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userId);
		Order order = orderMapper.selectByPrimaryKey(orderid);
		int userMoney = user.getMoney();
		int orderMoney = order.getTotalprice();
		
		if(userMoney - orderMoney >=0 ) {
			userMoney -= orderMoney;
			user.setMoney(userMoney);
			order.setPay(1);
			userMapper.updateByPrimaryKeySelective(user);
			orderMapper.updateByPrimaryKeySelective(order);
			return true;
		}
		
		return false;
	}

	@Override
	public User getInfo(int userId) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public boolean updateInfo(String userId, String name, String password, String defaultAddress, String defaultPhone,
			String mail) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(new Integer(userId));
		user.setName(name);
		user.setAddress(defaultAddress);
		user.setPassword(password);
		user.setPhone(defaultPhone);
		user.setMail(mail);
		return 0!=userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String getPassword(String username) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforUser(username);
		for(Map<String ,Object> k : list) {
			if(username.equals(k.get("name"))) {
				return (String)k.get("password");
			}
		}
		return "";
	}

	@Override
	public String getId(String username) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforUser(username);
		for(Map<String ,Object> k : list) {
			if(username.equals(k.get("name"))) {
				return (String)k.get("id");
			}
		}
		return "";
	}

	@Override
	public int getRole(String username) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforUser(username);
		for(Map<String ,Object> k : list) {
			if(username.equals(k.get("name"))) {
				return (int)k.get("role");
			}
		}
		return 0;
	}
}
