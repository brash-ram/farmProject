package com.rsreu.bestProject.data.entity;

import com.rsreu.bestProject.enums.TagProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private ProductCategory category;

    @Column(name = "tags")
    @ElementCollection
    @CollectionTable(name = "product_rating", joinColumns = @JoinColumn(name = "product_id"))
    private List<TagProduct> tags;

    @Column(name = "position")
    private String position;

    @Column(name = "rating")
    @ElementCollection
    @CollectionTable(name = "product_rating", joinColumns = @JoinColumn(name = "product_id"))
    private List<Integer> rating;

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

}
