package com.rsreu.bestProject.dto.product.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDiscountDTORequest {
    @NotNull
    private Long productId;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer discount;
}
