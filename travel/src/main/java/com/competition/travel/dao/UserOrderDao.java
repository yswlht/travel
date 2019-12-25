package com.competition.travel.dao;

import com.competition.travel.bean.UserOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserOrderDao {

    /**创建订单*/
    @Insert("insert into User_order(order_no,product_id,user_id,datetime,tel,remarks,num,name,money) values(#{order_no},#{product_id},#{user_id},#{datetime},#{tel},#{remarks},#{num},#{name},#{money})")
    boolean insertOrder(UserOrder userOrder);

    /**根据微信号查找所有订单*/
    @Select("select a.*,b.name as shop_name from User_order a,Product b where user_id=#{user_id} and a.product_id = b.product_id order by datetime DESC")
    List<UserOrder> findAllOrderById(String user_id);

    /**根据微信号查找未完成订单*/
    @Select("select a.*,b.name as shop_name from User_order a,Product b where user_id=#{user_id} and pin = false and is_pay = false and a.product_id = b.product_id order by datetime DESC")
    List<UserOrder> findNoPayById(String user_id);

    /**根据微信号查找已完成订单*/
    @Select("select a.*,b.name as shop_name from User_order a,Product b where user_id=#{user_id} and pin = true and is_pay = true and a.product_id = b.product_id order by datetime DESC")
    List<UserOrder> findFinOrderById(String user_id);

    /**核销订单*/
    @Update("update User_order set pin = true where order_id = #{order_id}")
    boolean destructionOrder(int order_id, int product_id);

    @Update("update Product set sales = sales + 1 where product_id=#{product_id}")
    boolean addDestructionOrder(int product_id);

    /**根据类型统计订单金额*/
    @Select("select sum(money) as money from User_order")
    double moneyByType();

    /**统计总交易额*/
    @Select("select sum(money) from User_order where is_pay=true")
    double allMoney();

    /**统计每日交易额*/
    @Select("select sum(money) as money from User_order where is_pay=true and DATE(datetime)=#{datetime}")
    double allMoneyByDate(String datetime);

    /**查找订单*/
    @Select("select order_id,product_id,order_no,user_id,name,datetime,tel,remarks,num,pin,delivery_way from User_order where product_id in (select product_id from Product where com_id=#{com_id})")
    List<UserOrder> findAllComOrder(int com_id);

    /**删除订单*/
    @Delete("delete from User_order where order_id=#{order_id}")
    boolean deleteOrder(int order_id);

    /**搜索订单*/
    @Select("select order_id,order_no,product_id,user_id,name,datetime,tel,remarks,num,pin,delivery_way from User_order where order_id like #{order_id} and product_id in (select product_id from Product where com_id=#{com_id})")
    List<UserOrder> searchComOrderById(String order_id, int com_id);

    /**查找商品的图片*/
    @Select("select image as pictures from product where product_id=#{product_id}")
    String findPicture(int product_id);

    /**根据ID查找订单*/
    @Select("select a.*,b.name as shop_name from User_order a,Product b where order_id=#{order_id} and a.product_id = b.product_id")
    UserOrder findOrderByOrderId(int order_id);

    /**根据微信号查找未销单已付款订单*/
    @Select("select a.*,b.name as shop_name from User_order a,Product b where user_id=#{user_id} and is_pay = true and pin = false and a.product_id = b.product_id order by datetime DESC")
    List<UserOrder> findPayById(String user_id);

    /**根据订单号查找订单*/
    @Select("select order_id from User_order where order_no=#{order_no}")
    int findOrderByNo(String order_no);

    /**修改订单状态*/
    @Update("update User_order set is_pay = #{is_pay}, delivery_way = #{delivery_way} where order_no = #{order_no}")
    boolean updateOrder(UserOrder userOrder);

    /**查找订单(超级管理员用)*/
    @Select("select order_id,product_id,order_no,user_id,name,datetime,tel,remarks,num,pin,delivery_way from User_order")
    List<UserOrder> findAllComOrders();
}
