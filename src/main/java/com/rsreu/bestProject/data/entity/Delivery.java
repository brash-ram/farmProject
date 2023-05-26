package com.rsreu.bestProject.data.entity;

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

    @Column(name = "delivery_type")
    private String name;

    @Column(name = "delivery_date_start")
    private OffsetDateTime date;

    @Column(name = "adress_from")
    private String adressFrom;

    @Column(name = "adress_to")
    private String adressTo;

    @Column(name = "adress_from")
    private String from;

    @Column(name = "period")
    private int period;

    @Column(name ="product")
    private int product;



}
