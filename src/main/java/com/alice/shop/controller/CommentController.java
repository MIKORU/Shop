package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alice.shop.service.CommentService;

import net.sf.json.JSONArray;
@Controller
public class CommentController {
	
	@Autowired
	private CommentService comService;
	
	@RequestMapping(value="getCommentById",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getCommentById(HttpServletRequest request, HttpServletResponse response) {
		String commodityId = request.getParameter("commodityId");
		return JSONArray.fromObject(comService.getCommentById(Integer.parseInt(commodityId)));
	}
	
	@RequestMapping(value="getComments", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getComments(HttpServletRequest request, HttpServletResponse response) {
		return JSONArray.fromObject( comService.getComments( ) );
	}
}
