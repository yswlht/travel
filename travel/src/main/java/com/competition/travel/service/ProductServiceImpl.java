package com.competition.travel.service;

import com.competition.travel.bean.Product;
import com.competition.travel.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> productBySalesDown(String type, String search, int limit, int offset) {
        search = "%" + search + "%";
        List<Product> pros = productDao.productBySalesDown(type, search, limit, offset);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public List<Product> productBySalesUp(String type, String search, int limit, int offset) {
        search = "%" + search + "%";
        List<Product> pros = productDao.productBySalesUp(type, search, limit, offset);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public List<Product> productByPriceDown(String type, String search, int limit, int offset) {
        search = "%" + search + "%";
        List<Product> pros = productDao.productByPriceDown(type, search, limit, offset);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public List<Product> productByPriceUp(String type, String search, int limit, int offset) {
        search = "%" + search + "%";
        List<Product> pros = productDao.productByPriceUp(type, search, limit, offset);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public Product productById(int product_id) {
        Product pro = productDao.productById(product_id);
        if(pro != null){
            pro.setParam(segValues(pro.getParams()));
            pro.setPicture(segValues(pro.getPictures()));
            pro.setPrice(segValues(pro.getPrices()));
            /**pro.setInventory(productDao.productInventory(new Date(), product_id));*/
        }
        return pro;
    }

    @Override
    public List<Map> statisticalProductByType() {
        return productDao.statisticalProductByType();
    }

    @Override
    public List<Product> findAllProduct(int com_id) {
        List<Product> pros = productDao.findAllProduct(com_id);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public List<Product> searchProduct(String search) {
        search = "%" + search + "%";
        List<Product> pros = productDao.searchProduct(search);
        pros = setValues(pros);
        return pros;
    }

    @Override
    public boolean deleteProduct(int product_id) {
        boolean flag = false;
        try {
            productDao.deleteProduct(product_id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateProduct(Product product) {
        product.setPrices(product.getPrices().replace(",", "^"));
        product.setParams(product.getParams().replace(",", "^"));
        return productDao.updateProduct(product);
    }

    @Override
    public boolean insertProduct(Product product) {
        product.setPrices(product.getPrices().replace(",", "^"));
        product.setParams(product.getParams().replace(",", "^"));
        return productDao.insertProduct(product);
    }

    @Override
    public int findProductInventory(int product_id) {
        Date datetime = new Date();
        return productDao.findProductInventory(datetime, product_id);
    }

    @Override
    public List<Product> findAllComProduct() {
        List<Product> pros = productDao.findAllComProduct();
        pros = setValues(pros);
        return pros;
    }

    private List<Product> setValues(List<Product> pros){
        for(int i=0; i<pros.size(); i++){
            pros.get(i).setPrice(segValues(pros.get(i).getPrices()));
            pros.get(i).setParam(segValues(pros.get(i).getParams()));
            pros.get(i).setPicture(segValues(pros.get(i).getPictures()));
        }
        return pros;
    }

    public static List<String> segValues(String value){
        List<String> list = new ArrayList<String>();
        if(value != null) {
            String[] values = value.split("\\^");
            for (int i = 0; i < values.length; i++) {
                list.add(values[i]);
            }
        }
        return list;
    }
}
