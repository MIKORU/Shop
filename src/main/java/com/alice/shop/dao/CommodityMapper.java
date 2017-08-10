package com.alice.shop.dao;

import java.util.List;

import com.alice.shop.bean.Commodity;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKeyWithBLOBs(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    List<Commodity> queryAllList(Commodity com);
    
    List<Commodity> serach(String keyword);
}