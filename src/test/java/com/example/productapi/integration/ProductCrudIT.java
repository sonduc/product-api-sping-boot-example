package com.example.productapi.integration;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.exception.ErrorResponse;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.support.ProductBuilder;
import java.math.BigDecimal;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

class ProductCrudIT extends BaseIntegrationTest {
    @SpyBean
    private ProductMapper mapper;

    @Test
    void createReadUpdateDeleteAndCacheInvalidation() {
        String token = login("admin");
        ProductRequest original = new ProductBuilder().build();
        var created = create(token, original);
        String path = "/api/products/" + created.id();
        var read = request(HttpMethod.GET, path, token, null, ProductResponse.class);
        assertThat(read.getBody().name()).isEqualTo(original.name());
        assertThat(read.getBody().createdAt()).isNotNull();
        var replacement = new ProductBuilder().stock(0).price(BigDecimal.ONE).build();
        var updated = request(HttpMethod.PUT, path, token, replacement, ProductResponse.class);
        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody().name()).isEqualTo(replacement.name());
        assertThat(updated.getBody().createdAt()).isEqualTo(read.getBody().createdAt());
        assertThat(request(HttpMethod.GET, path, token, null, ProductResponse.class).getBody().stock()).isZero();
        assertThat(request(HttpMethod.DELETE, path, token, null, Void.class).getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(request(HttpMethod.GET, path, token, null, ErrorResponse.class).getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void validationReturnsFieldErrors() {
        var invalid = new ProductBuilder().name("").stock(-1).price(BigDecimal.ZERO).build();
        var response = request(HttpMethod.POST, "/api/products", login("admin"), invalid, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().details()).containsKeys("name", "price", "stock");
    }

    @Test
    void missingProductReturnsNotFound() {
        String token = login("admin");
        var created = create(token);
        String path = "/api/products/" + created.id();
        request(HttpMethod.DELETE, path, token, null, Void.class);
        assertThat(request(HttpMethod.GET, path, token, null, ErrorResponse.class).getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void concurrentHttpUpdatesReturnOneSuccessAndOneConflict() throws Exception {
        String token = login("admin");
        var created = create(token);
        var barrier = new CyclicBarrier(2);
        // Both real HTTP transactions must read the same DB version before either writes.
        doAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            if (created.id().equals(product.getId())) barrier.await(15, TimeUnit.SECONDS);
            return invocation.callRealMethod();
        }).when(mapper).updateEntity(any(Product.class), any(ProductRequest.class));
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            var first = executor.submit(() -> request(HttpMethod.PUT, "/api/products/" + created.id(),
                    token, new ProductBuilder().build(), String.class).getStatusCode());
            var second = executor.submit(() -> request(HttpMethod.PUT, "/api/products/" + created.id(),
                    token, new ProductBuilder().build(), String.class).getStatusCode());
            assertThat(java.util.List.of(first.get(30, TimeUnit.SECONDS), second.get(30, TimeUnit.SECONDS)))
                    .containsExactlyInAnyOrder(HttpStatus.OK, HttpStatus.CONFLICT);
        } finally {
            executor.shutdownNow();
        }
    }
}
