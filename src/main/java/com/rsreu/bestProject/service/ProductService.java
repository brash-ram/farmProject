package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.ProductCategoryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.enums.AnalyzeMessageType;
import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.enums.Unit;
import com.rsreu.bestProject.security.AuthUtil;
import com.rsreu.bestProject.util.AnalyzeUtil;
import com.rsreu.bestProject.util.DtoMapper;
import com.rsreu.bestProject.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
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

    private final Analyser analyser;

    private final UserService userService;

    @Transactional
    public ProductDTO add(AddProductDTORequest dto, String pathToImage, UserInfo user) {
        String filePath = "";
        if (dto.getImage() != null) {
            filePath = fileUtil.save(dto.getImage(), pathToImage);
        }
        user = userService.getById(dto.getFarmerId());
        Product product = new Product()
                .setImage(filePath)
                .setCategory(categoryService.getByName(dto.getCategory()))
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPriceBoard(dto.getPriceBoard())
                .setTags(dto.getTags())
                .setPrice(dto.getPrice())
                .setTradePrice(dto.getTradePrice())
                .setDateRegistration(OffsetDateTime.now())
                .setUserInfo(user)
                        .setUnit(dto.getUnit())
                .setPosition(dto.getPosition());
        productRepository.save(product);

        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapProductToAnalyze(product), AnalyzeMessageType.ADD));
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
                .setTags(dto.getTags())
                .setPrice(dto.getPrice())
                .setTradePrice(dto.getTradePrice())
                        .setPosition(dto.getPosition());
        productRepository.save(product);

        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapProductToAnalyze(product), AnalyzeMessageType.UPDATE));
        return dtoMapper.mapProductToDTO(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapProductToAnalyze(product), AnalyzeMessageType.REMOVE));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public List<ProductDTO> my(UserInfo user) {
        return dtoMapper.mapProductsToDTO(productRepository.findAllByUserInfo(user));
    }
}
