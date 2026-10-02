package com.example.productapi.support;

import com.example.productapi.dto.ProductRequest;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductBuilder {
    private String name = "Test product " + UUID.randomUUID();
    private String description = "Test description";
    private BigDecimal price = new BigDecimal("125.50");
    private int stock = 5;

    public ProductBuilder name(String value) { name = value; return this; }
    public ProductBuilder price(BigDecimal value) { price = value; return this; }
    public ProductBuilder stock(int value) { stock = value; return this; }
    public ProductRequest build() { return new ProductRequest(name, description, price, stock); }
}
