package com.alice.shop.bean;

public class Order {
    private Integer id;

    private Integer userid;

    private String address;

    private String phone;

    private Integer totalprice;

    private Integer pay;

    private String orderlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public String getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(String orderlist) {
        this.orderlist = orderlist == null ? null : orderlist.trim();
    }
}