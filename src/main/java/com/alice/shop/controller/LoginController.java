package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.UserService;
import com.alice.shop.util.CipherUtil;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String tologin() {
		return "/login";
	}
	
//	@Autowired  
//	HttpServletRequest request;
	@RequestMapping(value = "checkin", method = RequestMethod.POST)
	@ResponseBody
	public boolean login(
//			@RequestParam(value="user",required=true)String name,
//			@RequestParam(value="password",required=true)String password
			HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		String correctPassword = CipherUtil.generatePassword(password);
		
		UsernamePasswordToken token = new UsernamePasswordToken(name,correctPassword);
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()){
            currentUser.login(token);
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
