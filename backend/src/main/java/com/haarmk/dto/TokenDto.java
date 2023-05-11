package com.haarmk.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    private TokenType tokenType;
    private String tokenValue;
    private Long duration;
    private OffsetDateTime expiryDate;

    public enum TokenType {
        ACCESS, REFRESH
    }
}
