package com.alice.shop.bean;

import java.util.Date;

public class User {
    private String id;

    private String name;

    private String password;

    private String address;

    private String phone;

    private String mail;

    private Integer role;

    private Integer money;

    private Date regtime;
    
    

    public User() {
		// TODO Auto-generated constructor stub
	}
    public User(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.mail = user.getMail();
    	this.password = user.getPassword();
    	this.phone = user.getPhone();
    	this.address = user.getAddress();
    	this.money = user.getMoney();
    	this.role = user.getRole();
    	this.regtime = user.getRegtime();
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", address=" + address + ", phone="
				+ phone + ", mail=" + mail + ", role=" + role + ", money=" + money + ", regtime=" + regtime + "]";
	}
    
    
}