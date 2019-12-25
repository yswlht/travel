package com.competition.travel.service;

import com.competition.travel.bean.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> productBySalesDown(String type, String search, int limit, int offset);

    List<Product> productBySalesUp(String type, String search, int limit, int offset);

    List<Product> productByPriceDown(String type, String search, int limit, int offset);

    List<Product> productByPriceUp(String type, String search, int limit, int offset);

    Product productById(int product_id);

    List<Map> statisticalProductByType();

    List<Product> findAllProduct(int com_id);

    List<Product> searchProduct(String search);

    boolean deleteProduct(int product_id);

    boolean updateProduct(Product product);

    boolean insertProduct(Product product);

    int findProductInventory(int product_id);

    List<Product> findAllComProduct();
}
