package com.alice.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;

@Controller
public class UtilController {
	
	@RequestMapping(value="getcontext",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getcontext(HttpServletRequest request,HttpServletResponse response) {
		String username = (String)request.getSession().getAttribute("name");
		String userid = (String)request.getSession().getAttribute("id");
		Map<String ,String > user = new HashMap<String,String>();
		user.put("name", username);
		user.put("id",userid );
		return JSONArray.fromObject(user);
	}
}
