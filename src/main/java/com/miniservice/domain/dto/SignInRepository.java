package com.miniservice.domain.dto;

import com.miniservice.domain.core.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SignInRepository extends JpaRepository<SignIn, Long> {


    @Query(value = "select id,user_id,open_id,create_time,signin_time from sign_in where user_id =: userId and signin_time >= ?2 and signin_time < ?3 order by signin_time desc",
            nativeQuery=true)
    List<SignIn> findByUserId(@Param("userId") Long userId,
                              @Param("startTime") Date startTime,
                              @Param("endTime") Date endTime);
}
