package com.example.altrieserciziee.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(

        @NotEmpty(message = "The email is mandatory data!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
        String email,

        @NotEmpty(message = "The password is mandatory data!")
        @Size(min = 3, max = 25, message = "The password must be between 3 and 25 characters!")
        String password,

        @NotEmpty(message = "The name is mandatory data!")
        @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters!")
        String name,

        @NotEmpty(message = "The surname is mandatory data!")
        @Size(min = 2, max = 50, message = "The surname must be between 2 and 50 characters!")
        String surname





) {
}
/*
*    private UUID id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String avatar;
    private Boolean isAdmin;
*
* */