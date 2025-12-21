package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.CategoryDao;
import com.ssafy.o2omystore.dto.Product;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao categoryDao;
	
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		return categoryDao.selectProductsByCategory(categoryId);
	}

}
