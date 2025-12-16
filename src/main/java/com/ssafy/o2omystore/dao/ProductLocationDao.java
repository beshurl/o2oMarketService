package com.ssafy.o2omystore.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.ProductLocation;

@Mapper
public interface ProductLocationDao {
    ProductLocation selectLocationByProductId(int productId);
}
