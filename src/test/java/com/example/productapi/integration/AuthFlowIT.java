package com.example.productapi.integration;

import com.example.productapi.dto.AuthRequest;
import com.example.productapi.dto.AuthResponse;
import com.example.productapi.exception.ErrorResponse;
import com.example.productapi.support.ProductBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import static org.assertj.core.api.Assertions.assertThat;

class AuthFlowIT extends BaseIntegrationTest {
    @Test
    void adminLoginReturnsTokenAndIdentity() {
        var result = http.postForEntity("/api/auth/login",
                new AuthRequest("admin", "password"), AuthResponse.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().token()).isNotBlank();
        assertThat(result.getBody().username()).isEqualTo("admin");
        assertThat(result.getBody().role()).isEqualTo("ADMIN");
        assertThat(result.getBody().expiresIn()).isPositive();
    }

    @Test
    void wrongPasswordAndUnknownUserReturnUnauthorized() {
        for (var credentials : new AuthRequest[]{new AuthRequest("admin", "incorrect"),
                new AuthRequest("missing-account", "incorrect")}) {
            var result = http.postForEntity("/api/auth/login", credentials, ErrorResponse.class);
            assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
            assertThat(result.getBody().message()).isEqualTo("Invalid credentials");
        }
    }

    @Test
    void anonymousAndMalformedTokensCannotReadProducts() {
        for (String token : new String[]{null, "malformed-token"}) {
            assertThat(request(HttpMethod.GET, "/api/products", token, null, ErrorResponse.class)
                    .getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        }
    }

    @Test
    void userCanReadButCannotWrite() {
        var product = create(login("admin"));
        String token = login("user");
        assertThat(request(HttpMethod.GET, "/api/products", token, null, String.class)
                .getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(request(HttpMethod.GET, "/api/products/" + product.id(), token, null, String.class)
                .getStatusCode()).isEqualTo(HttpStatus.OK);
        var body = new ProductBuilder().build();
        assertThat(request(HttpMethod.POST, "/api/products", token, body, ErrorResponse.class)
                .getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(request(HttpMethod.PUT, "/api/products/" + product.id(), token, body, ErrorResponse.class)
                .getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(request(HttpMethod.DELETE, "/api/products/" + product.id(), token, null, ErrorResponse.class)
                .getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void adminCanAccessIdentityAndAllProductOperations() {
        String token = login("admin");
        var product = create(token);
        assertThat(request(HttpMethod.GET, "/api/auth/me", token, null, String.class)
                .getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(request(HttpMethod.GET, "/api/products", token, null, String.class)
                .getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(request(HttpMethod.GET, "/api/products/" + product.id(), token, null, String.class)
                .getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(request(HttpMethod.PUT, "/api/products/" + product.id(), token,
                new ProductBuilder().build(), String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(request(HttpMethod.DELETE, "/api/products/" + product.id(), token, null, Void.class)
                .getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
