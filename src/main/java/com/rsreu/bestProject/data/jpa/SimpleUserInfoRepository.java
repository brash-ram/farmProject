package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.SimpleUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimpleUserInfoRepository extends JpaRepository<SimpleUserInfo, Long> {
    Boolean existsByEmail(String email);


    Optional<SimpleUserInfo> findByEmail(String email);
}