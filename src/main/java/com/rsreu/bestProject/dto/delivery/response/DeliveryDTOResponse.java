package com.rsreu.bestProject.dto.delivery.response;

import com.rsreu.bestProject.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTOResponse {
    @NotNull
    private Long id;

    @NotNull
    private Integer deliveryType;

    @NotNull
    private Long deliveryDate;

    @NotNull
    private String adressFrom;

    @NotNull
    private String adressTo;

    @NotNull
    private Long productId;

    @NotNull
    private Long consumerId;

    @NotNull
    private  Long farmerId;

    @NotNull
    private PaymentType paymentType;
}
