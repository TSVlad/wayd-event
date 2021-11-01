package com.example.waydevent.config.security;

import com.example.waydevent.config.security.JwtAuthenticationManager;
import com.example.waydevent.config.security.JwtServerAuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@EnableWebFluxSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtAuthenticationManager jwtAuthenticationManager;
    private JwtServerAuthenticationConverter jwtServerAuthenticationConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/event").hasAnyRole("PERSON", "ORGANIZATION")
                .pathMatchers(HttpMethod.POST, "/event-category/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/event-category/**").hasRole("ADMIN")
                .anyExchange().permitAll()
                .and().addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public AuthenticationWebFilter authenticationWebFilter() {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(jwtServerAuthenticationConverter);
        return authenticationWebFilter;
    }
}
