package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.data.entity.Cart;
import com.rsreu.bestProject.dto.product.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/my")
    public ResponseEntity<List<ProductDTO>> getMyCart(
            HttpServletRequest request
    ) {
        return null;
    }

//    @PostMapping("/add")
//    public ResponseEntity<>
}
