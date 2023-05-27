package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Cart;
import com.rsreu.bestProject.data.entity.Product;
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
import jakarta.transaction.Transactional;
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

    @Transactional
    public CartDTO add(Long userId, Long productId){
        var userCart = getCartByUser(userId);
        var product = productRepository.findById(productId).get();
        List<Product> products = userCart.getProducts();
        products.add(product);
        userCart.setProducts(products);
        return dtoMapper.mapCartToDTO(userCart);
    }

    private Cart getCartByUser(Long userId){
        var user = userRepository.findById(userId).get();
        return cartRepository.findByOwner(user).get();
    }

    @Transactional
    public boolean delete(Long userId, Long productId){
        var cart = getCartByUser(userId);
        var product = productRepository.findById(productId).get();
        List<Product> products = cart.getProducts();
        products.remove(product);
        cart.setProducts(products);
        return true;
    }

    public Cart cretaeCart(UserInfo user) {
        Cart cart = new Cart().setOwner(user);
        cartRepository.save(cart);
        return cart;
    }

    public TemplateDTO update(TemplateDTORequest template){
        //var entity = templateRepository.findById(0L).orElse(null);
        //entity.set..
        return null;//dtoMapper.mapTemlateDto(entity);
    }


    public List<ProductDTO> getProducts(UserInfo user) {
        return dtoMapper.mapProductsToDTO(cartRepository.findByOwner(user).get().getProducts());
    }

    @Transactional
    public Boolean productExist(Long userId, Long productId) {
        UserInfo user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        return cartRepository.existsByOwnerAndProductsContains(user, product);
    }

    public CartDTO my(UserInfo user) {
        return dtoMapper.mapCartToDTO(cartRepository.findByOwner(user).get());
    }
}
