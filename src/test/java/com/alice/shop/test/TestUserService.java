package com.alice.shop.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.alice.shop.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml"})
public class TestUserService {
	@Autowired
	private UserService userService;
	public void testQueryAllList() {
		List<Order> orders = userService.getFormAllList();
		System.out.println(JSON.toJSON(orders));
	}
	public void testgetUser() {
		User user = userService.getInfo(23152);
		System.out.println(JSON.toJSON(user));
	}
	public void testgetPassword() {
		String password = "555666";
		String name="mikoru";
		System.out.println(userService.login(name, password));
	}
	@Test
	public void testaddOrder() {
		int userid=23152;
		int commodityid=45125632;
		int commodityCount=10;
		System.out.println(userService.addOrder(userid, commodityid, commodityCount));
	}
	@Test
	public void testgetList() {
		int userid=23152;
		System.out.println(userService.getOrderList(userid));
	}
	
	public void testdelOrder() {
		int userid=23152;
		System.out.println(userService.delOrder(userid));
	}
}