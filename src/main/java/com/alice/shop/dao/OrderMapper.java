package com.alice.shop.dao;

import com.alice.shop.bean.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);
}