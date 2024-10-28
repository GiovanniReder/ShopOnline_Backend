package com.example.altrieserciziee.payloads;

import jakarta.validation.constraints.*;

import java.util.List;

public record NewProductDTO(

        @NotEmpty(message = "The title is a mandatory data!")
        @Size(min = 2, max = 250, message = "Title must be between 2 and 250 chars!")
        String title,

        @NotNull(message = "The price is a mandatory data!")
        @Positive(message = "Price must be positive!")
        Float price,

        @NotEmpty(message = "The category is a mandatory data!")
        @Size(min = 2, max = 250, message = "Category must be between 2 and 250 chars!")
        String category,

        @NotEmpty(message = "The images are a mandatory data!")
        @Size(min = 1, message = "At least one image is required!")
        List<@Pattern(regexp = "https?://.*", message = "Invalid image URL!") String> img,

        @NotEmpty(message = "The description is a mandatory data!")
        @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 chars!")
        String description,

        @NotEmpty(message = "The details are a mandatory data!")
        @Size(min = 2, max = 1000, message = "Details must be between 2 and 1000 chars!")
        String details,

        Boolean isOnSale,

        @Min(0)
        @Max(100)
        Integer discountedPercentage
) {
        public NewProductDTO {

                if (Boolean.TRUE.equals(isOnSale)) {
                        if (discountedPercentage == null) {
                                throw new IllegalArgumentException("If the product is on sale, discounted percentage must be provided.");
                        }
                        if (discountedPercentage < 0 || discountedPercentage > 100) {
                                throw new IllegalArgumentException("Discounted percentage must be between 0 and 100.");
                        }
                }
        }
}


/*
String title;
Float price;
String category;
List<String> img;
String description;
String details;

boolean isOnSale;
Float discountedPrice;
int discountedPercentage;
* */
