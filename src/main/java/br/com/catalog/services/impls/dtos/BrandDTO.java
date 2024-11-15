package br.com.catalog.services.impls.dtos;

import br.com.catalog.services.impls.messages.ValidationMessages;
import jakarta.validation.constraints.NotEmpty;

public record BrandDTO(
        @NotEmpty(message = ValidationMessages.NAME_NOT_EMPTY) String name) {
}
