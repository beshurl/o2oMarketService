package com.ssafy.o2omystore.service;

import java.util.List;
import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.ProductDetail;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getDiscountProducts();
    List<Product> getDiscountProductsByCategory(String category);

    List<Product> getDeadlineProducts();
    List<Product> getDeadlineProductsByCategory(String category);
    
    int getStockByProductId(int productId);
    
    int decreaseStock(int productId, int quantity);

    int increaseStock(int productId, int quantity);

    int increaseSoldCount(int productId, int quantity);

    int decreaseSoldCount(int productId, int quantity);
    
    int getPriceByProductId(int productId);

    Product getProductById(int productId);

    ProductDetail getProductDetail(int productId);

    void createProduct(Product product);

    List<Product> getBestProducts(int limit);
}

