package com.alice.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GetMethod {
	@RequestMapping(value="cart", method=RequestMethod.GET)
	public String cart() {
		return "/cart";
	}

	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String detail() {
		return "/detail";
	}

	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index() {
		return "/index";
	}

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list() {
		return "/list";
	}

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value="user", method=RequestMethod.GET)
	public String user() {
		return "/user";
	}
	
	@RequestMapping(value="admin", method=RequestMethod.GET)
	public String admin() {
		return "/admin";
	}
}
