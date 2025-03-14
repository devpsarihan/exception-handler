package com.exception_handler.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String sku;
    private String name;
    private Integer categoryId;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer status;
    private Long createdDate;
    private Long modifiedDate;
}
