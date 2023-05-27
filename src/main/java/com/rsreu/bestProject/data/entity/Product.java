package com.rsreu.bestProject.data.entity;

import com.rsreu.bestProject.enums.DeliveryType;
import com.rsreu.bestProject.enums.TagProduct;
import com.rsreu.bestProject.enums.Unit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "description", length=1000)
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private ProductCategory category;

    @Column(name = "tags")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_rating", joinColumns = @JoinColumn(name = "product_id"))
    private List<TagProduct> tags = new ArrayList<>();

    @Column(name = "position")
    private String position;

    @Column(name = "rating")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_rating", joinColumns = @JoinColumn(name = "product_id"))
    private List<Integer> rating = new ArrayList<>();

    @Column(name = "price")
    @Min(0)
    private Long price;

    @Column(name = "trade_price")
    @Min(0)
    private Long tradePrice;

    @Column(name = "price_board")
    @Min(0)
    private Long priceBoard;

    @ManyToOne()
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", length = 30)
    private Unit unit;

    @Column(name = "date_registration")
    private OffsetDateTime dateRegistration;

    @Column(name = "start_sales")
    private OffsetDateTime startSales;

    @Column(name = "end_sales")
    private OffsetDateTime endSales;

    @Column(name = "delivery_types")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<DeliveryType> deliveryTypes;

}
