package com.competition.travel.controller;

import com.competition.travel.bean.Product;
import com.competition.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**根据类型找商品,根据销量降序*/
    @GetMapping(value = "/wx/productBySalesDown")
    public List<Product> productBySalesDown(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "search") String search,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(value = "offset") int offset){
        return productService.productBySalesDown(type, search, limit, offset);
    }

    /**根据类型找商品,根据销量升序*/
    @GetMapping(value = "/wx/productBySalesUp")
    public List<Product> productBySalesUp(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "search") String search,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(value = "offset") int offset){
        return productService.productBySalesUp(type, search, limit, offset);
    }

    /**根据类型找商品,根据价格降序*/
    @GetMapping(value = "/wx/productByPriceDown")
    public List<Product> productByPriceDown(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "search") String search,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(value = "offset") int offset){
        return productService.productByPriceDown(type, search, limit, offset);
    }

    /**根据类型找商品,根据价格升序*/
    @GetMapping(value = "/wx/productByPriceUp")
    public List<Product> productByPriceUp(@RequestParam(value = "type") String type,
                                        @RequestParam(value = "search") String search,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(value = "offset") int offset){
        return productService.productByPriceUp(type, search, limit, offset);
    }

    /**根据ID找商品信息*/
    @GetMapping(value = "/wx/productById")
    public Product productById(@RequestParam(value = "product_id") int product_id){
        return productService.productById(product_id);
    }

    /**根据类型统计商品数*/
    @GetMapping(value = "/web/statisticalProductByType")
    public List<Map> statisticalProductByType(){
        return productService.statisticalProductByType();
    }

    /**查找所有商品*/
    @GetMapping(value = "/web/findAllProduct")
    public List<Product> findAllPorduct(@RequestParam(value = "com_id") int com_id){
        return productService.findAllProduct(com_id);
    }

    /**搜索所有商品*/
    @GetMapping(value = "/web/searchProduct")
    public List<Product> searchProduct(@RequestParam(value = "search") String search){
        return productService.searchProduct(search);
    }

    @DeleteMapping(value = "/web/deleteProduct")
    public boolean deleteProduct(@RequestParam(value = "product_id") int product_id){
        return productService.deleteProduct(product_id);
    }


    /**修改商品信息*/
    @PutMapping(value = "/web/updateProduct")
    public boolean updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    /**增加商品信息*/
    @PostMapping(value = "/web/insertProduct")
    public String insertProduct(@RequestBody Product product){
        if(productService.insertProduct(product)){
            return "插入成功";
        }else{
            return "插入失败";
        }
    }

    /**根据当日时间返回库存
    @GetMapping(value = "/wx/findProductInventory")
    public int findProductInventory(@RequestParam(value = "product_id") int product_id){
        return productService.findProductInventory(product_id);
    }*/

    /**超管查找所有产品*/
    @GetMapping(value = "/web/findAllComProduct")
    public List<Product> findAllComProduct(){
        return productService.findAllComProduct();
    }
}