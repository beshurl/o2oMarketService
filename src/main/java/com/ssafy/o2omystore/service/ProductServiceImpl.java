package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.o2omystore.dao.OrderDao;
import com.ssafy.o2omystore.dao.ProductDao;
import com.ssafy.o2omystore.dto.Comment;
import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.ProductDetail;
import com.ssafy.o2omystore.dto.ProductLocation;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;
	private final ProductLocationService productLocationService;
	private final CommentService commentService;
	private final OrderDao orderDao;

	public ProductServiceImpl(ProductDao productDao, ProductLocationService productLocationService,
			CommentService commetService, OrderDao orderDao) {
		this.productDao = productDao;
		this.productLocationService = productLocationService;
		this.commentService = commetService;
		this.orderDao = orderDao;
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.selectAllProducts();
	}

	@Override
	public Product getProductById(int productId) {
		return productDao.selectProductById(productId);
	}

	@Override
	public ProductDetail getProductDetail(int productId) {
	    Product product = productDao.selectProductById(productId);
	    ProductLocation location =
	            productLocationService.getLocationByProductId(productId);
	    List<Comment> comments =
	            commentService.getCommentsByProductId(productId);

	    return new ProductDetail(product, location, comments);
	}


	@Override
	public List<Product> getDiscountProducts() {
		return productDao.selectDiscountProducts();
	}

	@Override
	public List<Product> getDiscountProductsByCategory(String category) {
		return productDao.selectDiscountProductsByCategory(category);
	}

	@Override
	public List<Product> getDeadlineProducts() {
		return productDao.selectDeadlineProducts();
	}

	@Override
	public List<Product> getDeadlineProductsByCategory(String category) {
		return productDao.selectDeadlineProductsByCategory(category);
	}

	@Override
	public int getStockByProductId(int productId) {
		
		return productDao.selectStockByProductId(productId);
		
	}

	@Override
	public int decreaseStock(int productId, int quantity) {
		return productDao.decreaseStock(productId, quantity);
	}

	@Override
	public int increaseStock(int productId, int quantity) {
		return productDao.increaseStock(productId, quantity);
	}

	@Override
	public int increaseSoldCount(int productId, int quantity) {
		return productDao.increaseSoldCount(productId, quantity);
	}

	@Override
	public int decreaseSoldCount(int productId, int quantity) {
		return productDao.decreaseSoldCount(productId, quantity);
	}

	@Override
	public int getPriceByProductId(int productId) {
		return productDao.selectPriceByProductId(productId);
	}

	@Override
	public void createProduct(Product product) {
		productDao.insertProduct(product);
		if (product.getCategory() != null) {
			productDao.insertProductCategory(product.getProductId(), product.getCategory());
		}
	}

	@Override
	@Transactional
	public void deleteProduct(int productId) {
		int orderCount = orderDao.countOrderDetailsByProductId(productId);
		if (orderCount > 0) {
			throw new IllegalStateException("상품 삭제 전에 관련 주문 내역을 처리해야 합니다.");
		}
		productDao.deleteProductCategoryByProductId(productId);
		productDao.deleteDiscountByProductId(productId);
		productDao.deleteLocationByProductId(productId);
		productDao.deleteProductById(productId);
	}

	@Override
	public List<Product> getBestProducts(int limit) {
		return productDao.selectBestProducts(limit);
	}
}
