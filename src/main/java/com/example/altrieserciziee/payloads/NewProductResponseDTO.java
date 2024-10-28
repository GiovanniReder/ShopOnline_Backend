package com.example.altrieserciziee.payloads;

import java.util.List;
import java.util.UUID;

public record NewProductResponseDTO(UUID uuid, String title ,Float price, String category, List<String> img, String description, String details, Boolean isOnSale, Integer discountedPercentage ) {
}
/*
*  String title;
    Float price;
    String category;
    List<String> img;
    String description;
    String details;
    Boolean isOnSale;
    Float discountedPrice;
    Integer discountedPercentage;
* */