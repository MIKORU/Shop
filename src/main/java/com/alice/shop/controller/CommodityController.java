package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.CommodityService;

import net.sf.json.JSONArray;

@Controller
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping(value="getAllCom",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getAllCom(HttpServletRequest request,HttpServletResponse response) {
		JSONArray ja = JSONArray.fromObject(commodityService.getAllCom());
		return ja;
	}
}