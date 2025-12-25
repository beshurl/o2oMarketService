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

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getXCode() {
        return xCode;
    }

    public void setXCode(int xCode) {
        this.xCode = xCode;
    }

    public int getYCode() {
        return yCode;
    }

    public void setYCode(int yCode) {
        this.yCode = yCode;
    }
}
