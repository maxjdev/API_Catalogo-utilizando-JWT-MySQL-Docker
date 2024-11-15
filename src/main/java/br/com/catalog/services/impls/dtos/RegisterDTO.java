package br.com.catalog.services.impls.dtos;

import br.com.catalog.models.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;

import static br.com.catalog.services.impls.messages.ValidationMessages.*;

public record RegisterDTO(@NotEmpty(message = LOGIN_NOT_EMPTY) String login,
                          @NotEmpty(message = PASSWORD_NOT_EMPTY) String password,
                          UserRole role) {
}
