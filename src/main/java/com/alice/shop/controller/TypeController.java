package com.alice.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alice.shop.service.TypeService;

@Controller
public class TypeController {
	@Autowired
	private TypeService typeService;
	
}
