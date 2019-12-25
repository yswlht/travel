package com.competition.travel.service;

import com.competition.travel.bean.UserOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserOrderService {

    int insertOrder(UserOrder userOrder);

    List<UserOrder> findAllOrderById(String weixin_openid);

    List<UserOrder> findNoPayById(String weixin_openid);

    List<UserOrder> findFinOrderById(String weixin_openid);

    boolean destructionOrder(int order_id, int product_id);

    double moneyByType();

    double allMoney();

    double allMoneyByDate(Date datetime);

    List<UserOrder> findAllComOrder(int com_id);

    boolean deleteOrder(int order_id);

    List<UserOrder> searchComOrderById(int order_id, int com_id);

    UserOrder findOrderByOrderId(int order_id);

    List<UserOrder> findPayById(String weixin_openid);

    boolean updateOrder(UserOrder userOrder);

    List<UserOrder> findAllComOrders();
}
