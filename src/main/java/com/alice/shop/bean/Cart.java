package com.alice.shop.bean;

public class Cart {
    private Integer id;

    private Integer commodityid;

    private Integer commoditycount;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getCommoditycount() {
        return commoditycount;
    }

    public void setCommoditycount(Integer commoditycount) {
        this.commoditycount = commoditycount;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}