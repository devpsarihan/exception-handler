package com.exception_handler.persistence.entity;

import com.exception_handler.model.enumeration.ProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "PRODUCT", name = "PRODUCT")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    @Column(name = "SKU", updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
    private UUID sku;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CATEGORY_ID", nullable = false)
    private int categoryId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS", nullable = false)
    private ProductStatus status;

    @PrePersist
    protected void onCreate() {
        if (sku == null) {
            sku = UUID.randomUUID();
        }
    }
}
