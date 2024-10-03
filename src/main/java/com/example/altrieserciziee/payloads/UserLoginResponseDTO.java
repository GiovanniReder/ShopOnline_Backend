package com.example.altrieserciziee.payloads;

import java.util.UUID;

public record UserLoginResponseDTO(String accessToken, String name, String surname, String avatar, UUID userId) {
}
