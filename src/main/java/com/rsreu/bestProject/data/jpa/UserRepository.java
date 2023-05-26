package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    //Optional<UserInfo> findByUsername(String username);
    //Boolean existsByUsername(String username);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByEmailAndPassword(String email, String password);
}