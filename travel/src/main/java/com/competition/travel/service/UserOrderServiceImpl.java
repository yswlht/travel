package com.competition.travel.service;

import com.competition.travel.bean.UserOrder;
import com.competition.travel.dao.UserOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private static volatile int Guid = 100;

    @Autowired
    private UserOrderDao userOrderDao;

    //创建订单
    @Override
    public int insertOrder(UserOrder userOrder) {
        Guid += 1;
        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (Guid > 999) {
            Guid = 100;
        }
        ran = Guid;
        String guid = time + info.substring(2, info.length()) + ran;
        userOrder.setOrder_no(guid);
        userOrderDao.insertOrder(userOrder);
        return userOrderDao.findOrderByNo(guid);
    }

    @Override
    public List<UserOrder> findAllOrderById(String weixin_openid) {
        List<UserOrder> list = userOrderDao.findAllOrderById(weixin_openid);
        list = setPicture(list);
        return list;
    }

    @Override
    public List<UserOrder> findNoPayById(String weixin_openid) {
        List<UserOrder> list = userOrderDao.findNoPayById(weixin_openid);
        list = setPicture(list);
        return list;
    }

    @Override
    public List<UserOrder> findFinOrderById(String weixin_openid) {
        List<UserOrder> list = userOrderDao.findFinOrderById(weixin_openid);
        list = setPicture(list);
        return list;
    }

    @Override
    public boolean destructionOrder(int order_id, int product_id) {
        boolean flag = false;
        try {
            userOrderDao.destructionOrder(order_id, product_id);
            userOrderDao.addDestructionOrder(product_id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public double moneyByType() {
        return userOrderDao.moneyByType();
    }

    @Override
    public double allMoney() {
        try {
            return userOrderDao.allMoney();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public double allMoneyByDate(Date datetime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = format.format(datetime);
        try {
            return userOrderDao.allMoneyByDate(str1);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<UserOrder> findAllComOrder(int com_id) {
        return userOrderDao.findAllComOrder(com_id);
    }

    @Override
    public boolean deleteOrder(int order_id) {
        return userOrderDao.deleteOrder(order_id);
    }

    @Override
    public List<UserOrder> searchComOrderById(int order_id, int com_id) {
        String id = "%" + order_id + "%";
        return userOrderDao.searchComOrderById(id, com_id);
    }

    @Override
    public UserOrder findOrderByOrderId(int order_id) {
        UserOrder userOrder = userOrderDao.findOrderByOrderId(order_id);
        return userOrder;
    }

    @Override
    public List<UserOrder> findPayById(String weixin_openid) {
        List<UserOrder> list = userOrderDao.findPayById(weixin_openid);
        list = setPicture(list);
        return list;
    }

    @Override
    public boolean updateOrder(UserOrder userOrder) {
        return userOrderDao.updateOrder(userOrder);
    }

    @Override
    public List<UserOrder> findAllComOrders() {
        return userOrderDao.findAllComOrders();
    }

    private List<UserOrder> setPicture(List<UserOrder> list){
        for(int i=0; i<list.size(); i++){

        }
        return list;
    }
}
