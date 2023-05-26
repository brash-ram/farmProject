package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.ProductCategory;
import com.rsreu.bestProject.data.jpa.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory getByName(String name) {
        return productCategoryRepository.findByName(name).get();
    }
}
