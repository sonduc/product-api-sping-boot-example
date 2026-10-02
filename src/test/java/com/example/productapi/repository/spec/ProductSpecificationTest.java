package com.example.productapi.repository.spec;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductFilter;
import com.example.productapi.exception.BusinessException;
import jakarta.persistence.criteria.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductSpecificationTest {
    @Test
    void blankNameAndNullFilterAddNoRestrictions() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        when(cb.conjunction()).thenReturn(predicate);
        assertThat(ProductSpecification.hasName(" ").toPredicate(null, null, cb)).isSameAs(predicate);
        assertThat(ProductSpecification.build(null).toPredicate(null, null, cb)).isSameAs(predicate);
    }

    @Test
    @SuppressWarnings("unchecked")
    void nameUsesLowercaseAndEscapesLiteralWildcards() {
        Root<Product> root = mock(Root.class);
        Path<String> path = mock(Path.class);
        Expression<String> lower = mock(Expression.class);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        when(root.<String>get("name")).thenReturn(path);
        when(cb.lower(path)).thenReturn(lower);
        ProductSpecification.hasName("  PHONE%_!  ").toPredicate(root, null, cb);
        verify(cb).like(lower, "%phone!%!_!!%", '!');
    }

    @Test
    @SuppressWarnings("unchecked")
    void stockSupportsTrueFalseAndAbsent() {
        Root<Product> root = mock(Root.class);
        Path<Integer> stock = mock(Path.class);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        when(root.<Integer>get("stock")).thenReturn(stock);
        ProductSpecification.inStock(true).toPredicate(root, null, cb);
        ProductSpecification.inStock(false).toPredicate(root, null, cb);
        ProductSpecification.inStock(null).toPredicate(root, null, cb);
        verify(cb).greaterThan(stock, 0);
        verify(cb).equal(stock, 0);
        verify(cb).conjunction();
    }

    @Test
    void rejectsNegativeAndReversedPriceRanges() {
        assertThatThrownBy(() -> ProductSpecification.priceBetween(BigDecimal.ONE.negate(), null))
                .isInstanceOf(BusinessException.class);
        assertThatThrownBy(() -> ProductSpecification.priceBetween(null, BigDecimal.ONE.negate()))
                .isInstanceOf(BusinessException.class);
        assertThatThrownBy(() -> ProductSpecification.build(
                new ProductFilter(null, BigDecimal.TEN, BigDecimal.ONE, null)))
                .isInstanceOf(BusinessException.class);
    }
}
