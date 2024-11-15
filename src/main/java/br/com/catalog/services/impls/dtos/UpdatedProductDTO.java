package br.com.catalog.services.impls.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

import static br.com.catalog.services.impls.messages.ValidationMessages.*;

public record UpdatedProductDTO(
        @NotEmpty(message = NAME_NOT_EMPTY)
        String name,
        String description,
        @DecimalMin(value = "0.0", inclusive = false, message = PRICE_MIN)
        BigDecimal price,
        int stockQuantity
) {
}
