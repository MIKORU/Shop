package com.alice.shop.bean;

public class Cart {
    private String id;

    private String commodityid;

    private Integer commoditycount;

    private String userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid == null ? null : commodityid.trim();
    }

    public Integer getCommoditycount() {
        return commoditycount;
    }

    public void setCommoditycount(Integer commoditycount) {
        this.commoditycount = commoditycount;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}