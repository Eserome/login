package com.web.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.login.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
