package com.alice.shop.dao;

import java.util.List;
import java.util.Map;

import com.alice.shop.bean.Cart;
import com.alice.shop.bean.Order;

public interface CartMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    
    List<Map<String ,Object> > queryforOrder(Cart cart);
    
    List<Order> queryForOrder(String userId);
    
    int deleteOrderByuserId(String userId);
    
    int delOrderbyComId(String commodityId);
}