package com.ssafy.o2omystore.chat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.o2omystore.chat.dto.ChatResponse;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;
import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.ProductLocation;
import com.ssafy.o2omystore.service.OrderService;
import com.ssafy.o2omystore.service.ProductLocationService;
import com.ssafy.o2omystore.service.ProductService;

@Service
public class ChatServiceImpl implements ChatService {

	@Value("${gms.api.key}")
	private String gmsApiKey;

	private final ProductService productService;
	private final ProductLocationService productLocationService;
	private final OrderService orderService;

	private final WebClient webClient;

	public ChatServiceImpl(ProductService productService, ProductLocationService productLocationService,
			OrderService orderService) {
		this.productService = productService;
		this.productLocationService = productLocationService;
		this.orderService = orderService;

		this.webClient = WebClient.builder().baseUrl("https://gms.ssafy.io/gmsapi")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader("anthropic-version", "2023-06-01").build();
	}

	@Override
	public ChatResponse ask(String userId, String message) {

		String context;
		RecommendationResult recommendation = null;

		if (message.contains("할인")) {
			context = buildDiscountContext();
		} else if (message.contains("위치")) {
			context = buildLocationContext(message);
		} else if (message.contains("주문") || message.contains("배송")) {
			if (isOverallDeliveryStatusQuestion(message)) {
				context = buildDeliverySummaryContext(userId);
			} else {
				context = buildOrderContext(userId);
			}
		}else if (
			    message.contains("추천") ||
			    message.contains("저녁") ||
			    message.contains("메뉴")
			) {
			    recommendation = buildRecommendationContext();
			    context = recommendation.context;
			}
		else {
			context = "Smart O2O Mart에 대한 일반 질문입니다.";
		}

		String answer = callClaude(context, message);
		if (recommendation != null) {
			return new ChatResponse(answer, recommendation.products);
		}
		return new ChatResponse(answer);
	}

