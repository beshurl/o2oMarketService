package com.ssafy.o2omystore.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ssafy.o2omystore.dto.TossPaymentCancelRequest;
import com.ssafy.o2omystore.dto.TossPaymentConfirmRequest;

@Service
public class TossPaymentService {

	private final WebClient webClient;

	@Value("${toss.payments.secret-key}")
	private String secretKey;

	public TossPaymentService(
			@Value("${toss.payments.base-url:https://api.tosspayments.com}") String baseUrl) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.build();
	}

	public Map<String, Object> confirmPayment(TossPaymentConfirmRequest request) {
		validateRequest(request);

		Map<String, Object> payload = new HashMap<>();
		payload.put("paymentKey", request.getPaymentKey());
		payload.put("orderId", request.getOrderId());
		payload.put("amount", request.getAmount());

		return webClient.post()
				.uri("/v1/payments/confirm")
				.header(HttpHeaders.AUTHORIZATION, buildAuthHeader())
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				.block();
	}

	public Map<String, Object> cancelPayment(TossPaymentCancelRequest request) {
		validateCancelRequest(request);

		Map<String, Object> payload = new HashMap<>();
		payload.put("cancelReason", resolveCancelReason(request.getReason()));
		if (request.getAmount() != null && request.getAmount() > 0) {
			payload.put("cancelAmount", request.getAmount());
		}

		return webClient.post()
				.uri("/v1/payments/{paymentKey}/cancel", request.getPaymentKey())
				.header(HttpHeaders.AUTHORIZATION, buildAuthHeader())
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				.block();
	}

	public boolean isApproved(Map<String, Object> response) {
		if (response == null) {
			return false;
		}
		Object status = response.get("status");
		return status != null && "DONE".equalsIgnoreCase(status.toString());
	}

	public boolean isCanceled(Map<String, Object> response) {
		if (response == null) {
			return false;
		}
		Object status = response.get("status");
		return status != null && "CANCELED".equalsIgnoreCase(status.toString());
	}

	private String buildAuthHeader() {
		if (secretKey == null || secretKey.isBlank()) {
			throw new IllegalStateException("toss.payments.secret-key is not configured.");
		}
		String credentials = secretKey + ":";
		String encoded = Base64.getEncoder()
				.encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
		return "Basic " + encoded;
	}

	private void validateCancelRequest(TossPaymentCancelRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request body is required.");
		}
		if (request.getPaymentKey() == null || request.getPaymentKey().isBlank()) {
			throw new IllegalArgumentException("paymentKey is required.");
		}
		if (request.getAmount() != null && request.getAmount() <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero.");
		}
	}

	private String resolveCancelReason(String reason) {
		if (reason == null || reason.isBlank()) {
			return "User requested cancellation.";
		}
		return reason;
	}

	private void validateRequest(TossPaymentConfirmRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request body is required.");
		}
		if (request.getPaymentKey() == null || request.getPaymentKey().isBlank()) {
			throw new IllegalArgumentException("paymentKey is required.");
		}
		if (request.getOrderId() == null || request.getOrderId().isBlank()) {
			throw new IllegalArgumentException("orderId is required.");
		}
		if (request.getAmount() == null || request.getAmount() <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero.");
		}
	}
}
