package com.rsreu.bestProject.dto.product;

import com.rsreu.bestProject.enums.DeliveryType;
import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.enums.Unit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull
    private Long id;

    @NotNull
    private String image;

    @NotNull
    @Size(max = 4000)
    private String description;

    @NotNull
    private String name;

    @NotNull
    private String category;

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

    @NotNull
    private Long idUser;

    @NotNull
    private Long dateRegistration;

    @NotNull
    private Long startSales;

    @NotNull
    private Long endSales;

    @NotNull
    private Unit unit;

    @NotNull
    private Integer discount;

    @NotNull
    private List<DeliveryType> deliveryTypes = new ArrayList<>();
}
