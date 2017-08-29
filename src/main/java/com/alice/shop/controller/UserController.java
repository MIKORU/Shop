package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.UserService;

import net.sf.json.JSONObject;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @Title: reg   
	 * @Description: 注册
	 * @param: @param name
	 * @param: @param password
	 * @param: @param defaultAddress
	 * @param: @param defaultPhone
	 * @param: @param mail
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="reg",method=RequestMethod.POST)
	@ResponseBody
	public boolean reg(String name,String password ,String defaultAddress,
			String defaultPhone,String mail) {
		return 0!=userService.reg(name, password, defaultAddress, defaultPhone, mail);
	}

	@RequestMapping("/index")
	public String initPage() {
		return "/index";
	}
	
	@RequestMapping("/user")
	public String userPage() {
		return "/user";
	}
	
	@RequestMapping("/admin")
	public String userAdmin() {
		return "/admin";
	}
	/**
	 * 
	 * @Title: getInfo   
	 * @Description: 获取用户信息
	 * @param: @param request
	 * @param: @return      
	 * @return: JSONObject      
	 * @throws
	 */
	@RequestMapping(value="getInfo", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getInfo(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("id");
		return JSONObject.fromObject( userService.getInfo( userId ) );
	}
	/**
	 * 
	 * @Title: updateInfo   
	 * @Description: 更新用户信息
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value = "updateInfo", method=RequestMethod.POST)
	@ResponseBody
	public boolean updateInfo( HttpServletRequest request ) {
		String userId = (String) request.getSession().getAttribute("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String defaultAddress = request.getParameter("defaultAddress");
		String defaultPhone = request.getParameter("defaultPhone");
		String mail = request.getParameter("mail");
		return userService.updateInfo(userId, name, password, defaultAddress, defaultPhone, mail);
	}
	
}
