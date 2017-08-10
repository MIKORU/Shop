package com.alice.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ListController {
	
	@RequestMapping("/list")
	public String initPage() {
		return "/list";
	}
}
