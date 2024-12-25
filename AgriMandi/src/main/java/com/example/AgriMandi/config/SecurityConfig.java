package com.example.AgriMandi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity in testing
            .authorizeRequests()
            .requestMatchers("/api/users/**").permitAll() // Allow public access to user endpoints
            .requestMatchers("/api/products/**").permitAll() // Allow public access to product endpoints
            .requestMatchers("/api/auth/login").permitAll()
            .anyRequest().authenticated() // Protect other endpoints
            .and()
            .httpBasic(); // Use HTTP Basic Authentication for simplicity

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow all origins (use specific domains in production)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
