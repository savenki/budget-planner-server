package com.atmakuruit.budgetPlanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev") 
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/budgetPlanner/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
