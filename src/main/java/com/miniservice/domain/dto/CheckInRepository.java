package com.miniservice.domain.dto;

import com.miniservice.domain.core.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {


    @Query(value = "select id,user_id,form_id,create_time,check_in_time from check_in where user_id =?1 and check_in_time >= ?2 and check_in_time < ?3 order by check_in_time desc",
            nativeQuery=true)
    List<CheckIn> findByUserId(@Param("userId") Long userId,
                               @Param("startTime") Date startTime,
                               @Param("endTime") Date endTime);
}
