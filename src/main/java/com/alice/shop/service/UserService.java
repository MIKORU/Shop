package com.alice.shop.service;

import java.util.List;
import java.util.Set;

import com.alice.shop.bean.Commodity;
import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	int reg(String name,String password,String defaultAddress,String defaultPhone,String mail);
    
    boolean login(String name,String password);
    
    boolean setPaying(String userId,String orderid);
    
    public User getInfo( String userId );
    
    public boolean updateInfo(String userId, String name,String  password, String defaultAddress, String defaultPhone, String mail);
    
    public String getPassword(String username);
    
    public String getId(String username);
    
    public int getRole(String username);
    
    public User getUserbyName(String username);
    
    public Set<String> findRoleByUserId(String userId);
    
}
