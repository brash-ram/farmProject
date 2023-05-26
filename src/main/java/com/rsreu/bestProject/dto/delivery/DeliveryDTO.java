package com.rsreu.bestProject.dto.delivery;

import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.enums.DeliveryType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

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
    private Product product;

    @NotNull
    private UserInfo consumer;

    @NotNull
    private  UserInfo farmer;



}
