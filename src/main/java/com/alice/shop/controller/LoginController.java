package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String tologin() {
		return "/login";
	}
	/**
	 * 
	 * @Title: logout   
	 * @Description: 登出功能
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="logout",method=RequestMethod.POST)
	@ResponseBody
	public boolean logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
//		request.getSession().removeAttribute("id");
//		request.getSession().removeAttribute("name");
		return true;
	}
	
	/**
	 * 
	 * @Title: login   
	 * @Description: 登录
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value = "checkin", method = RequestMethod.POST)
	@ResponseBody
	public boolean login(HttpServletRequest request) {
		String name = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		String correctPassword = new Md5Hash(password).toString().toLowerCase();
		UsernamePasswordToken token = new UsernamePasswordToken(name,correctPassword);
		Subject currentUser = SecurityUtils.getSubject();
		
		HttpSession session = request.getSession();
		
		if (!currentUser.isAuthenticated()){
            currentUser.login(token);
            session.setAttribute("name",name);
			session.setAttribute("id",userService.getId(name));
			session.setAttribute("role", userService.getRole(name));
			return true;
        }
		return false;
	}
}
