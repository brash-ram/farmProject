package com.rsreu.bestProject.controller;


import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
