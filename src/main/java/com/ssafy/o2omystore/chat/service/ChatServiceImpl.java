package com.ssafy.o2omystore.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.o2omystore.dto.Order;
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
	public String ask(String userId, String message) {

		String context;

		if (message.contains("할인")) {
			context = buildDiscountContext();
		} else if (message.contains("위치")) {
			context = buildLocationContext(message);
		} else if (message.contains("주문") || message.contains("배송")) {
			context = buildOrderContext(userId);
		}else if (
			    message.contains("추천") ||
			    message.contains("저녁") ||
			    message.contains("메뉴")
			) {
			    context = buildRecommendationContext();
			}
		else {
			context = "Smart O2O Mart에 대한 일반 질문입니다.";
		}

		return callClaude(context, message);
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
	private String buildRecommendationContext() {
	    List<Product> products = productService.getAllProducts();

	    if (products.isEmpty()) {
	        return "현재 판매 중인 상품이 없습니다.";
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append("Smart O2O Mart에서 판매 중인 상품입니다:\n\n");

	    for (Product p : products) {
	        sb.append("- ").append(p.getName())
	          .append(" (").append(p.getPrice()).append("원)\n");
	    }

	    sb.append("\n이 중에서 조합해서 식사를 추천해 주세요.");

	    return sb.toString();
	}


}
