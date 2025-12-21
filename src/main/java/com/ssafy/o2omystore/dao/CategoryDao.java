package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Product;

@Mapper
public interface CategoryDao {
	
	List<Product> selectProductsByCategory(int categoryId);

}
