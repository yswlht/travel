package com.competition.travel.dao;

import com.competition.travel.bean.Destination;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DestinationDao {

    /**查找根据类型查找攻略*/
    @Select("select destination_id,content,title,time,editor,dread,region,type from Destination where type=#{type}")
    List<Destination> findDestinationByType(String type);

    /**查找根据地域查找攻略*/
    @Select("select destination_id,content,title,time,editor,dread,region,type from Destination where region=#{region}")
    List<Destination> findDestinationByRegion(String region);

    /**查找所有地域*/
    @Select("select destination_id,region,type,title,content,time,editor,dread from Destination")
    List<Destination> findAllDestination();

    /**根据id查找攻略*/
    @Select("select destination_id,title,time,editor,dread,content,type,region,image as pictures from Destination where destination_id=#{destination_id}")
    Destination findOneDestination(int destination_id);

    /**模糊查询*/
    @Select("select destination_id,title,time,editor,dread,type from Destination where title like #{title} or region like #{title} order by dread DESC limit #{limit} offset #{offset}")
    List<Destination> fuzzySearch(String title, int limit, int offset);

    /**分页搜索*/
    @Select("select destination_id,title,time,editor,dread,image as pictures from Destination where region=#{region} and type=#{type} order by destination_id limit #{limit} offset #{offset}")
    List<Destination> findDestination(String region, String type, int limit, int offset);

    /**根据ID查找攻略所有图片
    @Select("CALL Destination_image(#{destination_id})")
    List<String> destinationImage(int destination_id);*/

    /**修改数据*/
    @Update("update Destination set content=#{content},time=#{time},title=#{title} where destination_id=#{destination_id}")
    boolean updateDestination(Destination destination);

    /**删除数据*/
    @Delete("delete from Destination where destination_id=#{destination_id}")
    boolean deleteDestination(int destination_id);

    /**浏览量增加*/
    @Update("update Destination set dread = dread + 1 where destination_id=#{destination_id}")
    boolean addDread(int destination_id);

    /**搜索旅游直播*/
    @Select("select destination_id,title,dread,editor,time from Destination where (region like #{search} or title like #{search}) and type = #{type} order by time DESC limit #{limit} offset #{offset}")
    List<Destination> findPhotos(String search, String type, int limit, int offset);

    /**旅游直播详情*/
    @Select("select destination_id,title,content,time,editor,dread,image as pictures from Destination where destination_id=#{destination_id}")
    Destination findOnePhotos(int destination_id);

    /**搜索攻略*/
    @Select("select destination_id,region,type,title,content,time,editor,dread from Destination where editor like #{search} or title like #{search} or type like #{search}")
    List<Destination> searchDestination(String search);

    /**增加攻略*/
    @Insert("insert into Destination(region,type,title,image,content,time,editor) values(#{region},#{type},#{title},#{path},#{content},#{time},#{editor})")
    boolean insertDestination(Destination destination);

    @Select("select destination_id,title,content,time,editor,dread,region,type from Destination where region=#{region} and type=#{type}")
    List<Destination> findDestinationByTyRe(String region, String type);

    @Select("select destination_id,title,content,time,editor,dread,region,type from Destination where editor=#{editor} and type='游记'")
    List<Destination> findMyDestination(String editor);
}
