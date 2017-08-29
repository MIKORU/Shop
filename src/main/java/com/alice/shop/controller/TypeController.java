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
	/**
	 * 
	 * @Title: getTypes   
	 * @Description: 获取所有类型
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getTypes", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getTypes() {
		JSONArray jsonArray = JSONArray.fromObject( typeService.getTypes() );
		return jsonArray;
	}
	/**
	 * 
	 * @Title: addType   
	 * @Description: 增加类型
	 * @param: @param type
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="addType", method=RequestMethod.POST)
	@ResponseBody
	public boolean addType(String type) {
		return typeService.addType(type);
	}
	/**
	 * 
	 * @Title: delType   
	 * @Description: 删除类型
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="delType", method=RequestMethod.POST)
	@ResponseBody
	public boolean delType(String id) {
		return typeService.delType(id);
	}
	
}
