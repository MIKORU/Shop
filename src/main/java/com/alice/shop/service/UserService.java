package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;

public interface UserService {
	int reg(String name,String password,String defaultAddress,String defaultPhone,String mail);
    
    boolean login(String name,String password);
    
    boolean setPaying(int userId,int orderid);
    
    public User getInfo( int userId );
    
    public boolean updateInfo(String userId, String name,String  password, String defaultAddress, String defaultPhone, String mail);
    
    public String getPassword(String username);
    
    public String getId(String username);
    
    public int getRole(String username);
    
}
