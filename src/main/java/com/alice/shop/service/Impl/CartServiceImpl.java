package com.alice.shop.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Cart;
import com.alice.shop.dao.CartMapper;
import com.alice.shop.service.CartService;

import net.sf.json.JSONArray;
@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public boolean addOrder(int userId, int commodityId, int commodityCount) {
		// TODO Auto-generated method stub
		
		Cart cart = new Cart();
		cart.setUserid(userId);
		cart.setCommodityid(commodityId);
		
		List<Map<String ,Object> > list = cartMapper.queryforOrder(cart);
		
		if(list.size() == 0) {
			cart.setCommoditycount(commodityCount);
			return 0!=cartMapper.insert(cart);
		}else {
			int count = (int)list.get(0).get("commodityCount");
			if(count != 0) {
				commodityCount += count;
			}
			cart.setCommoditycount(commodityCount);
			return 0!=cartMapper.updateByPrimaryKeySelective(cart);
		}
	}

	@Override
	public String getOrderList(int userId) {
		// TODO Auto-generated method stub
		Cart cart = new Cart();
		cart.setUserid(userId);
		List<Map<String ,Object>> list = cartMapper.queryforOrder(cart);
		return JSONArray.fromObject(list).toString();
	}

	@Override
	public boolean delOrder(int userId) {
		// TODO Auto-generated method stub
		return 0!=cartMapper.deleteOrderByuserId(userId);
	}

}
