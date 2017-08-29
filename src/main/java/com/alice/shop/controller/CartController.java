package com.alice.shop.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alice.shop.service.CartService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONArray;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart")
	public String initPage() {
		return "/cart";
	}
	/**
	 * 
	 * @Title: addOrder   
	 * @Description: 增加购物车商品
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="addOrder",method=RequestMethod.POST)
	@ResponseBody
	public boolean addOrder(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("id");
		String commodityId = (String) request.getParameter("commodityIds");
		int commodityCount = Integer.parseInt((String) request.getParameter("commodityCounts"));
		return cartService.addOrder(userid, commodityId, commodityCount);
	}
	/**
	 * 
	 * @Title: getOrderList   
	 * @Description: 根据用户id获取订单
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getOrderList( HttpServletRequest request, HttpServletResponse response) {
		String userId = (String)request.getSession().getAttribute("id");
		return JSONArray.fromObject(cartService.getOrderList(userId));
	}
	/**
	 * 
	 * @Title: delCart   
	 * @Description: 删除根据商品id删除购物车内商品
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="delCart", method = RequestMethod.POST)
	@ResponseBody
	public boolean delCart(String commodityId) {
		return cartService.delOrderbyComId(commodityId);
	}
	
}
