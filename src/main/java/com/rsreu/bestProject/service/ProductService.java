package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.ProductCategory;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.ProductCategoryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.product.request.AddProductDTORequest;
import com.rsreu.bestProject.dto.product.request.UpdateProductDTORequest;
import com.rsreu.bestProject.enums.AnalyzeMessageType;
import com.rsreu.bestProject.util.AnalyzeUtil;
import com.rsreu.bestProject.util.DateUtils;
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

    private final ProductCategoryRepository productCategoryRepository;

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
                .setPosition(dto.getPosition())
                        .setStartSales(DateUtils.parse(dto.getStartSales()))
                        .setEndSales(DateUtils.parse(dto.getEndSales()));
        productRepository.save(product);

        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapProductToAnalyze(product), AnalyzeMessageType.ADD));
        return dtoMapper.mapProductToDTO(product);
    }


    public List<ProductDTO> all() {
        return dtoMapper.mapProductsToDTO(productRepository.findAll());
    }

    public ProductDTO update(UpdateProductDTORequest dto, String imaegs) {
        String filePath = "";
        if (dto.getImage() != null) {
            filePath = fileUtil.save(dto.getImage(), imaegs);
        }
        Product product = new Product()
                .setId(dto.getIdProduct())
                .setImage(filePath)
                .setCategory(categoryService.getByName(dto.getCategory()))
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPriceBoard(dto.getPriceBoard())
                .setTags(dto.getTags())
                .setPrice(dto.getPrice())
                .setTradePrice(dto.getTradePrice())
                .setUnit(dto.getUnit())
                .setPosition(dto.getPosition())
                .setStartSales(DateUtils.parse(dto.getStartSales()))
                .setEndSales(DateUtils.parse(dto.getEndSales()));
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

    @Transactional
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public List<ProductDTO> my(UserInfo user) {
        return dtoMapper.mapProductsToDTO(productRepository.findAllByUserInfo(user));
    }

    public List<String> getAllCategories() {
        return productCategoryRepository.findAll().stream().map(ProductCategory::getName).toList();
    }

    public ProductDTO getProductDtoById(Long id){
        return dtoMapper.mapProductToDTO(getById(id));
    }


}
