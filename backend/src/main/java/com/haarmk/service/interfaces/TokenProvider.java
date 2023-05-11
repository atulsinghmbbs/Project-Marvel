package com.haarmk.service.interfaces;

import java.time.OffsetDateTime;

import com.haarmk.dto.TokenDto;

public interface TokenProvider {
    TokenDto generateAccessToken(String subject);

    TokenDto generateRefreshToken(String subject);

    String getUsernameFromToken(String token);

    OffsetDateTime getExpiryDateFromToken(String token);

    boolean validateToken(String token);
}
