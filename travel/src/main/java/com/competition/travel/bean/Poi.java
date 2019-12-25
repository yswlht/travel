package com.competition.travel.bean;

import java.util.List;

public class Poi {
    private Integer place_id;
    private String region;
    private String name;
    private String image;
    private String address;
    private String time;
    private String content;
    private float ticket;
    private List<String> imagescroll;

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    public List<String> getImagescroll() {
        return imagescroll;
    }

    public void setImagescroll(List<String> imagescroll) {
        this.imagescroll = imagescroll;
    }
}
