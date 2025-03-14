package com.exception_handler.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.exception_handler.configuration.advice.ErrorCode;
import com.exception_handler.configuration.advice.exception.ProductApiException;
import com.exception_handler.controller.v1.request.CreateProductRequest;
import com.exception_handler.controller.v1.request.UpdateProductRequest;
import com.exception_handler.integration.TestContainersConfiguration;
import com.exception_handler.model.enumeration.Category;
import com.exception_handler.model.enumeration.ProductStatus;
import com.exception_handler.persistence.ProductRepository;
import com.exception_handler.service.ProductService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ProductServiceIntegrationTest extends TestContainersConfiguration {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private CreateProductRequest createProductRequest;
    private UpdateProductRequest updateProductRequest;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();

        createProductRequest = CreateProductRequest.builder()
            .name("Test Product")
            .categoryId(Category.TECHNOLOGY.getCode())
            .description("Test Description")
            .price(new BigDecimal(100.00))
            .quantity(10)
            .build();

        updateProductRequest = UpdateProductRequest.builder()
            .name("Updated Product")
            .categoryId(Category.TECHNOLOGY.getCode())
            .description("Updated Description")
            .price(new BigDecimal(150.00))
            .quantity(20)
            .status(ProductStatus.ACTIVE.getCode())
            .build();
    }

    @Test
    public void testCreateProduct_WhenInvalidCreateProductRequest_ShouldThrowProductAlreadyCreatedException() {
        productService.createProduct(createProductRequest);
        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            productService.createProduct(createProductRequest);
        });

        assertEquals(ErrorCode.PRODUCT_ALREADY_CREATED.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testGetProduct_WhenInvalidProductId_ShouldThrowProductNotFoundException() {
        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            productService.getProduct(999L);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND_ERROR.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testUpdateProduct_WhenInvalidUpdateProductRequest_ShouldThrowProductCannotUpdateException() {
        Long productId = productService.createProduct(createProductRequest);
        productService.updateProduct(productId, UpdateProductRequest.builder()
            .name("Updated Product")
            .categoryId(Category.TECHNOLOGY.getCode())
            .description("Updated Description")
            .price(new BigDecimal("150.00"))
            .quantity(20)
            .status(ProductStatus.ON_SALE.getCode()).build());

        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            productService.updateProduct(productId, updateProductRequest);
        });

        assertEquals(ErrorCode.PRODUCT_CANNOT_UPDATE.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testUpdateProduct_WhenInvalidUpdateProductRequest_ShouldThrowProductNotFoundException() {
        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            productService.updateProduct(999L, updateProductRequest);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND_ERROR.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testCategoryNotFound_WhenInvalidCategoryId_ShouldThrowCategoryNotFoundException() {
        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            Category.from(999);
        });

        assertEquals(ErrorCode.CATEGORY_NOT_FOUND.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testProductStatusNotFound_WhenInvalidProductStatus_ShouldThrowProductNotFoundException() {
        ProductApiException exception = assertThrows(ProductApiException.class, () -> {
            ProductStatus.from(999);
        });

        assertEquals(ErrorCode.PRODUCT_STATUS_NOT_FOUND.getCode(), exception.getCode());
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), exception.getHttpStatusCode());
    }

    @Test
    public void testUnknownError() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Unknown error");
        });

        assertEquals("Unknown error", exception.getMessage());
    }
}