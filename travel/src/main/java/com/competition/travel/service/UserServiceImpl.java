package com.competition.travel.service;

import com.competition.travel.bean.User;
import com.competition.travel.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserDao userDao;

    /**发送邮箱验证码*/
    @Override
    public void sendSimpleMail(String to, String title, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    /**找微信id*/
    @Override
    public User findOpen(String weixin_openid) {
        return userDao.findOpen(weixin_openid);
    }

    /**删除用户*/
    @Override
    public boolean deleteUser(int user_id) {
        boolean flag = false;
        try {
            userDao.deleteUser(user_id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**查找所有用户*/
    @Override
    public List<User> findAll() {
        List<User> list = userDao.findAll();
        for(int i = 0; i < userDao.findAll().size(); i++){
            System.out.println(list.get(i).toString());
        }
        return userDao.findAll();
    }

    /**微信登录*/
    @Override
    public boolean wxLogin(String username, String weixin_openid) {
        boolean flag = false;
        try {
            userDao.wxRegister(username,weixin_openid);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int statisticalUser() {
        return userDao.statisticalUser();
    }
}
