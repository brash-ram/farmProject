package com.rsreu.bestProject.controller;


import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody AddProductDTORequest addProductDTORequest,
            HttpServletRequest request
    ) {
        productService.add(addProductDTORequest,  request.getSession().getServletContext().getRealPath("/images/"));

        return null;
    }

//    @PostMapping("/delete")
//    public ResponseEntity<TemplateDTOResponse> delete(@RequestParam Long id) {
//        if(productService.delete(id))
//            return ResponseEntity.ok().build();
//        else
//            return ResponseEntity.badRequest().build();
//
//    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> update(@RequestBody AddProductDTORequest request) {
        return ResponseEntity.ok(productService.update(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> all() {
        return ResponseEntity.ok(productService.all());
    }
}
