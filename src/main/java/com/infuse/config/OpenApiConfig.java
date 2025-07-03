package com.infuse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI creditConsultationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Credit Consultation API")
                        .description("API for querying constituted credits based on NFS-e and credit numbers")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Infuse Technology")
                                .email("contato@infuse.com.br"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development server"),
                        new Server()
                                .url("https://api.infuse.com.br")
                                .description("Production server")));
    }
}