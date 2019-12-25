package com.competition.travel.dao;

import com.competition.travel.bean.Poi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PoiDao {

    /**搜索景点*/
    @Select("select DISTINCT place_id,name,image FROM Poi where region like #{search} or name like #{search}limit #{limit} OFFSET #{offset}")
    List<Poi> searchPoi(String search, int limit, int offset);

    /**根据ID返回景点*/
    @Select("select DISTINCT place_id,region,name,image,address,time,content,ticket from Poi where place_id=#{place_id}")
    Poi findPoiById(int place_id);

    @Insert("insert into Poi(region, name, address, content, image, time, ticket) values(#{region}, #{name}, #{address}, #{content}, #{image}, #{time}, #{ticket})")
    Boolean insertPoi(Poi poi);
}
