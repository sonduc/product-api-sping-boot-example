package com.example.productapi.mapper;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getStock(), product.getCreatedAt(), product.getUpdatedAt());
    }

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }
        return new Product(request.name(), request.description(), request.price(), request.stock());
    }

    public void updateEntity(Product product, ProductRequest request) {
        Objects.requireNonNull(product, "Product must not be null");
        Objects.requireNonNull(request, "Product request must not be null");
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
    }
}
