package com.competition.travel.bean;

import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Destination {
    private Integer destination_id;
    private String region;
    private String title;
    private String content;
    private String type;
    private String editor;
    private Date time;
    private List<String> picture;
    private String pictures;
    private Integer dread;
    private MultipartFile[] file;
    private String path;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getDread() {
        return dread;
    }

    public void setDread(Integer dread) {
        this.dread = dread;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTime() {
        Date time = this.time;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String str1 = format.format(time);
        return str1;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Integer destination_id) {
        this.destination_id = destination_id;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}
