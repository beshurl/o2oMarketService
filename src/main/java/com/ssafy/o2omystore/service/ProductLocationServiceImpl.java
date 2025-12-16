package com.ssafy.o2omystore.service;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.ProductLocationDao;
import com.ssafy.o2omystore.dto.ProductLocation;

@Service
public class ProductLocationServiceImpl implements ProductLocationService {

    private final ProductLocationDao productLocationDao;

    public ProductLocationServiceImpl(ProductLocationDao productLocationDao) {
        this.productLocationDao = productLocationDao;
    }

    @Override
    public ProductLocation getLocationByProductId(int productId) {
        return productLocationDao.selectLocationByProductId(productId);
    }
}
