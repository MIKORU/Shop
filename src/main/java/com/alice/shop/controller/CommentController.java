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
		return JSONArray.fromObject(comService.getCommentById(commodityId));
	}
	
	@RequestMapping(value="getComments", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getComments(HttpServletRequest request, HttpServletResponse response) {
		return JSONArray.fromObject( comService.getComments( ) );
	}
	/**
	 * 
	 * @Title: addComment   
	 * @Description: 增加评论功能
	 * @param: @param request
	 * @param: @param response
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="addComment",method=RequestMethod.POST)
	@ResponseBody
	public boolean addComment(HttpServletRequest request,HttpServletResponse response) {
		String userId = (String) request.getSession().getAttribute("id");
		String userName = (String) request.getSession().getAttribute("name");
		String commodityID = request.getParameter("commodityID");
		String comment = request.getParameter("comment");
		return comService.addComment(userId, userName, commodityID, comment);
	}
	
}
