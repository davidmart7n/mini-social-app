package com.minisocial.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI miniSocialOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MiniSocial API")
                        .description("API de prueba y demo de MiniSocial con arquitectura hexagonal")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("David")
                                .email("tu-email@ejemplo.com")
                                .url("https://github.com/tu-usuario"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub del proyecto")
                        .url("https://github.com/tu-usuario/MiniSocial"));
    }

    @Bean
    public GroupedOpenApi minisocialApi() {
        return GroupedOpenApi.builder()
                .group("minisocial")
                .packagesToScan("com.minisocial.infrastructure.web") // aquí van tus controllers
                .build();
    }
}
