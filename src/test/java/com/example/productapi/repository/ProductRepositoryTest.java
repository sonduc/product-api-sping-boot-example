package com.example.productapi.repository;

import com.example.productapi.domain.Product;
import com.example.productapi.dto.ProductFilter;
import com.example.productapi.repository.spec.ProductSpecification;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest(properties = {"spring.flyway.enabled=false", "spring.jpa.hibernate.ddl-auto=create-drop"})
class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private Product save(String name, String price, int stock) {
        return repository.saveAndFlush(new Product(name, null, new BigDecimal(price), stock));
    }

    @Test
    void combinesCaseInsensitiveNameInclusivePricesAndStockWithPagination() {
        save("Phone Basic", "100.00", 1);
        save("PHONE Pro", "200.00", 2);
        save("Phone Empty", "150.00", 0);
        save("Tablet", "180.00", 5);
        var spec = ProductSpecification.build(new ProductFilter("phone",
                new BigDecimal("100"), new BigDecimal("200"), true));
        var page = repository.findAll(spec, PageRequest.of(0, 1, Sort.by("price").descending()));
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent()).extracting(Product::getName).containsExactly("PHONE Pro");
        assertThat(repository.findAll(ProductSpecification.inStock(false)))
                .extracting(Product::getName).containsExactly("Phone Empty");
    }

    @Test
    void supportsAbsentFiltersOpenBoundsAndLiteralWildcards() {
        save("100% Phone", "100.00", 1);
        save("Phone", "200.00", 0);
        assertThat(repository.findAll(ProductSpecification.build(null))).hasSize(2);
        assertThat(repository.findAll(ProductSpecification.hasName("%")))
                .extracting(Product::getName).containsExactly("100% Phone");
        assertThat(repository.findAll(ProductSpecification.priceBetween(null, new BigDecimal("100"))))
                .hasSize(1);
        assertThat(repository.findAll(ProductSpecification.priceBetween(new BigDecimal("200"), null)))
                .hasSize(1);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void overlappingUpdatesRejectTheSecondWriter() {
        Product saved = save("Original", "100.00", 1);
        var first = entityManagerFactory.createEntityManager();
        var second = entityManagerFactory.createEntityManager();
        try {
            first.getTransaction().begin();
            second.getTransaction().begin();
            Product firstCopy = first.find(Product.class, saved.getId());
            Product secondCopy = second.find(Product.class, saved.getId());
            assertThat(firstCopy.getVersion()).isEqualTo(secondCopy.getVersion());
            firstCopy.setName("First writer");
            secondCopy.setName("Second writer");
            first.getTransaction().commit();
            assertThatThrownBy(second::flush).isInstanceOf(OptimisticLockException.class);
            second.getTransaction().rollback();
            assertThat(repository.findById(saved.getId()).orElseThrow().getName()).isEqualTo("First writer");
        } finally {
            if (first.getTransaction().isActive()) first.getTransaction().rollback();
            if (second.getTransaction().isActive()) second.getTransaction().rollback();
            first.close();
            second.close();
            repository.deleteById(saved.getId());
        }
    }
}
