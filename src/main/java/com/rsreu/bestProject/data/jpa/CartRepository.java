package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.Cart;
import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByOwner(UserInfo owner);

    Boolean existsByOwnerAndProductsContains(UserInfo owner, Product product);
}