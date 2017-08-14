package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.OrderService;
import com.alice.shop.service.UserService;

import net.sf.json.JSONArray;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value="getFormList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormList(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userId"));
		return JSONArray.fromObject( orderService.getFormList(userid )) ;
	}
	
	@RequestMapping(value="pay", method=RequestMethod.POST)
	@ResponseBody
	public boolean setPaying(HttpServletRequest request) {
		int userId = Integer.parseInt( request.getParameter("userId") );
		int orderformId = Integer.parseInt( request.getParameter("orderformId") );
		return userService.setPaying(userId , orderformId);
	}
	@RequestMapping(value="getFormAllList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormAllList(HttpServletRequest request) {
		return JSONArray.fromObject( orderService.getFormAllList() );
	}
	
}
