package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.TypeService;

import net.sf.json.JSONArray;

@Controller
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	@RequestMapping(value="getTypes", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getTypes() {
		JSONArray jsonArray = JSONArray.fromObject( typeService.getTypes() );
		return jsonArray;
	}
	
	@RequestMapping(value="addType", method=RequestMethod.POST)
	@ResponseBody
	public boolean addType(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		return typeService.addType(type);
	}

	@RequestMapping(value="delType", method=RequestMethod.POST)
	@ResponseBody
	public boolean delType(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		return typeService.delType(id);
	}
	
}
