package com.rsreu.bestProject.dto.cart;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    @NotNull
    private Long id;

    @NotNull
    private UserInfo owner;

    @NotNull
    private List<Product> products;
}
