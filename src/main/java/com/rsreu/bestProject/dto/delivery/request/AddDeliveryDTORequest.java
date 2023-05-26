package com.rsreu.bestProject.dto.delivery.request;

import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.enums.DeliveryType;
import com.rsreu.bestProject.enums.Unit;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class AddDeliveryDTORequest {
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
    private Long productId;

    @NotNull
    private Long farmerId;

    @NotNull
    private Long consumerId;



}
