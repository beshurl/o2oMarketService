package com.ssafy.o2omystore.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.o2omystore.dto.TossPaymentCancelRequest;
import com.ssafy.o2omystore.dto.TossPaymentConfirmRequest;
import com.ssafy.o2omystore.service.OrderService;
import com.ssafy.o2omystore.service.TossPaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Payment API", description = "Toss payment confirm/cancel API")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private final TossPaymentService tossPaymentService;
	private final OrderService orderService;

	public PaymentController(TossPaymentService tossPaymentService, OrderService orderService) {
		this.tossPaymentService = tossPaymentService;
		this.orderService = orderService;
	}

	@Operation(summary = "Confirm Toss payment and mark order as paid.")
	@PostMapping("/confirm")
	public Map<String, Object> confirmPayment(@RequestBody TossPaymentConfirmRequest request) {
		Map<String, Object> response = tossPaymentService.confirmPayment(request);

		if (tossPaymentService.isApproved(response)) {
			int orderId = parseOrderId(request.getOrderId());
			orderService.updateOrderStatus(orderId, "PAYMENT_COMPLETED");
		}

		return response;
	}

	@Operation(summary = "Cancel Toss payment and mark order as cancelled.")
	@PostMapping("/cancel")
	public Map<String, Object> cancelPayment(@RequestBody TossPaymentCancelRequest request) {
		Map<String, Object> response = tossPaymentService.cancelPayment(request);

		if (tossPaymentService.isCanceled(response)) {
			Integer orderId = parseOptionalOrderId(request.getOrderId());
			if (orderId != null) {
				orderService.updateOrderStatus(orderId, "CANCELLED");
			}
		}

		return response;
	}

	@Operation(summary = "Refund Toss payment and mark order as returned.")
	@PostMapping("/refund")
	public Map<String, Object> refundPayment(@RequestBody TossPaymentCancelRequest request) {
		Map<String, Object> response = tossPaymentService.cancelPayment(request);

		if (tossPaymentService.isCanceled(response)) {
			Integer orderId = parseOptionalOrderId(request.getOrderId());
			if (orderId != null) {
				orderService.updateOrderStatus(orderId, "RETURNED");
			}
		}

		return response;
	}

	private int parseOrderId(String orderId) {
		if (orderId == null || orderId.isBlank()) {
			throw new IllegalArgumentException("orderId is required.");
		}
		try {
			return Integer.parseInt(orderId.trim());
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("orderId must be a numeric value.");
		}
	}

	private Integer parseOptionalOrderId(String orderId) {
		if (orderId == null || orderId.isBlank()) {
			return null;
		}
		try {
			return Integer.parseInt(orderId.trim());
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("orderId must be a numeric value.");
		}
	}
}
