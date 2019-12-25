package com.competition.travel.dao;

import com.competition.travel.bean.ComUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ComUserDao")
public interface ComUserDao {

    /**注册数据*/
    @Insert("insert into Com_user(username,password,place,tel,com_name) values(#{username},#{password},#{place},#{tel},#{com_name})")
    void comRegister(ComUser comUser);

    /**根据用户名删除用户*/
    @Delete("delete from Com_user where username=#{username}")
    void comDelete(String username);

    /**审核商户通过*/
    @Update("update Com_user set audit=true where username=#{username}")
    void comAudit(String username);

    /**根据用户名查找*/
    @Select("select * from Com_user where username=#{username}")
    ComUser findByUsername(String username);

    /**查找所有用户*/
    @Select("select * from Com_user where type=false")
    List<ComUser> findAll();

    /**修改用户信息*/
    @Update("update Com_user set password=#{password},place=#{place},tel=#{tel},com_name=#{com_name} where username=#{username}")
    void updateInformation(ComUser comUser);

    /**统计企业用户数*/
    @Select("select count(*) from Com_user where type = false")
    int statisticalCom();

    @Insert("insert into Com_pin(com_id,username,password) values(#{com_id},#{username},#{password})")
    boolean insertComPin(int com_id, String username, String password);
}
