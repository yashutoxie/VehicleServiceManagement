package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User
            .withUsername("admin")
            .password("admin123")
            .roles("ADMIN")
            .build());

        manager.createUser(User
            .withUsername("advisor")
            .password("advisor123")
            .roles("ADVISOR")
            .build());

        return manager;
    }
}