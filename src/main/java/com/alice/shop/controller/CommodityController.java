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
	
	@RequestMapping(value="getAllComByPage",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getAllComByPage(HttpServletRequest request,HttpServletResponse response) {
		int PageNo = Integer.parseInt(request.getParameter("pageNo"));
		int PageSize = Integer.parseInt(request.getParameter("pageSize"));
		return JSONArray.fromObject(commodityService.listCom(PageNo, PageSize));
	}
	
	@RequestMapping(value="getAllCom",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getAllCom(HttpServletRequest request,HttpServletResponse response) {
		return JSONArray.fromObject(commodityService.getAllCom());
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
		String id = request.getParameter("id") ;
		return JSONArray.fromObject( commodityService.getComById( id ) );
	}
	@RequestMapping(value="addPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean addPro(HttpServletRequest request, HttpServletResponse response) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		String name = request.getParameter("name");
		String depict = request.getParameter("depict");
		float price = Float.parseFloat( request.getParameter("price") );
		int amount = Integer.parseInt( request.getParameter("amount") );
		String manufacturer= request.getParameter("manufacturer");
		String img = request.getParameter("img");
		String type = request.getParameter("type");
		
		return commodityService.addPro( name,  depict,  price,  amount,  manufacturer,  img,  type);
	}
	
	@RequestMapping(value="delPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean delPro(HttpServletRequest request, HttpServletResponse response) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		String id = request.getParameter("id");
		return commodityService.delPro( id );
	}
	@RequestMapping(value="getComCount", method=RequestMethod.POST)
	@ResponseBody
	public int getComCount(HttpServletRequest request) {
		String commodityId = request.getParameter("commodityId");
		return commodityService.getComCount(commodityId);
	}
	@RequestMapping(value="editPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean editPro(HttpServletRequest request, HttpServletResponse response) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		String id = request.getParameter("commodityId");
		String name = request.getParameter("name");
		String depict = request.getParameter("depict");
		float price = Float.parseFloat( request.getParameter("price") );
		int amount = Integer.parseInt( request.getParameter("amount") );
		String manufacturer = request.getParameter("manufacturer");
		String img = request.getParameter("img");
		String type = request.getParameter("type");
		return commodityService.editPro(id, name, depict, price, amount, manufacturer, img, type);
	}
	
}