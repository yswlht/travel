package com.competition.travel.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserOrder {
    private Integer order_id;
    private String order_no;
    private Integer product_id;
    private String user_id;
    private String name;
    private String shop_name;
    private String tel;
    private String remarks = "";
    private Integer num;
    private String delivery_way = "";
    private boolean is_pay;
    private double money;
    private Date datetime;
    private Boolean pin;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDelivery_way() {
        return delivery_way;
    }

    public void setDelivery_way(String delivery_way) {
        this.delivery_way = delivery_way;
    }

    public boolean isIs_pay() {
        return is_pay;
    }

    public void setIs_pay(boolean is_pay) {
        this.is_pay = is_pay;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getDatetime() {
        Date time = this.datetime;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String str1 = format.format(time);
        return str1;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Boolean getPin() {
        return pin;
    }

    public void setPin(Boolean pin) {
        this.pin = pin;
    }
}
