package com.ssafy.o2omystore.dto;

public class ProductLocation {

    private int productId;
    private String zone;
    private int xCode;
    private int yCode;

    public ProductLocation() {}

    public ProductLocation(int productId, String zone, int xCode, int yCode) {
        this.productId = productId;
        this.zone = zone;
        this.xCode = xCode;
        this.yCode = yCode;
    }

    public int getProductId() {
        return productId;
    }

    public String getZone() {
        return zone;
    }

    public int getXCode() {
        return xCode;
    }

    public int getYCode() {
        return yCode;
    }
}
