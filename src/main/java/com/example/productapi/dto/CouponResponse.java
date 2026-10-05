package com.example.productapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.productapi.domain.CouponType;

public record CouponResponse(
        Long id,
        String code,
        CouponType type,
        BigDecimal discountValue,
        LocalDateTime startsAt,
        LocalDateTime expiresAt,
        Long usageLimit,
        Long usageCount,
        Long version,
        Long createdBy,
        Long updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
