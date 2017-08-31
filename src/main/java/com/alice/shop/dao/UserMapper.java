package com.alice.shop.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<Map<String , Object> > queryforUser(String name);
    
    Set<String> findRoleByUserId(String id);
}