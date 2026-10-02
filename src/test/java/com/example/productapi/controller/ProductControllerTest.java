package com.example.productapi.controller;

import com.example.productapi.config.SecurityConfig;
import com.example.productapi.config.WebConfig;
import com.example.productapi.security.JwtService;
import com.example.productapi.service.AuthService;
import com.example.productapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductController.class,
        excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class)
@Import({SecurityConfig.class, WebConfig.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthService authService;

    @Test
    void getProductsWithoutTokenReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void optimisticUpdateFailureReturnsConflict() throws Exception {
        when(productService.update(eq(1L), any()))
                .thenThrow(new ObjectOptimisticLockingFailureException(Product.class, 1L));
        mockMvc.perform(put("/api/products/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Phone\",\"price\":100,\"stock\":1}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409));
    }

    @Test
    @WithMockUser(roles = "USER")
    void appliesDefaultPaginationAndStableSort() throws Exception {
        when(productService.getAll(any(Pageable.class), any(ProductFilter.class))).thenReturn(Page.empty());
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
        verify(productService).getAll(argThat(pageable -> pageable.getPageSize() == 10
                && pageable.getSort().getOrderFor("createdAt").isDescending()
                && pageable.getSort().getOrderFor("id").isAscending()), any(ProductFilter.class));
    }

    @Test
    @WithMockUser(roles = "USER")
    void rejectsOversizedPagesAndInvalidSort() throws Exception {
        mockMvc.perform(get("/api/products?size=101")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/products?sort=password,asc")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/products?sort=price,invalid")).andExpect(status().isBadRequest());
        verifyNoInteractions(productService);
    }
}
