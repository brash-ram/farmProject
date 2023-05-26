package com.rsreu.bestProject.util;


import com.rsreu.bestProject.data.entity.*;
import com.rsreu.bestProject.dto.analyse.DeliveryAnalyzeDTO;
import com.rsreu.bestProject.dto.analyse.ProductAnalyzeDTO;
import com.rsreu.bestProject.dto.cart.CartDTOResponse;
import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.enums.TagProduct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.typeMap(UserInfo.class, UserInfoDTO.class).addMappings(mp -> {
            mp.skip(UserInfoDTO::setRoles);
            mp.skip(UserInfoDTO::setDateRegistration);
        });

        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mp -> {
            mp.skip(ProductDTO::setCategory);
            mp.skip(ProductDTO::setTags);
            mp.skip(ProductDTO::setRating);
            mp.skip(ProductDTO::setIdUser);
            mp.skip(ProductDTO::setDateRegistration);
            mp.skip(ProductDTO::setUnit);
            mp.skip(ProductDTO::setEndSales);
            mp.skip(ProductDTO::setStartSales);
        });

        modelMapper.typeMap(Delivery.class, DeliveryDTO.class).addMappings(mp -> {
            mp.skip(DeliveryDTO::setDeliveryType);
            mp.skip(DeliveryDTO::setConsumerId);
            mp.skip(DeliveryDTO::setFarmerId);
            mp.skip(DeliveryDTO::setDate);
        });

        modelMapper.typeMap(Product.class, ProductAnalyzeDTO.class).addMappings(mp -> {
            mp.skip(ProductAnalyzeDTO::setDateRegistration);
            mp.skip(ProductAnalyzeDTO::setCategory);
        });

        modelMapper.typeMap(Delivery.class, DeliveryAnalyzeDTO.class).addMappings(mp -> {
            mp.skip(DeliveryAnalyzeDTO::setDeliveryDate);
            mp.skip(DeliveryAnalyzeDTO::setUnit);
        });
    }

    public UserInfoDTO mapUserInfoToDto(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = modelMapper.map(userInfo, UserInfoDTO.class);
        userInfoDTO.setRoles(userInfo.getRoles().stream().map(Role::getName).toList());
        userInfoDTO.setDateRegistration(userInfo.getDateRegistration().toEpochSecond());
        return userInfoDTO;
    }

    public TemplateDTO mapTemlateDto(TemplateEntity template){
        return modelMapper.map(template, TemplateDTO.class);
    }

    public DeliveryDTO mapDeliveryToDto(Delivery delivery){
        DeliveryDTO dto = modelMapper.map(delivery, DeliveryDTO.class);
        dto.setConsumerId(delivery.getConsumer().getId());
        dto.setFarmerId(delivery.getFarmer().getId());
        dto.setDeliveryType(delivery.getDeliveryType());
        dto.setDate(delivery.getDate().toEpochSecond());
        return dto;
    }

    public ProductDTO mapProductToDTO(Product product) {
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        dto.setCategory(product.getCategory().getName());
        dto.setTags(product.getTags());
        dto.setRating(RatingUtil.getAverage(product.getRating()));
        dto.setIdUser(product.getUserInfo().getId());
        dto.setDateRegistration(product.getDateRegistration().toEpochSecond());
        dto.setUnit(product.getUnit());
        dto.setStartSales(product.getStartSales().toEpochSecond());
        dto.setEndSales(product.getStartSales().toEpochSecond());

        return dto;
    }

    public List<ProductDTO> mapProductsToDTO(List<Product> products) {
        return products.stream().map(this::mapProductToDTO).toList();
    }

    public ProductAnalyzeDTO mapProductToAnalyze(Product product) {
        ProductAnalyzeDTO dto = modelMapper.map(product, ProductAnalyzeDTO.class);
        dto.setDateRegistration(product.getDateRegistration().toEpochSecond());
        dto.setCategory(product.getCategory().getName());
        return dto;
    }

    public DeliveryAnalyzeDTO mapDeliveryToAnalyze(Delivery delivery) {
        DeliveryAnalyzeDTO dto = modelMapper.map(delivery, DeliveryAnalyzeDTO.class);
        dto.setDeliveryDate(delivery.getDate().toEpochSecond());
        dto.setUnit(delivery.getProduct().getUnit().getId());
        return dto;
    }
}
