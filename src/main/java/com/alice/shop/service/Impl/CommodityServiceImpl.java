package com.alice.shop.service.Impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Commodity;
import com.alice.shop.dao.CommodityMapper;
import com.alice.shop.service.CommodityService;
import com.alice.shop.util.utilUUID;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
	
	@Autowired
	private CommodityMapper comMapper;
	
	@Override
	public boolean addPro(String name, String depict, float price, int amount, String manufacturer, String img,
			String type) {
		// TODO Auto-generated method stub
		Commodity com = new Commodity();
		String id = utilUUID.getUUID();
		com.setId(id);
		com.setAmount(amount);
		com.setName(name);
		com.setDepict(depict);
		com.setPrice(new Float(price));
		com.setImg(img);
		com.setType(type);
		com.setManufacturer(manufacturer);
		return 0!=comMapper.insertSelective(com);
	}

	@Override
	public boolean delPro(String id) {
		// TODO Auto-generated method stub
		return 0!=comMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Commodity> getAllCom() {
		// TODO Auto-generated method stub
		Commodity com = new Commodity();
		List<Commodity> coms = comMapper.queryAllList(com);
		return coms;
	}

	@Override
	public Commodity getComById(String id) {
		// TODO Auto-generated method stub
		return comMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Commodity> getComByType(String type) {
		// TODO Auto-generated method stub
		Commodity com = new Commodity();
		com.setType(type);
		return comMapper.queryAllList(com);
	}

	@Override
	public List<Commodity> search(String keyword) {
		// TODO Auto-generated method stub
		return comMapper.serach(keyword);
	}

	@Override
	public int getComCount(String commodityId) {
		// TODO Auto-generated method stub
		return comMapper.selectByPrimaryKey(commodityId).getAmount();
	}

	@Override
	public boolean editPro(String id,String name, String depict, 
			float price, int amount, String manufacturer, String img,String type) {
		// TODO Auto-generated method stub
		Commodity com = new Commodity();
		com.setId(id);
		com.setAmount(amount);
		com.setName(name);
		com.setDepict(depict);
		com.setPrice(new Float(price));
		com.setImg(img);
		com.setType(type);
		com.setManufacturer(manufacturer);
		
		return 0!=comMapper.updateByPrimaryKey(com);
	}	
	
}
