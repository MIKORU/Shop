package com.alice.shop.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Cart;
import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.alice.shop.dao.CartMapper;
import com.alice.shop.dao.OrderMapper;
import com.alice.shop.dao.UserMapper;
import com.alice.shop.service.UserService;
import com.alice.shop.util.UUIDUtil;

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
		String id = UUIDUtil.getUUID();
		user.setId(id);
		user.setName(name);
		user.setAddress(defaultAddress);
		user.setPassword(new Md5Hash(password).toString().toLowerCase());
		user.setPhone(defaultPhone);
		user.setMail(mail);
		user.setRole(0);
		user.setRegtime(time);
		return userMapper.insertSelective(user);
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforUser(name);
		for(Map<String ,Object> k : list) {
			if(name.equals(k.get("name"))) {
				return (new Md5Hash(password).toString().toLowerCase()).equals(String.valueOf(k.get("password")));
			}
		}
		return false;
	}

	@Override
	public boolean setPaying(String userId, String orderid) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userId);
		Order order = orderMapper.selectByPrimaryKey(orderid);
		int userMoney = user.getMoney();
		float orderMoney = order.getTotalprice();
		
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
	public User getInfo(String userId) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public boolean updateInfo(String userId, String name, String password, String defaultAddress, String defaultPhone,
			String mail) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setAddress(defaultAddress);
		user.setPassword(new Md5Hash(password).toString());
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
				return String.valueOf(k.get("password"));
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
				return String.valueOf(k.get("id"));
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

	@Override
	public User getUserbyName(String username) {
		// TODO Auto-generated method stub
		List<Map<String , Object> > users = userMapper.queryforUser(username);
		for(Map<String,Object> k:users) {
			if(username.equals(k.get("name"))) {
				return userMapper.selectByPrimaryKey((String)k.get("id"));
			}
		}
		return null;
	}
}
