package com.alice.shop.dao;

import java.util.List;
import java.util.Map;

import com.alice.shop.bean.Cart;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    
    int updateByuserId(int commodityCount,int userid,int commodityId);
    
    List<Map<String ,Object> > queryforOrder(int userid,int commodityId);
    
    int queryforCommodityCount(int userid,int commodityId);
    
    List<Map<String ,Object> > queryByuserid(int userid);
    
    int deleteOrderByuserId(int userId);
}