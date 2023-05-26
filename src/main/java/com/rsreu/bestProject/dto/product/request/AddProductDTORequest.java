package com.rsreu.bestProject.dto.product.request;

import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.enums.Unit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDTORequest {
    private MultipartFile image;

    @NotNull
    private String description;

    @NotNull
    private String name;

    @NotNull
    private String category;

    @NotNull
    private List<TagProduct> tags;

    @NotNull
    private String position;

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
    private Long farmerId;

    @NotNull
    private Long dateRegistration;


    @NotNull
    private Unit unit;

    @NotNull
    private Long startSales;

    @NotNull
    private Long endSales;

}
