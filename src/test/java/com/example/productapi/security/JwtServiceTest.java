package com.example.productapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import static org.assertj.core.api.Assertions.*;

class JwtServiceTest {
    private final byte[] key = new byte[32];
    private final String secret = Base64.getEncoder().encodeToString(key);
    private final JwtService service = new JwtService(secret, 3600000);

    @Test
    void generatesAndValidatesIdentityRoleAndExpiration() {
        var user = User.withUsername("tester").password("unused").roles("ADMIN").build();
        String token = service.generateToken(user.getUsername(), "ADMIN");
        assertThat(service.extractUsername(token)).isEqualTo(user.getUsername());
        assertThat(service.extractRole(token)).isEqualTo("ADMIN");
        assertThat(service.validateToken(token, user)).isTrue();
        assertThat(service.getExpirationMs()).isEqualTo(3600000);
        assertThat(service.validateToken(token,
                User.withUsername("other").password("unused").roles("ADMIN").build())).isFalse();
        assertThat(service.validateToken(token,
                User.withUsername("tester").password("unused").roles("USER").build())).isFalse();
    }

    @Test
    void rejectsExpiredTokenWithoutSleeping() {
        String expired = Jwts.builder().subject("tester").claim("role", "ADMIN")
                .expiration(Date.from(Instant.now().minusSeconds(60)))
                .signWith(Keys.hmacShaKeyFor(key)).compact();
        assertThatThrownBy(() -> service.extractUsername(expired)).isInstanceOf(ExpiredJwtException.class);
    }

    @Test
    void rejectsMalformedAndWronglySignedTokens() {
        assertThatThrownBy(() -> service.extractUsername("malformed")).isInstanceOf(JwtException.class);
        byte[] otherKey = key.clone();
        otherKey[0] = 1;
        String wrong = Jwts.builder().subject("tester").signWith(Keys.hmacShaKeyFor(otherKey)).compact();
        assertThatThrownBy(() -> service.extractUsername(wrong)).isInstanceOf(JwtException.class);
    }

    @Test
    void rejectsMissingRoleAndInvalidConfiguration() {
        String token = Jwts.builder().subject("tester").signWith(Keys.hmacShaKeyFor(key)).compact();
        assertThat(service.validateToken(token,
                User.withUsername("tester").password("unused").roles("ADMIN").build())).isFalse();
        assertThatIllegalArgumentException().isThrownBy(() -> new JwtService(secret, 0));
        assertThatThrownBy(() -> new JwtService(Base64.getEncoder().encodeToString(new byte[8]), 1000))
                .isInstanceOf(JwtException.class);
    }
}
