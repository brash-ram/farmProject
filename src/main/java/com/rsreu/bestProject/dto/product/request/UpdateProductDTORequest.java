package com.rsreu.bestProject.dto.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTORequest extends AddProductDTORequest {
    @NotNull
    private Long idProduct;
}
