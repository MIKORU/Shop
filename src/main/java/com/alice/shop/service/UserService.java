package com.alice.shop.service;

import java.util.List;

import com.alice.shop.bean.Order;
import com.alice.shop.bean.User;

public interface UserService {
	int reg(String name,String password,String defaultAddress,String defaultPhone,String mail);
    
    boolean login(String name,String password);
    
    boolean addOrder(int userId,int commodityId,int commodityCount);
    
    String getOrderList(int userId);
    
    boolean delOrder(int userId);
    
    boolean addForm(int userId,String address,String phone,String totalPrice,String pay,String orderlist);
    
    List<Order> getFormList(int userId);
    
    List<Order> getFormAllList();
    
    List<Order> getFormList(int userId,boolean flag);
    
    boolean setPaying(int userId,int orderid);
    
    public User getInfo( int userId );
    
    public boolean updateInfo(String userId, String name,String  password, String defaultAddress, String defaultPhone, String mail);
    
    public String getPassword(String username);
    
    public String getId(String username);
    
    public int getRole(String username);
    
}
