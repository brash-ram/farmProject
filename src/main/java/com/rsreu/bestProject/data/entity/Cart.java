package com.rsreu.bestProject.data.entity;

import jakarta.persistence.*;
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
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private UserInfo owner;

    @ManyToMany
    private List<Product> products;
}
