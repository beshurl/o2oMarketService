package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category API", description = "상품 카테고리 조회, 추가 API")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	@Operation(summary = "전체 카테고리 목록을 반환합니다.")
    @GetMapping
    public void getCategories() {
    }

    @Operation(summary = "{categoryId}에 해당하는 상품 목록을 반환합니다.")
    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
    	
    	return categoryService.getProductsByCategory(categoryId);
    	
    }

    @Operation(summary = "카테고리에 상품을 추가합니다.")
    @PostMapping("/products/{productId}")
    public void mapProductCategory(@PathVariable int productId) {
    }
}
