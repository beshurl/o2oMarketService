package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.PointHistory;

public interface PointHistoryService {
	List<PointHistory> getPointHistoryByUserId(String userId);

	void createPointHistory(PointHistory pointHistory);
}
