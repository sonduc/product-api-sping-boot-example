package com.example.productapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 255) String name,
        String description,
        @NotNull @DecimalMin(value = "0.00", inclusive = false)
        @Digits(integer = 10, fraction = 2) BigDecimal price,
        @NotNull @Min(0) Integer stock
) {
}
