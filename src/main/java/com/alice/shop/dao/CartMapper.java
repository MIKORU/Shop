package com.alice.shop.dao;

import java.util.List;
import java.util.Map;

import com.alice.shop.bean.Cart;
import com.alice.shop.bean.Order;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    
    List<Map<String ,Object> > queryforOrder(Cart cart);
    
    List<Order> queryForOrder(int userId);
    
    int deleteOrderByuserId(int userId);
}