package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public void getCategories() {
    }

    @GetMapping("/{categoryId}/products")
    public void getProductsByCategory(@PathVariable int categoryId) {
    }

    @PostMapping("/products/{productId}")
    public void mapProductCategory(@PathVariable int productId) {
    }
}
