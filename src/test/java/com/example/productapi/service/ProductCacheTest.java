package com.example.productapi.service;

import com.example.productapi.config.CacheConfig;
import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductRequest;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductCacheTest {
    @Test
    void cachesDtosAndEvictsAfterSuccessfulWrite() {
        var repository = mock(ProductRepository.class);
        Product product = new Product("Phone", null, BigDecimal.TEN, 1);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.saveAndFlush(product)).thenReturn(product);
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(CacheConfig.class);
            context.registerBean(ProductRepository.class, () -> repository);
            context.registerBean(ProductMapper.class);
            context.registerBean(ProductService.class);
            context.refresh();
            var service = context.getBean(ProductService.class);
            var first = service.getById(1L);
            assertThat(service.getById(1L)).isSameAs(first);
            verify(repository, times(1)).findById(1L);
            service.update(1L, new ProductRequest("Updated", null, BigDecimal.TEN, 1));
            assertThat(service.getById(1L).name()).isEqualTo("Updated");
            verify(repository, times(3)).findById(1L);
        }
    }

    @Test
    void rollbackKeepsCacheAndCommitEvictsIt() {
        var cache = new CacheConfig().cacheManager().getCache("products");
        cache.put(1L, new ProductMapper().toResponse(new Product("Phone", null, BigDecimal.TEN, 1)));
        TransactionSynchronizationManager.initSynchronization();
        try {
            cache.evict(1L);
            assertThat(cache.get(1L)).isNotNull();
            // No afterCommit callback is run on rollback.
        } finally {
            TransactionSynchronizationManager.clearSynchronization();
        }
        assertThat(cache.get(1L)).isNotNull();
        TransactionSynchronizationManager.initSynchronization();
        try {
            cache.evict(1L);
            TransactionSynchronizationUtils.triggerAfterCommit();
        } finally {
            TransactionSynchronizationManager.clearSynchronization();
        }
        assertThat(cache.get(1L)).isNull();
    }
}
