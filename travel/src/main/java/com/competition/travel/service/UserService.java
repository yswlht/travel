package com.competition.travel.service;

import com.competition.travel.bean.User;

import java.util.List;

public interface UserService {

    boolean deleteUser(int user_id);

    List<User> findAll();

    void sendSimpleMail(String to, String title, String content);

    User findOpen(String weixin_openid);

    boolean wxLogin(String username,String weixin_openid);

    int statisticalUser();
}
