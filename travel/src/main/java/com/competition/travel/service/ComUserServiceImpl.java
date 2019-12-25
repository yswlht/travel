package com.competition.travel.service;

import com.competition.travel.bean.ComUser;
import com.competition.travel.dao.ComUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class ComUserServiceImpl implements ComUserService {

    @Autowired
    private ComUserDao comUserDao;

    @Override
    public boolean comRegister(ComUser comUser) {
        boolean flag = false;
        try {
            comUser.setPassword(DigestUtils.md5DigestAsHex(comUser.getPassword().getBytes()));
            comUserDao.comRegister(comUser);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean comDelete(String username) {
        boolean flag = false;
        try {
            comUserDao.comDelete(username);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean comAudit(String username) {
        boolean flag = false;
        ComUser com = comUserDao.findByUsername(username);
        if(com != null && !com.getAudit()) {
            comUserDao.comAudit(username);
            flag = true;
        }
        return flag;
    }

    @Override
    public ComUser findByUsername(String username) {
        return comUserDao.findByUsername(username);
    }

    @Override
    public List<ComUser> findAll() {
        return comUserDao.findAll();
    }

    @Override
    public boolean updateInformation(ComUser comUser) {
        boolean flag = false;
        try {
            comUser.setPassword(DigestUtils.md5DigestAsHex(comUser.getPassword().getBytes()));
            comUserDao.updateInformation(comUser);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int statisticalCom() {
        return comUserDao.statisticalCom();
    }

    //产生随机字符串
    public char[] charArray(){
        int i = 1234567890;
        String s ="qwertyuiopasdfghjklzxcvbnm";
        String S=s.toUpperCase();
        String word=s+S+i;
        char[] c=word.toCharArray();

        return c;
    }
}
