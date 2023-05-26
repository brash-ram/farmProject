package com.rsreu.bestProject.util;


import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import com.rsreu.bestProject.enums.TagProduct;
import lombok.RequiredArgsConstructor;
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
        });
    }

    public UserInfoDTO mapUserInfoToDto(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = modelMapper.map(userInfo, UserInfoDTO.class);
        userInfoDTO.setRoles(userInfo.getRoles().stream().map(role -> role.getName().getId()).toList());
        userInfoDTO.setDateRegistration(userInfo.getDateRegistration().toEpochSecond());
        return userInfoDTO;
    }

    public TemplateDTO mapTemlateDto(TemplateEntity template){
        return modelMapper.map(template, TemplateDTO.class);
    }

    public ProductDTO mapProductToDTO(Product product) {
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        dto.setCategory(product.getCategory().getName());
        dto.setTags(product.getTags().stream().map(TagProduct::getId).toList());
        dto.setRating(RatingUtil.getAverage(product.getRating()));
        dto.setIdUser(product.getUserInfo().getId());
        dto.setDateRegistration(product.getDateRegistration().toEpochSecond());

        return dto;
    }

    public List<ProductDTO> mapProductsToDTO(List<Product> products) {
        return products.stream().map(this::mapProductToDTO).toList();
    }

}
