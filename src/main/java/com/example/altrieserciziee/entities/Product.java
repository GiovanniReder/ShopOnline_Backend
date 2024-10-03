package com.example.altrieserciziee.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Products")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    String title;
    Float price;
    String category;
    List<String> img;
    String description;
    String details;
    Boolean isOnSale;
    Float discountedPrice;
    Integer discountedPercentage;

    public Product(String details, int discountedPercentage, Float discountedPrice, boolean isOnSale, String description, List<String> img, String category, Float price, String title) {
        this.details = details;
        this.discountedPercentage = discountedPercentage;
        this.discountedPrice = discountedPrice;
        this.isOnSale = isOnSale;
        this.description = description;
        this.img = img;
        this.category = category;
        this.price = price;
        this.title = title;
    }
}
