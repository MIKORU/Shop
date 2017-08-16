package com.alice.shop.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Cart;
import com.alice.shop.bean.Commodity;
import com.alice.shop.bean.Order;
import com.alice.shop.dao.CartMapper;
import com.alice.shop.dao.CommodityMapper;
import com.alice.shop.service.CartService;
import com.alice.shop.util.utilUUID;

import net.sf.json.JSONArray;
@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private CommodityMapper comMapper;
	
	/**
	 *购物车增加商品（订单）
	 *@author mikoru
	 *@param 
	 */
	@Override
	public boolean addOrder(String userId, String commodityId, int commodityCount) {
		// TODO Auto-generated method stub
		
		Cart cart = new Cart();
		cart.setUserid(userId);
		cart.setCommodityid(commodityId);
		
		//商品数量不够，添加不成功
		Commodity com = comMapper.selectByPrimaryKey(commodityId);
		if(commodityCount > 0) {
			if(com.getAmount() > 0) {
				com.setAmount(com.getAmount()-commodityCount);
			}else {
				return false;
			}
			comMapper.updateByPrimaryKeySelective(com);
		}else {
			com.setAmount(com.getAmount()-commodityCount);
			comMapper.updateByPrimaryKeySelective(com);
		}
		
		//更新订单数量
		List<Map<String ,Object> > list = cartMapper.queryforOrder(cart);
		
		if(list.size() == 0) {
			String id = utilUUID.getUUID();
			cart.setId(id);
			cart.setCommoditycount(commodityCount);
			return 0!=cartMapper.insert(cart);
		}else {
			int count = (int)list.get(0).get("commoditycount");
			if(count != 0) {
				commodityCount += count;
			}
			cart.setCommoditycount(commodityCount);
			return 0!=cartMapper.updateByPrimaryKeySelective(cart);
		}
	}
	@Override
	public List<Order> getOrderList(String userId) {
		// TODO Auto-generated method stub
		List<Order> list = cartMapper.queryForOrder(userId);
		return list;
	}

	@Override
	public boolean delOrder(String userId) {
		// TODO Auto-generated method stub
		return 0!=cartMapper.deleteOrderByuserId(userId);
	}
	@Override
	public boolean delOrderbyComId(String commodityId) {
		// TODO Auto-generated method stub
		return 0!=cartMapper.delOrderbyComId(commodityId);
	}
}
