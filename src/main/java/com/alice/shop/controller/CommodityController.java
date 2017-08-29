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
	
	@RequestMapping("/detail")
	public String initPage() {
		return "/detail";
	}
	
	/**
	 * 
	 * @Title: getAllComByPage   
	 * @Description: 分页展示所有商品
	 * @param: @param pageNo
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getAllComByPage",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getAllComByPage(Integer pageNo,Integer pageSize) {
		return JSONArray.fromObject(commodityService.listCom(pageNo, pageSize));
	}
	/**
	 * 
	 * @Title: getAllCom   
	 * @Description: 获取所有商品
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getAllCom",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getAllCom() {
		return JSONArray.fromObject(commodityService.getAllCom());
	}
	
	/**
	 * 
	 * @Title: search   
	 * @Description: 根据关键字查询商品
	 * @param: @param keyword
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="search", method=RequestMethod.POST) 
	@ResponseBody
	public JSONArray search(String keyword){
		return JSONArray.fromObject( commodityService.search(keyword) );
	}
	/**
	 * 
	 * @Title: getComById   
	 * @Description: 根据id获取商品信息
	 * @param: @param id
	 * @param: @return      
	 * @return: JSONArray      
	 * @throws
	 */
	@RequestMapping(value="getComById", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray getComById(String id) {
		return JSONArray.fromObject( commodityService.getComById( id ) );
	}
	/**
	 * 
	 * @Title: addPro   
	 * @Description: 增加商品
	 * @param: @param name
	 * @param: @param depict
	 * @param: @param price
	 * @param: @param amount
	 * @param: @param manufacturer
	 * @param: @param img
	 * @param: @param type
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="addPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean addPro(String name,String depict,Float price,Integer amount,
			String manufacturer,String img,String type) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type		
		return commodityService.addPro( name,  depict,  price,  amount,  manufacturer,  img,  type);
	}
	/**
	 * 
	 * @Title: delPro   
	 * @Description: 删除商品
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="delPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean delPro(String id) {
		//String name, String depict, int price, int amount, String manufacturer, String img, String type
		return commodityService.delPro( id );
	}
	
	/**
	 * 
	 * @Title: getComCount   
	 * @Description: 获取商品数量
	 * @param: @param commodityId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	@RequestMapping(value="getComCount", method=RequestMethod.POST)
	@ResponseBody
	public int getComCount(String commodityId) {
		return commodityService.getComCount(commodityId);
	}
	/**
	 * 
	 * @Title: editPro   
	 * @Description: 根据id编辑该商品
	 * @param: @param id
	 * @param: @param name
	 * @param: @param depict
	 * @param: @param price
	 * @param: @param amount
	 * @param: @param manufacturer
	 * @param: @param img
	 * @param: @param type
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@RequestMapping(value="editPro", method=RequestMethod.POST)
	@ResponseBody
	public boolean editPro(String id,String name,String depict,Float price,Integer amount,
			String manufacturer,String img,String type) {
		return commodityService.editPro(id, name, depict, price, amount, manufacturer, img, type);
	}
	
}