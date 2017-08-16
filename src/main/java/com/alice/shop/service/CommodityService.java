package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Commodity;

public interface CommodityService {
	
	public boolean addPro(String name,String depict,float price,int amount,String manufacturer,String img,String type);
	
	public boolean delPro(String id);
	
	public List<Commodity> getAllCom();
	
	public Commodity getComById(String id);
	
	public List<Commodity> getComByType(String type);
	
	public List<Commodity> search(String keyword);
	
	public int getComCount(String commodityId);
	
	public boolean editPro(String id,String name,String depict,float price,int amount,String manufacturer,String img,String type);
	
}
