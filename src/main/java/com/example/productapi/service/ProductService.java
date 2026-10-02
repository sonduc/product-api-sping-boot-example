package com.example.productapi.service;

import com.example.productapi.dto.ProductFilter;
import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.domain.Product;
import com.example.productapi.exception.ResourceNotFoundException;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.repository.spec.ProductSpecification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getAll(Pageable pageable, ProductFilter filter) {
        return productRepository.findAll(ProductSpecification.build(filter), pageable)
                .map(productMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getById(Long id) {
        return productMapper.toResponse(findProduct(id));
    }

    // Runtime failures, including optimistic conflicts, roll back writes. External side effects
    // should use an outbox or AFTER_COMMIT event, rather than execute before commit.
    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse create(ProductRequest request) {
        return productMapper.toResponse(productRepository.saveAndFlush(productMapper.toEntity(request)));
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findProduct(id);
        productMapper.updateEntity(product, request);
        return productMapper.toResponse(productRepository.saveAndFlush(product));
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public void delete(Long id) {
        productRepository.delete(findProduct(id));
        productRepository.flush();
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
    }
}
