package com.company.networkmovers.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "oauth2Password";

        return new OpenAPI()
                .info(new Info()
                        .title("Network Movers API")
                        .version("v1")
                        .description("API documentation for Network Movers")
                        .contact(new Contact().name("Network Movers Team").email("uzairanwar2299@gmail.com"))
                )
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                                .password(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                        .tokenUrl("/api/v1/auth/login")
                                                )
                                        )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((path, pathItem) -> {
                    if (path.contains("/auth/") || path.contains("/public/")) {
                        pathItem.readOperations().forEach(operation -> 
                            operation.setSecurity(Collections.emptyList())
                        );
                    }
                });
            }
        };
    }
}