	// ==============================
	// 할인 상품 Context
	// ==============================
	private String buildDiscountContext() {
		List<Product> products = productService.getDiscountProducts();

		if (products == null || products.isEmpty()) {
			return "현재 할인 중인 상품이 없습니다.";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("다음은 Smart O2O Mart의 할인 상품 목록입니다.\n\n");

		for (Product p : products) {
			sb.append("- 상품명: ").append(p.getName()).append("\n").append("  할인율: ").append(p.getDiscountRate())
					.append("%\n").append("  가격: ").append(p.getPrice()).append("원\n\n");
		}

		return sb.toString();
	}

	// ==============================
	// 상품 위치 Context
	// ==============================
	private String buildLocationContext(String message) {

		Product product = findProductFromMessage(message);

		if (product == null) {
			return """
					요청하신 상품을 찾지 못했습니다.
					상품명을 정확히 입력해 주세요.
					예) "삼겹살 위치 알려줘"
					""";
		}

		ProductLocation location = productLocationService.getLocationByProductId(product.getProductId());

		if (location == null) {
			return "해당 상품의 매장 위치 정보가 없습니다.";
		}

		return """
				상품 위치 안내입니다.

				상품명: %s
				매장 위치: %s
				진열 좌표: (%d, %d)

				해당 위치로 이동하시면 상품을 찾을 수 있습니다.
				""".formatted(product.getName(), location.getZone(), location.getXCode(), location.getYCode());
	}

	// ==============================
	// 주문 / 배송 Context
	// ==============================
	private String buildOrderContext(String userId) {

		List<Order> orders = orderService.getOrdersByUserId(userId);

		if (orders == null || orders.isEmpty()) {
			return "현재 사용자의 주문 내역이 없습니다.";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("사용자의 주문 정보입니다.\n\n");

		for (Order o : orders) {
			sb.append("- 주문번호: ").append(o.getOrderId()).append("\n").append("  상태: ").append(o.getStatus())
					.append("\n").append("  총 금액: ").append(o.getTotalPrice()).append("원\n\n");
		}

		return sb.toString();
	}

	// ==============================

	private String buildDeliverySummaryContext(String userId) {

		List<Order> orders = orderService.getOrdersByUserId(userId);

		if (orders == null || orders.isEmpty()) {
			return "현재 주문 내역이 없습니다.";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("최근 주문 3건 배송 현황입니다.\n\n");

		int index = 1;
		for (Order order : orders) {
			if (index > 3) {
				break;
			}

			String productLabel = buildOrderProductLabel(order);
			String status = (order.getStatus() == null || order.getStatus().isBlank()) ? "확인 필요" : order.getStatus();

			sb.append(index).append(") 주문번호: ").append(order.getOrderId()).append("\n")
				.append("  상품: ").append(productLabel).append("\n")
				.append("  배송 상태: ").append(status).append("\n\n");

			index++;
		}

		sb.append("원하시는 주문이 있으면 주문번호로 알려주세요.");
		return sb.toString();
	}

	private String buildOrderProductLabel(Order order) {
		List<OrderDetail> details = order.getOrderDetails();
		if (details == null || details.isEmpty()) {
			return "상품 정보 없음";
		}

		OrderDetail first = details.get(0);
		for (OrderDetail detail : details) {
			if (detail.getOrderItemId() < first.getOrderItemId()) {
				first = detail;
			}
		}

		Product product = productService.getProductById(first.getProductId());
		String productName = product == null ? null : product.getName();
		if (productName == null || productName.isBlank()) {
			productName = "상품 정보 없음";
		}

		int additionalCount = Math.max(details.size() - 1, 0);
		if (additionalCount > 0) {
			return productName + " 외 " + additionalCount + "개";
		}
		return productName;
	}

	private boolean isOverallDeliveryStatusQuestion(String message) {
		if (message == null) {
			return false;
		}
		String normalized = message.replace(" ", "");
		return normalized.contains("배송현황")
			|| normalized.contains("배송상태")
			|| normalized.contains("배송상황")
			|| normalized.contains("전체배송");
	}
	// Claude Sonnet 4 호출
	// ==============================
	private String callClaude(String context, String question) {

		Map<String, Object> body = Map.of("model", "claude-sonnet-4-20250514", "max_tokens", 1024, "system", """
				너는 Smart O2O Mart의 AI 쇼핑 도우미 정준용이다.

				규칙:
				1. 반드시 제공된 마트 데이터(context)만 사용해 답변한다.
				2. 데이터에 없는 정보는 절대 추측하지 말고 "해당 정보는 확인할 수 없습니다"라고 말한다.
				3. 가격, 할인율, 상품명은 정확히 그대로 사용한다.
				4. 사용자가 물어본 질문에만 집중해서 간결하고 친절하게 한국어로 답변한다.
				5. 마트 직원처럼 말하되 과장하지 않는다.
								""", "messages",
				List.of(Map.of("role", "user", "content", context), Map.of("role", "user", "content", question)));

		Map response = webClient.post().uri("/api.anthropic.com/v1/messages").header("x-api-key", gmsApiKey)
				.bodyValue(body).retrieve().bodyToMono(Map.class).block();

		List content = (List) response.get("content");
		Map text = (Map) content.get(0);

		return (String) text.get("text");
	}

	private Product findProductFromMessage(String message) {

		// 전체 상품 목록 조회
		List<Product> products = productService.getAllProducts();

		if (products == null || products.isEmpty()) {
			return null;
		}

		// 단순 포함 문자열 매칭 (1차 구현으로 충분)
		for (Product p : products) {
			if (message.contains(p.getName())) {
				return p;
			}
		}

		return null;
	}
	private RecommendationResult buildRecommendationContext() {
	    List<Product> deadlineProducts = safeList(productService.getDeadlineProducts());
	    List<Product> discountProducts = safeList(productService.getDiscountProducts());
	    List<Product> allProducts = safeList(productService.getAllProducts());

	    Set<Integer> included = new HashSet<>();
	    List<Product> prioritizedDeadline = filterNewProducts(deadlineProducts, included);
	    List<Product> prioritizedDiscount = filterNewProducts(discountProducts, included);
	    List<Product> regularProducts = filterNewProducts(allProducts, included);

	    List<Product> recommended = new ArrayList<>();
	    recommended.addAll(prioritizedDeadline);
	    recommended.addAll(prioritizedDiscount);
	    recommended.addAll(regularProducts);

	    if (recommended.isEmpty()) {
	        return new RecommendationResult("No products are available right now.", recommended);
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append("Smart O2O Mart product recommendations.\n\n");

	    appendProductSection(sb, "Deadline products", prioritizedDeadline);
	    appendProductSection(sb, "Discount products", prioritizedDiscount);
	    appendProductSection(sb, "Other products", regularProducts);

	    sb.append("\nPick anything you like, and I can help you choose.");

	    return new RecommendationResult(sb.toString(), recommended);
	}

	private List<Product> filterNewProducts(List<Product> products, Set<Integer> included) {
	    List<Product> results = new ArrayList<>();
	    for (Product product : products) {
	        if (product == null || product.getProductId() == null) {
	            continue;
	        }
	        if (included.add(product.getProductId())) {
	            results.add(product);
	        }
	    }
	    return results;
	}

	private List<Product> safeList(List<Product> products) {
	    return products == null ? List.of() : products;
	}

	private void appendProductSection(StringBuilder sb, String title, List<Product> products) {
	    if (products.isEmpty()) {
	        return;
	    }
	    sb.append(title).append("\n");
	    for (Product product : products) {
	        sb.append("- ").append(product.getName());
	        if (product.getDiscountRate() != null) {
	            sb.append(" (discount ").append(product.getDiscountRate()).append("%)");
	        }
	        if (product.getPrice() != null) {
	            sb.append(" price ").append(product.getPrice());
	        }
	        if (product.getFinalPrice() != null) {
	            sb.append(" final ").append(product.getFinalPrice());
	        }
	        sb.append("\n");
	    }
	    sb.append("\n");
	}

	private static class RecommendationResult {
	    private final String context;
	    private final List<Product> products;

	    private RecommendationResult(String context, List<Product> products) {
	        this.context = context;
	        this.products = products;
	    }
	}

}
