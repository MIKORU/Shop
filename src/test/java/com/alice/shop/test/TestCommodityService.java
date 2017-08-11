package com.alice.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alice.shop.service.CommodityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml"})
public class TestCommodityService {
	@Autowired
	private CommodityService comService;
	
	@Test
	public void testSearch() {
		
		System.out.println(JSON.toJSON(comService.search("甜")));
	}
	
}
