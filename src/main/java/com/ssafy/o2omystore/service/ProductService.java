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

    Product getProductById(int productId);

    ProductDetail getProductDetail(int productId);
}

