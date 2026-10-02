package com.example.productapi.service;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductFilter;
import com.example.productapi.exception.BusinessException;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Test
    void returnsDtoPageAndPreservesPaginationMetadata() {
        var pageable = PageRequest.of(1, 2, Sort.by("price").ascending());
        when(repository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(List.of(
                        new Product("Phone", null, new BigDecimal("199.00"), 3)), pageable, 5));
        var service = new ProductService(repository, new ProductMapper());
        var result = service.getAll(pageable,
                new ProductFilter("phone", new BigDecimal("100"), null, true));
        assertThat(result.getNumber()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getTotalElements()).isEqualTo(5);
        assertThat(result.getTotalPages()).isEqualTo(3);
        assertThat(result.getContent()).extracting("name").containsExactly("Phone");
    }

    @Test
    void rejectsInvalidFilterBeforeQueryingRepository() {
        var service = new ProductService(repository, new ProductMapper());
        assertThatThrownBy(() -> service.getAll(PageRequest.of(0, 10),
                new ProductFilter(null, BigDecimal.TEN, BigDecimal.ONE, null)))
                .isInstanceOf(BusinessException.class);
        verifyNoInteractions(repository);
    }
}
