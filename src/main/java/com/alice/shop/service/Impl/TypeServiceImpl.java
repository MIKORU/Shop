package com.alice.shop.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alice.shop.bean.Type;
import com.alice.shop.dao.TypeMapper;
import com.alice.shop.service.TypeService;
@Service("typeService")
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public List<Type> getTypes() {
		// TODO Auto-generated method stub
		return typeMapper.queryAllType();
	}

	@Override
	public boolean addType(String name) {
		// TODO Auto-generated method stub
		Type type = new Type();
		type.setName(name);
		return 0!=typeMapper.insertSelective(type);
	}

	@Override
	public boolean delType(String id) {
		// TODO Auto-generated method stub
		return 0!=typeMapper.deleteByPrimaryKey(new Integer(id));
	}
}
