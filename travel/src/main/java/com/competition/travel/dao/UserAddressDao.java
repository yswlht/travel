package com.competition.travel.dao;

import com.competition.travel.bean.UserAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserAddressDao {

    @Insert("insert into User_address(user_id,name,address,mobile,is_default) values(#{user_id},#{name},#{address},#{mobile},#{is_default})")
    boolean insertAddress(UserAddress userAddress);

    @Delete("delete from User_address where id=#{id}")
    boolean deleteAddress(int id);

    @Select("select * from User_address where user_id=#{user_id}")
    List<UserAddress> getAddress(int user_id);

    @Select("select (select name from Select_region where id=#{province_id}) as province_name,(select name from Select_region where id=#{city_id}) as city_name,(select name from Select_region where id=#{area_id}) as area_name")
    UserAddress getRegion(int province_id, int city_id, int area_id);

    @Select("select * from User_address where user_id=#{user_id} and is_default=true")
    UserAddress getDefaultAddress(int user_id);

    @Select("select *from User_address where id=#{id}")
    UserAddress getOneAddress(int id);

    @Update("update User_address set name=#{name},address=#{address},mobile=#{mobile} where id=#{id}")
    boolean updateAddress(UserAddress userAddress);

    @Update("update User_address set is_default=false where user_id=#{user_id}")
    boolean deleteDefaultAddress(int user_id);

    @Update("update User_address set is_default=true where id=#{id}")
    boolean addDefaultAddress(int id);
}
