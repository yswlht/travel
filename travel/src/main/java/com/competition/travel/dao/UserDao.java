package com.competition.travel.dao;

import com.competition.travel.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into User(username,weixin_openid) values(#{username},#{weixin_openid})")
    void wxRegister(String username,String weixin_openid);

    /**删除用户*/
    @Delete("delete from User where user_id=#{user_id}")
    void deleteUser(int user_id);

    /**查找所有数据*/
    @Select("select * from User")
    List<User> findAll();

    /**根据微信id查找用户*/
    @Select("select * from User where weixin_openid=#{weixin_openid}")
    User findOpen(String weixin_openid);

    /**统计微信用户数*/
    @Select("select count(*) from User")
    int statisticalUser();
}
