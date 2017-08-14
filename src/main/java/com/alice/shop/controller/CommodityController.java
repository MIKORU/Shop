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
	@RequestMapping(value="search", method=RequestMethod.POST) 
	@ResponseBody
	public JSONArray search(HttpServletRequest request, HttpServletResponse response){
		String keyword = request.getParameter("keyword");
		return JSONArray.fromObject( commodityService.search(keyword) );
	}
	
	@RequestMapping(value="getComById", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getComById(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt( request.getParameter("id") );
		return JSONArray.fromObject( commodityService.getComById( id ) );
	}
	@RequestMapping(value="addPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean addPro(HttpServletRequest request, HttpServletResponse response) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		String name = request.getParameter("name");
		String depict = request.getParameter("depict");
		int price = Integer.parseInt( request.getParameter("price") );
		int amount = Integer.parseInt( request.getParameter("amount") );
		String manufacturerString = request.getParameter("manufacturer");
		String img = request.getParameter("img");
		String type = request.getParameter("type");
		
		return commodityService.addPro( name,  depict,  price,  amount,  manufacturerString,  img,  type);
	}
	
	@RequestMapping(value="delPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean delPro(HttpServletRequest request, HttpServletResponse response) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		int id = Integer.parseInt(request.getParameter("id") );
		return commodityService.delPro( id );
	}
	
}