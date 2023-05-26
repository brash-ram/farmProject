package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.CartRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.TemplateRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.request.TemplateDTORequest;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final DtoMapper dtoMapper;

    public void add(Long userId, Long productId){
        var user = userRepository.findById(userId).get();
        var userCart = cartRepository.findByOwner(user).get().getProducts();
        var product = productRepository.findById(productId).get();
        if(userCart != null && product != null){

        }
    }

    public boolean delete(Long id){
//        TemplateEntity template = templateRepository.findById(id).orElse(null);
//        if(template != null){
//            //templateRepository.delete(template);
//            return true;
//        }
        return false;

    }

    public TemplateDTO update(TemplateDTORequest template){
        //var entity = templateRepository.findById(0L).orElse(null);
        //entity.set..
        return null;//dtoMapper.mapTemlateDto(entity);
    }


    public List<ProductDTO> getProducts(UserInfo user) {
        return dtoMapper.mapProductsToDTO(cartRepository.findByOwner(user).get().getProducts());
    }
}
