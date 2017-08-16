package com.alice.shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alice.shop.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
		"classpath:spring-mybatis.xml"})
public class TestCommentService {
	@Autowired
	private CommentService commentService;
	
	@Test
	public void testGetAllList() {
		System.out.println(JSON.toJSON(commentService.getCommentById("124522")));
	}
	
}
