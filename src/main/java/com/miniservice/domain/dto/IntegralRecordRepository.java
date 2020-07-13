package com.miniservice.domain.dto;

import com.miniservice.domain.core.IntegralRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegralRecordRepository extends JpaRepository<IntegralRecord, Long> {
	List<IntegralRecord> findByUserId(Long userId);
}
