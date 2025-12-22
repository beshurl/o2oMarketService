package com.ssafy.o2omystore.dto;

import java.time.LocalDateTime;

public class PointHistory {

	private long pointId;
	private String userId;
	private int earnedPoints;
	private int usedPoints;
	private int expiredPoints;
	private String earnMethod;
	private String expireReason;
	private LocalDateTime validFrom;
	private LocalDateTime validTo;
	private LocalDateTime occurredAt;

	public long getPointId() {
		return pointId;
	}

	public void setPointId(long pointId) {
		this.pointId = pointId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}

	public int getUsedPoints() {
		return usedPoints;
	}

	public void setUsedPoints(int usedPoints) {
		this.usedPoints = usedPoints;
	}

	public int getExpiredPoints() {
		return expiredPoints;
	}

	public void setExpiredPoints(int expiredPoints) {
		this.expiredPoints = expiredPoints;
	}

	public String getEarnMethod() {
		return earnMethod;
	}

	public void setEarnMethod(String earnMethod) {
		this.earnMethod = earnMethod;
	}

	public String getExpireReason() {
		return expireReason;
	}

	public void setExpireReason(String expireReason) {
		this.expireReason = expireReason;
	}

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDateTime getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}

	public LocalDateTime getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(LocalDateTime occurredAt) {
		this.occurredAt = occurredAt;
	}
}
