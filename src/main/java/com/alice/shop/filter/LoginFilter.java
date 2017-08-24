package com.alice.shop.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class LoginFilter extends PathMatchingFilter {
	
	private String loginURL = "/login";
	private String successURL = "/index";

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		if(SecurityUtils.getSubject().isAuthenticated()) {
			return true;
		}
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		if(isLoginRequest(req)) {
			if("post".equalsIgnoreCase(req.getMethod())) {
				boolean successLogin = login(req);
				if(successLogin) {
					return redirectToSuccessUrl(req,res);
				}
			}
			return true;
		}else {
			saveRequstUrl(req,res);
			return false;
		}
	}
	
	@Override
	protected boolean preHandle(ServletRequest arg0, ServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(arg0, arg1);
	}
	private boolean redirectToSuccessUrl(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		WebUtils.redirectToSavedRequest(request, response, loginURL);
		return false;
	}
	
	private void saveRequstUrl(HttpServletRequest request,HttpServletResponse response) throws IOException {
		WebUtils.saveRequest(request);
		WebUtils.redirectToSavedRequest(request, response, loginURL);
	}
	
	private boolean login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try{
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username,password));
		}catch(Exception e) {
			request.setAttribute("loginFailed", e.getClass());
			return false;
		}
		return true;
	}
	
	private boolean isLoginRequest(HttpServletRequest request) {
		return pathsMatch(loginURL,WebUtils.getPathWithinApplication(request));
	}
	
}
