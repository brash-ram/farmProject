package com.rsreu.bestProject.controller;


import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddDiscountDTORequest;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.dto.product.request.UpdateProductDTORequest;
import com.rsreu.bestProject.security.AuthUtil;
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
        productService.add(
                addProductDTORequest,
                request.getSession().getServletContext().getRealPath("/images/"),
                //AuthUtil.getUserFromContext(request)
                null
        );

        return null;
    }

    @PostMapping("/my")
    public ResponseEntity<List<ProductDTO>> myProducts() {
        UserInfo user = AuthUtil.getUserFromContext();
        return ResponseEntity.ok(productService.my(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDTO> update(
            @RequestBody UpdateProductDTORequest dto,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(productService.update(dto,  request.getSession().getServletContext().getRealPath("/images/")));
    }

    @GetMapping("/api/product/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(productService.getAllCategories());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> all() {
        return ResponseEntity.ok(productService.all());
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ProductDTO> getById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductDtoById(id));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<ProductDTO>> searchCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.searchByCategory(category));
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductDTO>> searchName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<ProductDTO>> searchPosition(@RequestParam String location) {
        return ResponseEntity.ok(productService.searchByPosition(location));
    }

    @PostMapping("/add/discount")
    public ResponseEntity<ProductDTO> addNewDiscount(@RequestBody AddDiscountDTORequest request) {
        return ResponseEntity.ok(productService.addDiscount(request));
    }
}
