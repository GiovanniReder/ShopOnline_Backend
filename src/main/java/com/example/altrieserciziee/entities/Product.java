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

    public UUID getId() {
        return id;
    }

    public Integer getDiscountedPercentage() {
        return discountedPercentage;
    }

    public void setDiscountedPercentage(Integer discountedPercentage) {
        this.discountedPercentage = discountedPercentage;
    }

    public Float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
