package com.alice.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@RequestMapping
	public String initPage() {
		return "/cart";
	}
	
}
