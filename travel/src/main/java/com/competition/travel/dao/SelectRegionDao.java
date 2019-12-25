package com.competition.travel.dao;

import com.competition.travel.bean.SelectRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectRegionDao {

    @Select("select id,pid,name,type,code from Select_region where pid=#{pid}")
    List<SelectRegion> getRegion(int pid);
}
