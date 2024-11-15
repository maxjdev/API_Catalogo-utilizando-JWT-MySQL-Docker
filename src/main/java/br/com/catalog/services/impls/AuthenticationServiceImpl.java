package br.com.catalog.services.impls;

import br.com.catalog.exceptions.business_exceptions.UserAlreadyExistsException;
import br.com.catalog.models.User;
import br.com.catalog.models.enums.UserRole;
import br.com.catalog.repositories.IUserRepository;
import br.com.catalog.security.TokenService;
import br.com.catalog.services.IAuthenticationService;
import br.com.catalog.services.impls.dtos.AuthenticationDTO;
import br.com.catalog.services.impls.dtos.RegisterDTO;
import br.com.catalog.services.impls.dtos.UserResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService<AuthenticationDTO, UserResponseDTO, RegisterDTO> {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final IUserRepository userRepository;
    private final Function<User, UserResponseDTO> userToResponse;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     TokenService tokenService,
                                     IUserRepository userRepository,
                                     Function<User, UserResponseDTO> userToResponse) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userToResponse = userToResponse;
    }

    @Override
    public String login(AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((User) auth.getPrincipal());
    }

    @Override
    public UserResponseDTO register(RegisterDTO dto){
        if (userRepository.existsByLogin(dto.login()))
            throw new UserAlreadyExistsException("Usuario já existe");

        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var newUser = User.builder()
                .login(dto.login())
                .password(encryptedPassword)
                .role(dto.role() != null ? dto.role() : UserRole.USER)
                .build();
        var save = userRepository.save(newUser);

        return userToResponse.apply(save);
    }
}
