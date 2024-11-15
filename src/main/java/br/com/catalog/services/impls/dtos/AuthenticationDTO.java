package br.com.catalog.services.impls.dtos;

import jakarta.validation.constraints.NotEmpty;

import static br.com.catalog.services.impls.messages.ValidationMessages.*;

public record AuthenticationDTO(@NotEmpty(message = LOGIN_NOT_EMPTY) String login,
                                @NotEmpty(message = PASSWORD_NOT_EMPTY) String password) {
}
