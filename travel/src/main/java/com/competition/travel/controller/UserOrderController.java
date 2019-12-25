package com.competition.travel.controller;

import com.competition.travel.bean.UserOrder;
import com.competition.travel.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    /**创建订单*/
    @PostMapping(value = "/wx/insertOrder")
    public int insertOrder(@RequestBody UserOrder userOrder){
        userOrder.setDatetime(new Date());
        return userOrderService.insertOrder(userOrder);
    }

    /**根据微信号查找所有订单*/
    @GetMapping(value = "/wx/findAllOrderById")
    public List<UserOrder> findAllOrderById(@RequestParam(value = "user_id") String user_id){
        return userOrderService.findAllOrderById(user_id);
    }

    /**根据微信号查找未完成订单*/
    @GetMapping(value = "/wx/findNoPayById")
    public List<UserOrder> findNoPayById(@RequestParam(value = "user_id") String user_id){
        return userOrderService.findNoPayById(user_id);
    }

    /**根据微信号查找已完成订单*/
    @GetMapping(value = "/wx/findFinOrderById")
    public List<UserOrder> findFinOrderById(@RequestParam(value = "user_id") String user_id){
        return userOrderService.findFinOrderById(user_id);
    }

    /**核销订单*/
    @PutMapping(value = "/wx/destructionOrder")
    public String destructionOrder(@RequestParam(value = "order_id") int order_id,
                                   @RequestParam(value = "product_id") int product_id){
        if(userOrderService.destructionOrder(order_id, product_id)) {
            return "核销成功";
        }else {
            return "核销失败";
        }
    }

    /**根据类型统计订单总金额*/
    @GetMapping(value = "/web/moneyByType")
    public double moneyByType(){
        return userOrderService.moneyByType();
    }

    /**总交易额*/
    @GetMapping(value = "/web/allMoney")
    public double allMoney(){
        return userOrderService.allMoney();
    }

    /**每日总交易额*/
    @GetMapping(value = "/web/allMoneyByDate")
    public double allMoneyByDate(@RequestParam(value = "datetime") Date datetime){
        double money = userOrderService.allMoneyByDate(datetime);
        BigDecimal b = new BigDecimal(money);
        money = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(money);
        return money;
    }

    /**查找企业所有订单*/
    @GetMapping(value = "/web/findAllComOrder")
    public List<UserOrder> findAllComOrder(@RequestParam(value = "com_id") int com_id){
        return userOrderService.findAllComOrder(com_id);
    }

    @GetMapping(value = "/web/findAllComOrders")
    public List<UserOrder> findAllComOrders(){
        return userOrderService.findAllComOrders();
    }

    /**删除订单*/
    @DeleteMapping(value = "/web/deleteOrder")
    public String deleteOrder(@RequestParam(value = "order_id") int order_id){
        if(userOrderService.deleteOrder(order_id)){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    /**搜索订单*/
    @GetMapping(value = "/web/searchComOrderById")
    public List<UserOrder> searchComOrderById(@RequestParam(value = "order_id") int order_id,
                                              @RequestParam(value = "com_id") int com_id){
        return userOrderService.searchComOrderById(order_id, com_id);
    }

    /**根据ID找订单*/
    @GetMapping(value = "/wx/findOrderByOrderId")
    public UserOrder findOrderByOrderId(@RequestParam(value = "order_id") int order_id){
        return userOrderService.findOrderByOrderId(order_id);
    }

    /**根据ID查找未销单已付款订单*/
    @GetMapping(value = "/wx/findPayById")
    public List<UserOrder> findPayById(@RequestParam(value = "user_id") String user_id){
        return userOrderService.findPayById(user_id);
    }

    /**修改订单状态*/
    @PutMapping(value = "/wx/updateOrder")
    public boolean updateOrder(@RequestBody UserOrder userOrder){
        return userOrderService.updateOrder(userOrder);
    }
}
