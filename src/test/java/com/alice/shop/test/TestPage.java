package com.alice.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alice.shop.bean.Commodity;
import com.alice.shop.service.CommodityService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml",
		"classpath:mybatis-config.xml"})
public class TestPage {
	
	@Autowired
	CommodityService comService;
	
	@Test
	public void testPage() {
		PageInfo<Commodity> info = comService.listCom(1, 3);
		System.out.println("查找结果" + JSON.toJSON(info));  
	}
	
}
