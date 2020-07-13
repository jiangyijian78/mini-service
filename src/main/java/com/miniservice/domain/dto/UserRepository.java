package com.miniservice.domain.dto;

import com.miniservice.domain.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);
    User findByName(String name);
    User findByOpenId(String openId);
}
