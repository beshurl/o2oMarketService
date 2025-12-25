package com.ssafy.o2omystore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.o2omystore.dto.BestProductResponse;
import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.ProductDetail;
import com.ssafy.o2omystore.dto.ProductLocation;
import com.ssafy.o2omystore.service.ProductLocationService;
import com.ssafy.o2omystore.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product API", description = "상품 목록 조회, 특정 상품 상세 내역 조회 등 상품 관련 API")
@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;
	private final ProductLocationService productLocationService;

	public ProductController(ProductService productService, ProductLocationService productLocationService) {
		this.productService = productService;
		this.productLocationService = productLocationService;
	}

	@Operation(summary = "전체 상품의 목록을 조회한다.")
	@GetMapping
	public List<Product> getAllProducts() {
		return shuffled(productService.getAllProducts());
	}

	@GetMapping("/discount")
	public List<Product> getDiscountProducts(@RequestParam(required = false) String category) {

		if (category == null) {
			return shuffled(productService.getDiscountProducts());
		}
		return shuffled(productService.getDiscountProductsByCategory(category));
	}

	@GetMapping("/deadline")
	public List<Product> getDeadlineProducts(@RequestParam(required = false) String category) {
		if (category == null) {
			return shuffled(productService.getDeadlineProducts());
		}
		return shuffled(productService.getDeadlineProductsByCategory(category));
	}

	@GetMapping("/best")
	public List<BestProductResponse> getBestProducts(@RequestParam(defaultValue = "10") int limit) {
		List<Product> products = productService.getBestProducts(limit);
		List<BestProductResponse> responses = new java.util.ArrayList<>();
		int rank = 1;
		for (Product product : products) {
			BestProductResponse response = new BestProductResponse();
			int productId = product.getProductId() == null ? 0 : product.getProductId();
			response.setId(productId);
			response.setProductId(productId);
			response.setRank(rank++);

			int originalPrice = product.getPrice() == null ? 0 : product.getPrice();
			int finalPrice = product.getFinalPrice() == null ? originalPrice : product.getFinalPrice();
			int discount = product.getDiscountRate() == null ? 0 : product.getDiscountRate();

			response.setName(product.getName());
			response.setOriginalPrice(originalPrice);
			response.setPrice(finalPrice);
			response.setDiscount(discount);
			response.setImage(product.getImage());
			response.setSales(product.getSoldCount() == null ? 0 : product.getSoldCount());
			responses.add(response);
		}
		return responses;
	}

	@Operation(summary = "{productId}에 해당하는 상품의 위치, 리뷰 등 상품 상세 내역을 반환한다.")
	@GetMapping("/{productId}")
	public ProductDetail getProductDetail(@PathVariable int productId) {
	    return productService.getProductDetail(productId);
	}

	@Operation(summary = "상품을 추가합니다.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@Operation(summary = "{productId}에 해당하는 상품을 삭제합니다.")
	@DeleteMapping("/delete/{productId}")
	public void deleteProduct(@PathVariable int productId) {
		productService.deleteProduct(productId);
	}

	@Operation(summary = "상품을 추가합니다. (이미지 파일 업로드 포함)")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void createProductWithImage(
			@RequestParam String name,
			@RequestParam int price,
			@RequestParam String category,
			@RequestParam int stock,
			@RequestParam(required = false) String description,
			@RequestPart(required = false) MultipartFile imageFile,
			@RequestParam(required = false) String image
	) throws IOException {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setCategory(category);
		product.setStock(stock);
		product.setDescription(description);

		String imageUrl = resolveImageUrl(imageFile, image);
		product.setImage(imageUrl);

		productService.createProduct(product);
	}

	@Value("${app.upload.dir:uploads}")
	private String uploadDir;

	private String resolveImageUrl(MultipartFile imageFile, String image) throws IOException {
		String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

		if (imageFile != null && !imageFile.isEmpty()) {
			Path dirPath = Paths.get(uploadDir).toAbsolutePath();
			Files.createDirectories(dirPath);

			String originalName = imageFile.getOriginalFilename();
			String extension = getExtension(originalName);
			String filename = UUID.randomUUID().toString() + extension;
			Path targetPath = dirPath.resolve(filename);
			imageFile.transferTo(targetPath.toFile());

			return baseUrl + "/images/" + filename;
		}

		if (image != null && image.startsWith("/images/")) {
			return baseUrl + image;
		}

		return image;
	}

	private String getExtension(String filename) {
		if (filename == null) {
			return "";
		}
		int index = filename.lastIndexOf('.');
		if (index < 0) {
			return "";
		}
		return filename.substring(index);
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

	private <T> List<T> shuffled(List<T> items) {
		if (items == null || items.size() < 2) {
			return items;
		}
		List<T> copy = new ArrayList<>(items);
		Collections.shuffle(copy);
		return copy;
	}

}
