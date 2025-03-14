package com.exception_handler.controller.v1.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

    @NotEmpty(message = "Product name cannot be empty")
    @Size(max = 25, message = "Product name cannot exceed 25 characters")
    @Pattern(regexp = "^[^*?\\[\\]{}()|$^+\\-@!\\\\]+$", message = "Product name contains invalid characters")
    private String name;

    @NotNull(message = "Product category cannot be null")
    private Integer categoryId;

    @NotEmpty(message = "Product description cannot be empty")
    @Size(max = 200, message = "Product name cannot exceed 25 characters")
    @Pattern(regexp = "^[^*?\\[\\]{}()|$^+\\-@!\\\\]+$", message = "Product name contains invalid characters")
    private String description;

    @NotNull(message = "Product price cannot be null")
    @DecimalMin(value = "10.00", message = "Product price must be greater than 0")
    @DecimalMax(value = "100000.00", message = "Product price must be less than 100000.00")
    private BigDecimal price;

    @NotNull(message = "Product quantity cannot be null")
    @Min(value = 5, message = "Product quantity must be at least 0")
    @Max(value = 100, message = "Product quantity must be less than or equal to 100")
    private Integer quantity;

    @NotNull(message = "Product status cannot be null")
    private Integer status;
}
