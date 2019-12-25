package com.competition.travel.dao;

import com.competition.travel.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDao {

    /**根据类型找商品,根据销量降序*/
    @Select("select product_id,price as prices,image as pictures,sales,name,com_name from Product a,Com_user b where a.type=#{type} and name like #{search} and a.com_id = b.com_id order by sales DESC limit #{limit} offset #{offset}")
    List<Product> productBySalesDown(String type, String search, int limit, int offset);

    /**根据类型找商品,根据销量升序*/
    @Select("select product_id,price as prices,image as pictures,sales,name,com_name from Product a,Com_user b where a.type=#{type} and name like #{search} and a.com_id = b.com_id order by sales ASC limit #{limit} offset #{offset}")
    List<Product> productBySalesUp(String type, String search, int limit, int offset);

    /**根据类型找商品,根据价格降序*/
    @Select("select product_id,price as prices,image as pictures,sales,name,com_name from Product a,Com_user b where a.type=#{type} and name like #{search} and a.com_id = b.com_id order by price DESC limit #{limit} offset #{offset}")
    List<Product> productByPriceDown(String type, String search, int limit, int offset);

    /**根据类型找商品,根据价格升序*/
    @Select("select product_id,price as prices,image as pictures,sales,name,com_name from Product a,Com_user b where a.type=#{type} and name like #{search} and a.com_id = b.com_id order by price ASC limit #{limit} offset #{offset}")
    List<Product> productByPriceUp(String type, String search, int limit, int offset);

    /**根据ID找商品信息*/
    @Select("select product_id,name,price as prices,sales,introduct,inventory,com_name,a.type from Product a,Com_user b where product_id=#{product_id} and a.com_id = b.com_id")
    Product productById(int product_id);

    /**根据ID找商品的所有规格*/
    @Select("CALL Product_param(#{product_id})")
    List<String> productParam(int product_id);

    /**根据ID找商品的所有图片*/
    @Select("CALL Product_image(#{product_id})")
    List<String> productImage(int product_id);

    /**根据ID找商品的所有价格*/
    @Select("CALL Product_price(#{product_id})")
    List<Double> productPrice(int product_id);

    /**根据ID和日期找商品库存*/
    @Select("CALL Product_inventory(#{datetime},#{product_id})")
    int productInventory(Date datetime, int product_id);

    /**根据商品类型统计数量*/
    @Select("select count(*) as num,type from Product group by type")
    List<Map> statisticalProductByType();

    /**查找所有商品数据*/
    @Select("select product_id,com_name,name,sales,inventory,introduct,a.type from Product a,Com_user b where b.com_id=#{com_id} and a.com_id=b.com_id")
    List<Product> findAllProduct(int com_id);

    /**搜索所有商品*/
    @Select("select product_id,com_name,name,sales,introduct,a.type,inventory from Product a,Com_user b where (name like #{search} or com_name like #{search} or product_id like #{search}) and a.com_id = b.com_id")
    List<Product> searchProduct(String search);

    /**删除商品*/
    @Delete("delete from Product where product_id=#{product_id}")
    boolean deleteProduct(int product_id);

    /**修改商品信息*/
    @Update("update Product set name=#{name},type=#{type},inventory=#{inventory},price=#{prices},param=#{params} where product_id=#{product_id}")
    boolean updateProduct(Product product);

    /**添加商品*/
    @Insert("insert into Product(com_id,name,price,inventory,type,param,introduct,image) values(#{com_id},#{name},#{prices},#{inventory},#{type},#{params},#{introduct},#{path})")
    boolean insertProduct(Product product);

    /**根据当日时间获取库存*/
    @Select("CALL Product_inventory(#{datetime},#{product_id})")
    int findProductInventory(Date datetime, int product_id);

    /**查找所有商品数据(超管用)*/
    @Select("select product_id,name,sales,inventory,introduct,type from Product")
    List<Product> findAllComProduct();
}
