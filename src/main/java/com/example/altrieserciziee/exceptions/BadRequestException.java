package com.example.altrieserciziee.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(List<ObjectError> errorsList) {
        super("There are some error in the compilation of the payload!");
        this.errorsList = errorsList;
    }
}