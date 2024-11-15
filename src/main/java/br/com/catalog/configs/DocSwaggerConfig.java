package br.com.catalog.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocSwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gerenciamento de produtos")
                        .description("CRUD de produtos e marcas + operações de domínio")
                        .version("v1")
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact().name("maxjdev").email("maxjramosdev@gmail.com").url("https://www.linkedin.com/in/maxjdev/")));
    }
}
