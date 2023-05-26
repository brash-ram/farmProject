package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserInfo(UserInfo userInfo);
}