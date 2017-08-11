package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart")
	public String initPage() {
		return "/cart";
	}
	
	@RequestMapping(value="addOrder",method=RequestMethod.POST)
	@ResponseBody
	public boolean addOrder(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int commodityId = Integer.parseInt(request.getParameter("commodityIds"));
		int commodityCount = Integer.parseInt(request.getParameter("commodityCounts"));
		return cartService.addOrder(userId, commodityId, commodityCount);
	}
	
	@RequestMapping(value="getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public String getOrderList( HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt( request.getParameter("userId") );
		return cartService.getOrderList(userId);
	}
}
