package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Type;

public interface TypeService {
	
	public List<Type> getTypes();
	
	public boolean addType(String name);
	
	public boolean delType(String id);

}
