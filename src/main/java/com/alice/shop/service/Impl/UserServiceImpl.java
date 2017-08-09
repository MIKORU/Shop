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
	private CartMapper cartMapper;
	
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
		return userMapper.insert(user);
	}

	@Override
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > list = userMapper.queryforPassword(name);
		for(Map<String ,Object> k : list) {
			if(name.equals(k.get("name"))) {
				return password.equals((String)k.get("password"));
			}
		}
		return false;
	}

	@Override
	public boolean addOrder(int userId, int commodityId, int commodityCount) {
		// TODO Auto-generated method stub
		List<Map<String ,Object> > list = cartMapper.queryforOrder(userId, commodityId);
		
		if(list.size() == 0) {
			Cart cart = new Cart();
			cart.setCommodityid(commodityId);
			cart.setUserid(userId);
			cart.setCommoditycount(commodityCount);
			return 0!=cartMapper.insert(cart);
		}else {
			int count = cartMapper.queryforCommodityCount(userId, commodityId);
			if(count != 0) {
				commodityCount += count;
			}
			return 0!=cartMapper.updateByuserId(commodityCount,userId, commodityId);
		}
	}

	@Override
	public String getOrderList(int userId) {
		// TODO Auto-generated method stub
		List<Map<String ,Object>> list = cartMapper.queryByuserid(userId);
		return JSONArray.fromObject(list).toString();
	}

	@Override
	public boolean delOrder(int userId) {
		// TODO Auto-generated method stub
		return 0!=cartMapper.deleteOrderByuserId(userId);
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
		List<Order> orders =orderMapper.getAllList();
		return orders;
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
		return false;
	}

	@Override
	public String getPassword(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRole(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

}
