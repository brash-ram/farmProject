package com.rsreu.bestProject.config;

public record JwtConfig(
    String secret,
    Long expiration,
    String headerName
) {
}
