package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.PointHistory;

@Mapper
public interface PointHistoryDao {
	List<PointHistory> selectPointHistoryByUserId(String userId);
}
