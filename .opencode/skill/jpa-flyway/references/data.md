# JPA Patterns Reference

## When to load

Load for entity design, repository queries, N+1 prevention, transactions, and fetch strategies.

## Entity

```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @DecimalMin("0.0")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
```

## Repository

```java
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "category")
    Optional<Product> findWithCategoryById(Long id);

    List<Product> findByNameContainingIgnoreCase(String name);
}
```

## N+1 Prevention

- Default to LAZY for `@ManyToOne` / `@OneToOne`.
- Use `JOIN FETCH` or `@EntityGraph` when eager loading is needed.
- Detect by logging SQL and counting queries.

```java
@Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.name LIKE %:name%")
List<Product> searchWithCategory(@Param("name") String name);
```

## Transactions

- Reads: `@Transactional(readOnly = true)`
- Writes: `@Transactional` (multi-step)

## LazyInitializationException

Cause: accessing a lazy field outside the transaction.
Fix: `JOIN FETCH`, `@EntityGraph`, or DTO projection.
