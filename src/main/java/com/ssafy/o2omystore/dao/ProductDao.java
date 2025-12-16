package com.ssafy.o2omystore.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Product;
@Mapper
public interface ProductDao {
    List<Product> selectAllProducts();
    List<Product> selectDiscountProducts();
    List<Product> selectDiscountProductsByCategory(String category);
    
    List<Product> selectDeadlineProducts();
    List<Product> selectDeadlineProductsByCategory(String category);
    Product selectProductById(int productId);

}

