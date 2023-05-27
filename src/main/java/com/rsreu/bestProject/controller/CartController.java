package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.cart.CartDTO;
import com.rsreu.bestProject.dto.cart.CartDTOResponse;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.request.TemplateDTORequest;
import com.rsreu.bestProject.dto.template.response.TemplateDTOResponse;
import com.rsreu.bestProject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> add(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity
                .ok()
                .body(cartService.add(userId, productId));
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<Void> delete(@RequestParam Long userId, @RequestParam Long productId) {
        if(cartService.delete(userId, productId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @GetMapping("/productExist")
    public ResponseEntity<Boolean> existProduct(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.productExist(userId, productId));
    }

//    @PostMapping("/update")
//    public ResponseEntity<TemplateDTO> update(@RequestBody TemplateDTORequest request) {
//        return ResponseEntity.ok(cartService.update(request));
//    }

}
