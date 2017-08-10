package com.alice.shop.dao;

import java.util.List;
import java.util.Map;

import com.alice.shop.bean.Type;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
    
    List<Type> queryAllType();
}