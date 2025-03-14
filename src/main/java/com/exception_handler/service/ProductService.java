package com.exception_handler.service;

import com.exception_handler.configuration.advice.ErrorCode;
import com.exception_handler.configuration.advice.exception.ProductApiException;
import com.exception_handler.controller.v1.request.CreateProductRequest;
import com.exception_handler.controller.v1.request.UpdateProductRequest;
import com.exception_handler.model.dto.ProductDto;
import com.exception_handler.model.enumeration.Category;
import com.exception_handler.model.enumeration.ProductStatus;
import com.exception_handler.persistence.ProductRepository;
import com.exception_handler.persistence.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Long createProduct(final CreateProductRequest request) {
        productRepository.findByNameAndCategoryId(request.getName(), Category.from(request.getCategoryId()).getCode())
            .ifPresent(product -> {
                throw new ProductApiException(ErrorCode.PRODUCT_ALREADY_CREATED, request.getName(), request.getCategoryId());
            });

        Product product = Product.builder()
            .name(request.getName())
            .categoryId(Category.from(request.getCategoryId()).getCode())
            .description(request.getDescription())
            .price(request.getPrice())
            .quantity(request.getQuantity())
            .status(ProductStatus.ACTIVE)
            .build();
        return productRepository.save(product).getId();
    }

    public ProductDto getProduct(final Long productId) {
        return productRepository.findById(productId)
            .map(product -> ProductDto.builder()
                .id(product.getId())
                .sku(product.getSku().toString())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getStatus().getCode())
                .build())
            .orElseThrow(() -> new ProductApiException(ErrorCode.PRODUCT_NOT_FOUND_ERROR, productId));
    }

    public void updateProduct(final Long productId, final UpdateProductRequest request) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductApiException(ErrorCode.PRODUCT_NOT_FOUND_ERROR, productId));

        if (ProductStatus.ON_SALE.equals(product.getStatus())) {
            throw new ProductApiException(ErrorCode.PRODUCT_CANNOT_UPDATE, productId);
        }

        product.setName(request.getName());
        product.setCategoryId(Category.from(request.getCategoryId()).getCode());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(ProductStatus.from(request.getStatus()));
        productRepository.save(product);
    }
}
