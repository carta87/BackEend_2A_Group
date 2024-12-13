package com.proyect.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username", "message", "status", "token"})
public class AuthResponse {

    private String username;
    private String token;
    private String message;
    private boolean status;
}
