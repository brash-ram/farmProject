package com.rsreu.bestProject.dto.product.request;

import com.rsreu.bestProject.data.entity.ProductCategory;
import com.rsreu.bestProject.enums.TagProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDTORequest {
    @NotNull
    private Long id;

    @NotNull
    private String image;

    @NotNull
    private String description;

    @NotNull
    private String name;

    @NotNull
    private ProductCategory category;

    @NotNull
    private List<TagProduct> tags;

    @NotNull
    private String position;

    @NotNull
    private Double rating;

    @Min(0)
    @NotNull
    private Long price;

    @Min(0)
    @NotNull
    private Long tradePrice;

    @Min(0)
    @NotNull
    private Long priceBoard;
}
