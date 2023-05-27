package com.rsreu.bestProject.data.entity;

import com.rsreu.bestProject.enums.DeliveryType;
import com.rsreu.bestProject.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delivery_type", length = 30 )
    private DeliveryType deliveryType;

    @Column(name = "delivery_date_start")
    private OffsetDateTime date;

    @Column(name = "adress_from")
    private String adressFrom;

    @Column(name = "adress_to")
    private String adressTo;

    @Column(name = "period")
    private Long period;

    @Column(name = "count_product")
    private Long count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consumer_id")
    private UserInfo consumer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private  UserInfo farmer;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type")
    private PaymentType paymentType;

}
