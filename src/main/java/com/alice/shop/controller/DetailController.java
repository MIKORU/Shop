package com.alice.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class DetailController {
	
	@RequestMapping
	public String initPage() {
		return "/detail";
	}
}
