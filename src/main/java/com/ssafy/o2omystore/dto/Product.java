package com.ssafy.o2omystore.dto;

public class Product {

    private Integer productId;
    private String name;
    private Integer price;
    private Integer finalPrice;
    private String image;
    private String category;

    private Integer discountRate;
    private String discountType;

    public Product() {
    }

    public Product(
            Integer productId,
            String name,
            Integer price,
            Integer finalPrice,
            String image,
            String category,
            Integer discountRate,
            String discountType
    ) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.finalPrice = finalPrice;
        this.image = image;
        this.category = category;
        this.discountRate = discountRate;
        this.discountType = discountType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId
                + ", name=" + name
                + ", price=" + price
                + ", finalPrice=" + finalPrice
                + ", image=" + image
                + ", category=" + category
                + ", discountRate=" + discountRate
                + ", discountType=" + discountType + "]";
    }
}
