package com.rsreu.bestProject.util;


import com.rsreu.bestProject.data.entity.*;
import com.rsreu.bestProject.dto.analyse.DeliveryAnalyzeDTO;
import com.rsreu.bestProject.dto.analyse.ProductAnalyzeDTO;
import com.rsreu.bestProject.dto.cart.CartDTO;
import com.rsreu.bestProject.dto.course.CourseDTO;
import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.typeMap(UserInfo.class, UserInfoDTO.class).addMappings(mp -> {
            mp.skip(UserInfoDTO::setRoles);
            mp.skip(UserInfoDTO::setDateRegistration);
            mp.skip(UserInfoDTO::setRating);
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
            //mp.skip(DeliveryDTO::setProduct);
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

    public UserInfoDTO mapUserInfoToDto(UserInfo userInfo, Double rating) {
        UserInfoDTO userInfoDTO = modelMapper.map(userInfo, UserInfoDTO.class);
        userInfoDTO.setRoles(userInfo.getRoles().stream().map(Role::getName).toList());
        userInfoDTO.setDateRegistration(userInfo.getDateRegistration().toEpochSecond());
        userInfoDTO.setRating(RatingUtil.getAverage(userInfo.getRating()));
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
        dto.setDate(delivery.getDate() != null ? delivery.getDate().toEpochSecond() : OffsetDateTime.now().toEpochSecond());
        dto.setProduct(mapProductToDTO(delivery.getProduct()));
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
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(mapProductToDTO(product));
        }
        return productDTOS;
    }

    public ProductAnalyzeDTO mapProductToAnalyze(Product product) {
        return new ProductAnalyzeDTO()
                .setCategory(product.getCategory().getName())
                .setName(product.getName())
                .setPosition(product.getPosition())
                .setDateRegistration(product.getDateRegistration().toEpochSecond());
    }

    public DeliveryAnalyzeDTO mapDeliveryToAnalyze(Delivery delivery, Product product) {
        return new DeliveryAnalyzeDTO()
                .setCount(delivery.getCount())
                .setDeliveryDate(delivery.getDeliveryType().getId())
                .setUnit(delivery.getProduct().getUnit().getId())
                .setProduct(mapProductToAnalyze(product));
    }

    public CourseDTO mapCourseToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }

    public List<CourseDTO> mapCoursersToDTO(List<Course> courses) {
        return courses.stream().map(this::mapCourseToDTO).toList();
    }

    public CartDTO mapCartToDTO(Cart cart) {
        CartDTO dto = new CartDTO()
                .setOwner(mapUserInfoToDto(cart.getOwner(), 0D))
                .setProducts(mapProductsToDTO(cart.getProducts()));
        return dto;
    }

}
