package br.com.catalog;

import br.com.catalog.models.enums.UserRole;
import br.com.catalog.services.IAuthenticationService;
import br.com.catalog.services.impls.dtos.AuthenticationDTO;
import br.com.catalog.services.impls.dtos.RegisterDTO;
import br.com.catalog.services.impls.dtos.UserResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner {
	private final IAuthenticationService<AuthenticationDTO, UserResponseDTO, RegisterDTO> authenticationService;

    public CatalogApplication(IAuthenticationService<AuthenticationDTO, UserResponseDTO, RegisterDTO> authenticationService) {
        this.authenticationService = authenticationService;
    }

    public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		authenticationService.register(new RegisterDTO("useradmin", "passwordadmin", UserRole.ADMIN));
		authenticationService.register(new RegisterDTO("userstandard", "passwordstandard", UserRole.USER));
	}
}
