package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Cart;
import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.CartRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.TemplateRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.cart.CartDTO;
import com.rsreu.bestProject.dto.cart.CartDTOResponse;
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

    public CartDTO add(Long userId, Long productId){
        var userCart = getCartByUser(userId);
        var product = productRepository.findById(productId).get();
        if(userCart != null && product != null){
            userCart.getProducts().add(product);
        }
        return dtoMapper.mapCartToDTO(userCart);
    }

    private Cart getCartByUser(Long userId){
        var user = userRepository.findById(userId).get();
        return cartRepository.findByOwner(user).get();
    }

    public boolean delete(Long userId, Long productId){
        var cart = getCartByUser(userId);
        var product = productRepository.findById(productId).get();
        if(cart != null && product != null){
            cart.getProducts().remove(product);
            return true;
        }
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
