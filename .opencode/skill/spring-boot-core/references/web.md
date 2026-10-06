# Web/REST Reference

## When to load

Load when implementing controllers, DTOs, validation, or global exception handling.

## Controller

```java
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> search(@RequestParam(defaultValue = "") String name) {
        return service.search(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return service.create(request);
    }
}
```

## DTO (records)

```java
public record ProductRequest(
        @NotBlank String name,
        @DecimalMin("0.0") BigDecimal price) {}

public record ProductResponse(Long id, String name, BigDecimal price) {}
```

## Global Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fe -> fe.getDefaultMessage() == null ? "invalid" : fe.getDefaultMessage()));
        return new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(ResourceNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Map.of());
    }
}
```

## ApiError

```java
public record ApiError(int status, String message, Map<String, String> errors) {}
```

## Status Codes

| Operation | Success | Errors |
|---|---|---|
| Create | 201 | 400, 409 |
| Read | 200 | 404 |
| Update | 200 | 400, 404 |
| Delete | 204 | 404 |
