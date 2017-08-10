package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Commodity;

public interface CommodityService {
	
	public boolean addPro(String name,String depict,int price,int amount,String manufacturer,String img,String type);
	
	public boolean delPro(int id);
	
	public List<Commodity> getAllCom();
	
	public Commodity getComById(int id);
	
	public List<Commodity> getComByType(String type);
	
	public List<Commodity> search(String keyword);
	
}
