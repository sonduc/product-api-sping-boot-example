package com.example.productapi.repository.spec;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductFilter;
import com.example.productapi.exception.BusinessException;
import java.math.BigDecimal;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }
            String pattern = name.trim().toLowerCase(Locale.ROOT)
                    .replace("!", "!!").replace("%", "!%").replace("_", "!_");
            return cb.like(cb.lower(root.get("name")), "%" + pattern + "%", '!');
        };
    }

    public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
        if ((min != null && min.signum() < 0) || (max != null && max.signum() < 0)
                || (min != null && max != null && min.compareTo(max) > 0)) {
            throw new BusinessException("Invalid price range");
        }
        return (root, query, cb) -> {
            var lower = min == null ? cb.conjunction()
                    : cb.greaterThanOrEqualTo(root.<BigDecimal>get("price"), min);
            var upper = max == null ? cb.conjunction()
                    : cb.lessThanOrEqualTo(root.<BigDecimal>get("price"), max);
            return cb.and(lower, upper);
        };
    }

    public static Specification<Product> inStock(Boolean inStock) {
        return (root, query, cb) -> {
            if (inStock == null) {
                return cb.conjunction();
            }
            return inStock ? cb.greaterThan(root.<Integer>get("stock"), 0)
                    : cb.equal(root.get("stock"), 0);
        };
    }

    public static Specification<Product> build(ProductFilter filter) {
        if (filter == null) {
            return (root, query, cb) -> cb.conjunction();
        }
        return hasName(filter.name()).and(priceBetween(filter.minPrice(), filter.maxPrice()))
                .and(inStock(filter.inStock()));
    }
}
