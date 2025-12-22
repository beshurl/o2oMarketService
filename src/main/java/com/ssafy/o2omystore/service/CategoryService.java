package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.Product;

public interface CategoryService {
	
	List<Product> getProductsByCategory(int categoryId);

	
}
