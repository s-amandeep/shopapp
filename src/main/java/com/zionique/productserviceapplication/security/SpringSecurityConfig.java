package com.zionique.productserviceapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/products").hasAnyAuthority("ADMIN")
                                .requestMatchers("/products/**").authenticated()
//                        .requestMatchers("/messages/**").access(hasScope("message:read"))
                        .anyRequest().permitAll()       // allow anyone to access any uri without login
//                        .anyRequest().authenticated()    // only allow person who has logged in to access any uri
                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
                        )
                );
        return http.build();
    }
}