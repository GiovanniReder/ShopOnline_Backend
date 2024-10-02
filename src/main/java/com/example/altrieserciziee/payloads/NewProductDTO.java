package com.example.altrieserciziee.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewProductDTO(

        @NotEmpty(message = "The title is a mandatory data!")
        @Size(min = 2, max = 250, message = "Title must be between 2 and 250 chars!")
        String title,

        @NotNull(message = "The price is a mandatory data!")
        float price,

        @NotEmpty(message = "The category is a mandatory data!")
        @Size(min = 2, max = 250, message = "Category must be between 2 and 250 chars!")
        String category,

        @NotEmpty(message = "The images are a mandatory data!")
        List<String> img,

        @NotEmpty(message = "The description is a mandatory data!")
        @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 chars!")
        String description,

        @NotEmpty(message = "The details are a mandatory data!")
        @Size(min = 2, max = 1000, message = "Details must be between 2 and 1000 chars!")
        String details
) {
}

//  String title;
//    Float price;
//    String category;
//    List<String> img;
//    String description;
//    String details;