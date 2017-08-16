package com.alice.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alice.shop.bean.Order;
import com.alice.shop.service.OrderService;
import com.alice.shop.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml"})
public class TestOrderService {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	public void testInsert() {
		String userId = "23152";
		String address = "广东省深圳市龙华新区盛世江南";
		float totalPrice = 128;
		String phone = "13682635486";
		String pay = "1";
		String orderlist = "";
		System.out.println(orderService.addForm(userId, address, phone, totalPrice, pay, orderlist));
	}
	
	public void testSetPaying(){
		System.out.println(userService.setPaying("23152","451274"));
	}

	@Test
	public void testdelOrder() {
		System.out.println(orderService.delForm("5171c18c75d049e2827fdfef54bfc3d4"));
	}
	
}
