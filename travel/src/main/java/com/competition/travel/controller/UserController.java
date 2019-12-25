package com.competition.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.competition.travel.bean.User;
import com.competition.travel.service.UserService;
import com.competition.travel.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**获取邮箱验证码*/
    @GetMapping(value = "/wx/getCheckCode")
    public String getCheckCode(@RequestParam(value = "email") String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的邮箱验证码为:" + checkCode;
        try {
            userService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "邮箱不存在";
        }
        return DigestUtils.md5DigestAsHex(checkCode.getBytes());
    }

    /**删除用户*/
    @DeleteMapping(value = "/wx/deleteUser")
    public boolean delete(@RequestParam(value = "user_id") int user_id) {
        return userService.deleteUser(user_id);
    }

    /**测试*/
    @GetMapping(value = "/wx/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping(value = "/wx/wxlogin")
    public Map<String, String> wxLogin(@RequestParam(value = "code") String code,
                          @RequestParam(value = "nickName") String nickName ){
        Map<String, String> param = new HashMap<>();
        Map<String, String> returns = new HashMap<>();
        param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();

        Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", open_id);
        User users = userService.findOpen(open_id);
        if(users != null){
            returns.put("user_id", users.getUser_id().toString());
            returns.put("weixin_openid", users.getWeixin_openid());
        }else{
            userService.wxLogin(nickName,open_id);
            users = userService.findOpen(open_id);
            returns.put("user_id", users.getUser_id().toString());
            returns.put("weixin_openid", users.getWeixin_openid());
        }
        return returns;
    }

    @GetMapping(value = "/web/statisticalUser")
    public int statisticalUser(){
        return userService.statisticalUser();
    }
}
