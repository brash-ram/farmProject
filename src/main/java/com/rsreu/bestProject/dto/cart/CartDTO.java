package com.rsreu.bestProject.dto.cart;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.dto.user.UserInfoDTO;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartDTO {

    @NotNull
    private Long id;

    @NotNull
    private UserInfoDTO owner;

    @NotNull
    private List<ProductDTO> products;
}
