package com.rsreu.bestProject.dto.delivery;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.enums.DeliveryType;
import com.rsreu.bestProject.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    @NotNull
    private Long id;

    @NotNull
    private DeliveryType deliveryType;

    @NotNull
    private Long date;

    @NotNull
    private String adressFrom;

    @NotNull
    private String adressTo;

    @NotNull
    private Long period;

    @NotNull
    private ProductDTO product;

    @NotNull
    private Long consumerId;

    @NotNull
    private  Long farmerId;

    @NotNull
    private PaymentType paymentType;


}
