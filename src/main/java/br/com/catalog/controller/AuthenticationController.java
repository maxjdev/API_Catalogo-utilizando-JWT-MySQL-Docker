package br.com.catalog.controller;

import br.com.catalog.controller.responses.Response;
import br.com.catalog.services.IAuthenticationService;
import br.com.catalog.services.impls.AuthenticationServiceImpl;
import br.com.catalog.services.impls.dtos.AuthenticationDTO;
import br.com.catalog.services.impls.dtos.RegisterDTO;
import br.com.catalog.services.impls.dtos.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final IAuthenticationService<AuthenticationDTO, UserResponseDTO, RegisterDTO> authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationService = authenticationServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody @Valid AuthenticationDTO dto) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.OK.toString(),
                "User logged in successfully",
                authenticationService.login(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<Response<UserResponseDTO>> register(@RequestBody @Valid RegisterDTO dto) {
        return ResponseEntity.ok(new Response<>(
                HttpStatus.CREATED.toString(),
                "User created successfully",
                authenticationService.register(dto)));
    }
}
