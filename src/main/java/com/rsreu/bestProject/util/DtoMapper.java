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

@Component
@RequiredArgsConstructor
public class DtoMapper {

    private final ModelMapper modelMapper;

    public UserInfoDTO mapUserInfoToDto(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = modelMapper.map(userInfo, UserInfoDTO.class);
        userInfoDTO.setRoles(userInfo.getRoles().stream().map(role -> role.getName().getId()).toList());
        return userInfoDTO;
    }

    public TemplateDTO mapTemlateDto(TemplateEntity template){
        TemplateDTO templateDTO = modelMapper.map(template, TemplateDTO.class);
        return templateDTO;
    }

    public ProductDTO mapProductToDTO(Product product) {
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        dto.setCategory(product.getCategory().getName());
        dto.setTags(product.getTags().stream().map(TagProduct::getId).toList());
        dto.setRating(RatingUtil.getAverage(product.getRating()));

        return dto;
    }

}
