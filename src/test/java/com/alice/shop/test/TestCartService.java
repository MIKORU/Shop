package com.alice.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alice.shop.service.CartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml"})
public class TestCartService {
	
	@Autowired
	private CartService cartService;
	
	public void TestgetOrderList() {
		System.out.println(JSON.toJSON(cartService.getOrderList("123456")));
	}
	
	@Test
	public void testdelOrder() {
		System.out.println(cartService.delOrder("23152"));
	}
}
