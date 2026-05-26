package com.company.networkmovers.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String OAUTH_SECURITY_SCHEME = "oauth2Password";
    private static final String BEARER_SECURITY_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UAE Moving System - Core API Services")
                        .version("1.0.0")
                        .description("### Production-grade REST API backend for UAE Moving System\n\n" +
                                "This service acts as the Pricing, Scheduling, Crew Allocation, and Operational Master Data core layer.\n\n" +
                                "#### 🔑 Authentications:\n" +
                                "* **OAuth2 Password Grant Flow (`oauth2Password`)**: Used directly inside Swagger UI to authenticate with credentials.\n" +
                                "* **Bearer Token (`bearerAuth`)**: Standard JSON Web Token (JWT) header authorization for mobile, web, and external clients.\n\n" +
                                "#### 📂 Access Control & Scopes:\n" +
                                "* **Admin APIs (`/api/v1/admin/**`)**: Complete CRUD operations. Restricted to administrative/managerial roles.\n" +
                                "* **Mobile APIs (`/api/v1/mobile/**`)**: Lightweight read-only operations for Drivers, Surveyors, and Mobile Clients.\n" +
                                "* **Public APIs (`/api/v1/public/**` & `/auth/**`)**: Open access endpoints for guest estimation tool, pricing engines, and user registration.")
                        .contact(new Contact()
                                .name("Network Movers Architecture Team")
                                .email("uzairanwar2299@gmail.com")
                                .url("https://github.com/uzair2299"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Local Development Server"),
                        new Server().url("https://network-movers-backend-production.up.railway.app").description("Staging / Production Server")
                ))
                .components(new Components()
                        .addSecuritySchemes(OAUTH_SECURITY_SCHEME,
                                new SecurityScheme()
                                        .name(OAUTH_SECURITY_SCHEME)
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .description("Resource Owner Password Credentials Grant Flow for OAuth2 Authentication")
                                        .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                                .password(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                        .tokenUrl("/api/v1/auth/login")
                                                )
                                        )
                        )
                        .addSecuritySchemes(BEARER_SECURITY_SCHEME,
                                new SecurityScheme()
                                        .name(BEARER_SECURITY_SCHEME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter your Bearer JWT Token to access secured endpoints")
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList(OAUTH_SECURITY_SCHEME)
                        .addList(BEARER_SECURITY_SCHEME));
    }

    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("0-all-apis")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApis() {
        return GroupedOpenApi.builder()
                .group("1-admin-apis")
                .pathsToMatch("/api/v1/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi mobileApis() {
        return GroupedOpenApi.builder()
                .group("2-mobile-apis")
                .pathsToMatch("/api/v1/mobile/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApis() {
        return GroupedOpenApi.builder()
                .group("3-public-apis")
                .pathsToMatch("/api/v1/public/**", "/api/v1/auth/**")
                .build();
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

