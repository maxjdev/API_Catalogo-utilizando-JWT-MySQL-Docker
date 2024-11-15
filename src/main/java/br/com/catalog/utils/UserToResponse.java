package br.com.catalog.utils;

import br.com.catalog.models.User;
import br.com.catalog.services.impls.dtos.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToResponse implements Function<User, UserResponseDTO> {
    @Override
    public UserResponseDTO apply(User user) {
        return new UserResponseDTO(user.getUsername(), user.getRole().toString());
    }
}
