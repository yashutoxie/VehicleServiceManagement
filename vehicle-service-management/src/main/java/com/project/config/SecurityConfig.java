package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/customers/**", "/api/vehicles/**", "/api/representatives/**").hasRole("ADMIN")
                .requestMatchers("/api/records/**", "/api/bom/**").hasAnyRole("ADMIN", "ADVISOR")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Basic Auth login box

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        // Plaintext passwords (for testing only)
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}