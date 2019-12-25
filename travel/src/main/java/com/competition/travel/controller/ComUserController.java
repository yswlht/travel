package com.competition.travel.controller;

import com.competition.travel.bean.ComUser;
import com.competition.travel.service.ComUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComUserController {

    @Autowired
    private ComUserService comUserService;

    /**商户注册*/
    @PostMapping(value = "/web/comRegister")
    public String comRegister(@RequestBody ComUser comUser){
        ComUser user = comUserService.findByUsername(comUser.getUsername());
        if(user != null){
            return "用户已存在";
        }else{
            if(comUserService.comRegister(comUser)) {
                return "注册成功";
            }
            else {
                return "注册失败";
            }
        }
    }

    /**后台管理登录*/
    @GetMapping(value = "/web/comLogin")
    public List<String> comLogin(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password){
        ComUser user = comUserService.findByUsername(username);
        List<String> list = new ArrayList<>();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(user == null){
            list.add("用户名不存在");
        }else if(!user.getPassword().equals(password)){
            list.add("密码错误");
        }else if(!user.getAudit()){
            list.add("未审核");
        }else if(user.getType()){
            list.add("admin");
        }else{
            list.add("editor");
        }
        return list;
    }

    @GetMapping(value = "/web/comPermission")
    public boolean comPermission(@RequestParam(value = "token") String token){
        if(token.equals("admin") || token.equals("editor")){
            return true;
        }else {
            return false;
        }
    }

    /**删除商户*/
    @DeleteMapping(value = "/web/comDelete")
    public Boolean comDelete(@RequestParam(value = "username") String username){
        return comUserService.comDelete(username);
    }

    /**审核商户*/
    @PutMapping(value = "/web/comAudit")
    public Boolean comAudit(@RequestParam(value = "username") String username){
        return comUserService.comAudit(username);
    }

    /**查找所有用户*/
    @GetMapping(value = "/web/findAll")
    public List<ComUser> findAll(){
        return comUserService.findAll();
    }

    /**修改用户信息*/
    @PutMapping(value = "/web/updateInformation")
    public String updateInformation(@RequestBody ComUser comUser){
        ComUser judge = comUserService.findByUsername(comUser.getUsername());
        if(judge != null){
            if(comUserService.updateInformation(comUser)){
                return "修改成功";
            }else {
                return "修改失败";
            }
        }else {
            return "用户名不存在";
        }
    }

    /**统计企业用户数*/
    @GetMapping(value = "/web/statisticalCom")
    public int statisticalCom(){
        return comUserService.statisticalCom();
    }
}
