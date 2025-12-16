package com.ssafy.o2omystore.dto;

import java.util.List;

public class ProductDetail {

    private Product product;                 // 기본 상품 정보
    private ProductLocation location;         // 위치
    private List<Comment> comments;           // 댓글 목록

    public ProductDetail() {}

    public ProductDetail(Product product,
                         ProductLocation location,
                         List<Comment> comments) {
        this.product = product;
        this.location = location;
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductLocation getLocation() {
        return location;
    }

    public void setLocation(ProductLocation location) {
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
