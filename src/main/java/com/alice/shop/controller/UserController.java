package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="reg",method=RequestMethod.POST)
	@ResponseBody
	public boolean reg(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String defaultAddress = request.getParameter("defaultAddress");
		String defaultPhone = request.getParameter("defaultPhone");
		String mail = request.getParameter("mail");
		userService.reg(name, password, defaultAddress, defaultPhone, mail);
		return false;
	}

	@RequestMapping("/index")
	public String initPage() {
		return "/index";
	}
	
	@RequestMapping("/user")
	public String userPage() {
		return "/user";
	}
}
