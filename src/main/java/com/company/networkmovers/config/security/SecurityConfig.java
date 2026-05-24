package com.company.networkmovers.config.security;

import com.company.networkmovers.security.jwt.JwtFilter;
import com.company.networkmovers.security.handler.AccessDeniedHandler;
import com.company.networkmovers.security.handler.AuthenticationEntryPoint;
import com.company.networkmovers.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(@org.springframework.context.annotation.Lazy JwtFilter jwtFilter,
                          AccessDeniedHandler accessDeniedHandler,
                          AuthenticationEntryPoint authenticationEntryPoint,
                          CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            )
            .authorizeHttpRequests(auth -> auth
                // public API endpoints
                .requestMatchers("/api/v1/auth/**", "/api/v1/public/**").permitAll()
                // OpenAPI / Swagger endpoints (allow anonymous access)
                .requestMatchers(
                    // non-prefixed
                    "/v3/api-docs/**",
                    "/v3/api-docs",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-ui/index.html",
                    "/swagger-resources/**",
                    "/webjars/**",
                    // context-path prefixed (when server.servlet.context-path is set)
                    "/api/v1/v3/api-docs/**",
                    "/api/v1/v3/api-docs",
                    "/api/v1/swagger-ui/**",
                    "/api/v1/swagger-ui.html",
                    "/api/v1/swagger-ui/index.html",
                    "/api/v1/swagger-resources/**",
                    "/api/v1/webjars/**"
                ).permitAll()
                // Actuator monitoring endpoints (health/info are open for anonymous access)
                .requestMatchers(
                    "/actuator/health",
                    "/actuator/info",
                    "/actuator/prometheus",
                    "/api/v1/actuator/health",
                    "/api/v1/actuator/info",
                    "/api/v1/actuator/prometheus"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
