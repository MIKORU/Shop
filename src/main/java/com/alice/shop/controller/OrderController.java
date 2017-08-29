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
	
	@RequestMapping("/list")
	public String initPage() {
		return "/list";
	}
	/**
	 * 
	 * @Title: getFormList   
	 * @Description: 根据用户id获取订单（订单页）
	 * @param: @param request
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getFormList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormList(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("id");
		return JSONArray.fromObject( orderService.getFormList(userid )) ;
	}
	/**
	 * 
	 * @Title: setPaying   
	 * @Description: 支付（伪）
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="pay", method=RequestMethod.POST)
	@ResponseBody
	public boolean setPaying(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("id");
		String orderId = request.getParameter("orderId");
		return userService.setPaying(userId , orderId);
	}
	/**
	 * 
	 * @Title: getFormAllList   
	 * @Description: 获取所有订单（管理页）
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getFormAllList", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getFormAllList() {
		return JSONArray.fromObject( orderService.getFormAllList() );
	}
	/**
	 * 
	 * @Title: addForm   
	 * @Description: 增加订单
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
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
	/**
	 * 
	 * @Title: delOrder   
	 * @Description:根据id删除订单
	 * @param: @param userId
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="delOrder",method=RequestMethod.POST)
	@ResponseBody
	public boolean delOrder(String userId) {
		return orderService.delForm(userId);
	}
	
}
