package br.com.catalog.services.impls.dtos;

import static br.com.catalog.services.impls.messages.ValidationMessages.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(
        @NotEmpty(message = NAME_NOT_EMPTY)
        String name,
        @NotEmpty(message = BRAND_NOT_EMPTY)
        String brand,
        @NotEmpty(message = DESCRIPTION_NOT_EMPTY)
        String description,
        @DecimalMin(value = "0.0", inclusive = false, message = PRICE_MIN)
        BigDecimal price,
        @Min(value = 0,  message = STOCK_MIN)
        int stockQuanitity
) {
}
