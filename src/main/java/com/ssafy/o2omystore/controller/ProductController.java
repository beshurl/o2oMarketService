package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.ProductDetail;
import com.ssafy.o2omystore.dto.ProductLocation;
import com.ssafy.o2omystore.service.ProductLocationService;
import com.ssafy.o2omystore.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;
	private final ProductLocationService productLocationService;

	public ProductController(ProductService productService, ProductLocationService productLocationService) {
		this.productService = productService;
		this.productLocationService = productLocationService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/discount")
	public List<Product> getDiscountProducts(@RequestParam(required = false) String category) {

		if (category == null) {
			return productService.getDiscountProducts();
		}
		return productService.getDiscountProductsByCategory(category);
	}

	@GetMapping("/deadline")
	public List<Product> getDeadlineProducts(@RequestParam(required = false) String category) {
		if (category == null) {
			return productService.getDeadlineProducts();
		}
		return productService.getDeadlineProductsByCategory(category);
	}

	@GetMapping("/{productId}")
	public ProductDetail getProductDetail(@PathVariable int productId) {
	    return productService.getProductDetail(productId);
	}


	@PostMapping
	public void createProduct() {
	}

	@GetMapping("/{productId}/reviews")
	public void getReviews(@PathVariable int productId) {
	}

	@GetMapping("/{productId}/discount")
	public void getDiscount(@PathVariable int productId) {
	}

	@GetMapping("/{productId}/location")
	public void getLocation(@PathVariable int productId) {
	}

	@GetMapping("/test/location/{productId}")
	public ProductLocation testLocation(@PathVariable int productId) {
		return productLocationService.getLocationByProductId(productId);
	}

}
