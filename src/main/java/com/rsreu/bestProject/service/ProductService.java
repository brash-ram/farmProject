package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.jpa.ProductCategoryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.util.DtoMapper;
import com.rsreu.bestProject.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FileUtil fileUtil;

    private final CategoryService categoryService;

    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    private final UserRepository userRepository;

    @Transactional
    public ProductDTO add(AddProductDTORequest dto, String pathToImage) {
        String filePath = "";
        if (dto.getImage() != null) {
            filePath = fileUtil.save(dto.getImage(), pathToImage);
        }
        var user = userRepository.findById(dto.getFarmerId()).orElse(null);
        Product product = new Product()
                .setImage(filePath)
                .setCategory(categoryService.getByName(dto.getCategory()))
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPriceBoard(dto.getPriceBoard())
                .setTags(dto.getTags().stream().map(TagProduct::getById).toList())
                .setPrice(dto.getPrice())
                .setTradePrice(dto.getTradePrice())
                .setDateRegistration(OffsetDateTime.now())
                .setUserInfo(user);
        productRepository.save(product);
        return dtoMapper.mapProductToDTO(product);
    }


    public List<ProductDTO> all() {
        return dtoMapper.mapProductsToDTO(productRepository.findAll());
    }

    public ProductDTO update(AddProductDTORequest dto, String imaegs) {
        String filePath = "";
        if (dto.getImage() != null) {
            filePath = fileUtil.save(dto.getImage(), imaegs);
        }
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

    @Transactional
    public void delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }
}
