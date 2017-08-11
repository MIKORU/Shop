package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired  
//	HttpServletRequest request;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public boolean login(
//			@RequestParam(value="user",required=true)String name,
//			@RequestParam(value="password",required=true)String password
			HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		String correctPassword = userService.getPassword(name);
		if(correctPassword.equals(password)) {
			session.setAttribute("name",name);
			session.setAttribute("id",userService.getId(name));
			if( 1 == userService.getRole(name)) {
				session.setAttribute("role", 1);
			}else {
				session.setAttribute("role", 0);
			}
			return true;
		}
		return false;
	}
}
