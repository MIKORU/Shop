package com.alice.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alice.shop.dao.UserMapper;

@Repository
public class UserService {
	@Autowired
	UserMapper userMapper;

	public boolean addOrder(int userId, String commodityIds, String commodityCounts) {
		String[] commodityIdsArr = commodityIds.split(",");
		String[] commodityCountsArr = commodityCounts.split(",");

		boolean flag = true;
		for (int i = 0; i < commodityIdsArr.length; i++) {
		}
		return flag;
	}

}
