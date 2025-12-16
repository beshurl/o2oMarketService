package com.ssafy.o2omystore.service;

import com.ssafy.o2omystore.dto.ProductLocation;

public interface ProductLocationService {

    ProductLocation getLocationByProductId(int productId);
}
