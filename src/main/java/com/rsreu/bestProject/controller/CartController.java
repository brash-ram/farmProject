package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.cart.CartDTOResponse;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.request.TemplateDTORequest;
import com.rsreu.bestProject.dto.template.response.TemplateDTOResponse;
import com.rsreu.bestProject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTOResponse> add(@RequestBody Long userId) {
        //cartService.add(template);
        return ResponseEntity
                .ok()
                .body(new CartDTOResponse());
    }

    @PostMapping("/delete")
    public ResponseEntity<TemplateDTOResponse> delete(@RequestParam Long id) {
        if(cartService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @PostMapping("/update")
    public ResponseEntity<TemplateDTO> update(@RequestBody TemplateDTORequest request) {
        return ResponseEntity.ok(cartService.update(request));
    }

}
