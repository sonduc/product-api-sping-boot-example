package com.example.productapi.dto;

import java.math.BigDecimal;

public record ProductFilter(String name, BigDecimal minPrice, BigDecimal maxPrice, Boolean inStock) {
}
