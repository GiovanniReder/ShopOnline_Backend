package com.example.altrieserciziee.payloads;
import java.time.LocalDateTime;
public record ErrorsDTO(String message, LocalDateTime timestamp) {
}