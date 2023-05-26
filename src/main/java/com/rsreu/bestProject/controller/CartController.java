package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.Cart;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

//    @GetMapping("/my")
//    public ResponseEntity<Cart> getMyCart(
//            HttpServletRequest request
//    ) {
//        return null;
//    }
}
