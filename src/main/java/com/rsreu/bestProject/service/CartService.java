package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.CartRepository;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final DtoMapper dtoMapper;

    public List<ProductDTO> getProducts(UserInfo user) {
        return dtoMapper.mapProductsToDTO(cartRepository.findByOwner(user).get().getProducts());
    }
}
