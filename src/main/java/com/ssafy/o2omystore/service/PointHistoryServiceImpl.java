package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.PointHistoryDao;
import com.ssafy.o2omystore.dto.PointHistory;

@Service
public class PointHistoryServiceImpl implements PointHistoryService {

	private final PointHistoryDao pointHistoryDao;

	public PointHistoryServiceImpl(PointHistoryDao pointHistoryDao) {
		this.pointHistoryDao = pointHistoryDao;
	}

	@Override
	public List<PointHistory> getPointHistoryByUserId(String userId) {
		return pointHistoryDao.selectPointHistoryByUserId(userId);
	}

	@Override
	public void createPointHistory(PointHistory pointHistory) {
		pointHistoryDao.insertPointHistory(pointHistory);
	}
}
