package com.ssafy.o2omystore.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.o2omystore.dto.Product;
@Mapper
public interface ProductDao {
    List<Product> selectAllProducts();
    List<Product> selectDiscountProducts();
    List<Product> selectDiscountProductsByCategory(String category);
    
    List<Product> selectDeadlineProducts();
    List<Product> selectDeadlineProductsByCategory(String category);
    int selectStockByProductId(int productId);
    int decreaseStock(int productId, int quantity);
    int increaseStock(@Param("productId") int productId, @Param("quantity") int quantity);
    int increaseSoldCount(@Param("productId") int productId, @Param("quantity") int quantity);
    int decreaseSoldCount(@Param("productId") int productId, @Param("quantity") int quantity);
    int selectPriceByProductId(int productId);
    Product selectProductById(int productId);
    int insertProduct(Product product);
    int insertProductCategory(@Param("productId") int productId,
                              @Param("category") String category);
    List<Product> selectBestProducts(@Param("limit") int limit);
    int deleteProductCategoryByProductId(int productId);
    int deleteDiscountByProductId(int productId);
    int deleteLocationByProductId(int productId);
    int deleteProductById(int productId);

}

