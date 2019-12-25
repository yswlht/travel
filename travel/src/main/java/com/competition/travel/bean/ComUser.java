package com.competition.travel.bean;

public class ComUser {
    private String username;
    private String com_name;
    private String password;
    private String tel;
    private String place;
    private Integer com_id;
    private Boolean audit = false;
    private Boolean type = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    @Override
    public String toString() {
        return "ComUser{" +
                "username='" + username + '\'' +
                ", com_name='" + com_name + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", place='" + place + '\'' +
                ", com_id=" + com_id +
                ", audit=" + audit +
                ", type=" + type +
                '}';
    }
}
