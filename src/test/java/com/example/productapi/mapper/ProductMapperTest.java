package com.example.productapi.mapper;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper mapper = new ProductMapper();

    @Test
    void mapsAndUpdatesProductFields() {
        ProductRequest request = new ProductRequest("Phone", "Mobile", new BigDecimal("199.00"), 3);
        Product product = mapper.toEntity(request);
        assertThat(mapper.toResponse(product).name()).isEqualTo("Phone");
        assertThat(mapper.toResponse(product).price()).isEqualByComparingTo("199.00");
        mapper.updateEntity(product, new ProductRequest("Tablet", null, new BigDecimal("250.00"), 0));
        assertThat(product.getName()).isEqualTo("Tablet");
        assertThat(product.getDescription()).isNull();
        assertThat(product.getPrice()).isEqualByComparingTo("250.00");
        assertThat(product.getStock()).isZero();
    }

    @Test
    void handlesNullMappingAndRejectsNullUpdates() {
        assertThat(mapper.toEntity(null)).isNull();
        assertThat(mapper.toResponse(null)).isNull();
        Product product = new Product("Phone", null, BigDecimal.ONE, 1);
        assertThatNullPointerException().isThrownBy(() -> mapper.updateEntity(null,
                new ProductRequest("Phone", null, BigDecimal.ONE, 1)));
        assertThatNullPointerException().isThrownBy(() -> mapper.updateEntity(product, null));
        assertThat(product.getName()).isEqualTo("Phone");
    }
    @Test
    void updatesBusinessFieldsWithoutReplacingPersistenceMetadata() {
        Product product = new Product("Before", "Old", BigDecimal.TEN, 1);
        var createdAt = java.time.LocalDateTime.of(2026, 1, 1, 0, 0);
        product.setCreatedAt(createdAt);
        product.setUpdatedAt(createdAt);
        mapper.updateEntity(product, new ProductRequest("After", null, new BigDecimal("9999999999.99"), 0));
        assertThat(product.getCreatedAt()).isEqualTo(createdAt);
        assertThat(product.getUpdatedAt()).isEqualTo(createdAt);
        assertThat(product.getVersion()).isNull();
        assertThat(product.getId()).isNull();
        assertThat(mapper.toResponse(product).description()).isNull();
        assertThat(mapper.toResponse(product).stock()).isZero();
    }
}
