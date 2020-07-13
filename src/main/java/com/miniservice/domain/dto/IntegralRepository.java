package com.miniservice.domain.dto;

import com.miniservice.domain.core.Integral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IntegralRepository extends JpaRepository<Integral, Long> {
	Integral findByUserId(Long userId);
    @Modifying
    @Query(value = "update integral set integral = integral + 10 where user_id = ?1 and integral = ?2", nativeQuery=true)
    @Transactional
    int addIntegral(@Param("userId") Long userId,
                    @Param("integral") Long integral);
}
