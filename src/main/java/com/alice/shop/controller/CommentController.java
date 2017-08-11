package com.alice.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
		if(!StringUtils.isBlank(commodityId)) {
			return JSONArray.fromObject(comService.getCommentById(Integer.parseInt(commodityId)));
		}else return null;
	}
	
	@RequestMapping(value="getComments", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getComments(HttpServletRequest request, HttpServletResponse response) {
		return JSONArray.fromObject( comService.getComments( ) );
	}
	
	@RequestMapping(value="addComment",method=RequestMethod.POST)
	@ResponseBody
	public boolean addComment(HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String commodityID = request.getParameter("commodityID");
		String comment = request.getParameter("comment");
		if(StringUtils.isBlank(userId)&& StringUtils.isBlank(commodityID)) return false;
		return comService.addComment(Integer.parseInt(userId), userName, Integer.parseInt(commodityID), comment);
	}
	
}
