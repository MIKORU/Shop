package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alice.shop.service.CartService;
import com.alice.shop.service.OrderService;
import com.alice.shop.service.UserService;

import net.sf.json.JSONArray;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;

	
	@RequestMapping(value="getFormList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormList(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("id");
		return JSONArray.fromObject( orderService.getFormList(userid )) ;
	}
	
	@RequestMapping(value="pay", method=RequestMethod.POST)
	@ResponseBody
	public boolean setPaying(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("id");
		String orderId = request.getParameter("orderId");
		return userService.setPaying(userId , orderId);
	}
	
	@RequestMapping(value="getFormAllList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormAllList(HttpServletRequest request) {
		return JSONArray.fromObject( orderService.getFormAllList() );
	}
	
	@RequestMapping(value="addForm",method=RequestMethod.POST)
	@ResponseBody
	public boolean addForm(HttpServletRequest request) {
		String userId = (String)request.getSession().getAttribute("id");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
		String pay = "0";
		String orderlist = request.getParameter("orderList");
		
		if( orderService.addForm(userId, address, phone, totalPrice, pay, orderlist)) {
			return cartService.delOrder(userId);
		}else {
			return false;
		}
	}
	@RequestMapping(value="delOrder",method=RequestMethod.POST)
	@ResponseBody
	public boolean delOrder(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		return orderService.delForm(userId);
	}
	
}
