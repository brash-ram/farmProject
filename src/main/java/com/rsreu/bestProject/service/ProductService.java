package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.jpa.ProductCategoryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.util.DtoMapper;
import com.rsreu.bestProject.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FileUtil fileUtil;

    private final CategoryService categoryService;

    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    @Transactional
    public ProductDTO add(AddProductDTORequest dto, String pathToImage) {
        String filePath = fileUtil.save(dto.getImage(), pathToImage);
        Product product = new Product()
                .setImage(filePath)
                .setCategory(categoryService.getByName(dto.getName()))
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPriceBoard(dto.getPriceBoard())
                .setTags(dto.getTags().stream().map(TagProduct::getById).toList())
                .setPrice(dto.getPrice())
                .setTradePrice(dto.getTradePrice());
        productRepository.save(product);
        return dtoMapper.mapProductToDTO(product);
    }


}
