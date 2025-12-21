package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.CategoryDao;
import com.ssafy.o2omystore.dao.ProductDao;
import com.ssafy.o2omystore.dto.Product;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao categoryDao;
	private final ProductService productService;
	
	public CategoryServiceImpl(CategoryDao categoryDao, ProductService productService) {
		this.categoryDao = categoryDao;
		this.productService = productService;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		
		if (categoryId == 0) {
			return productService.getAllProducts();
		}
		
		return categoryDao.selectProductsByCategory(categoryId);
	}

}
