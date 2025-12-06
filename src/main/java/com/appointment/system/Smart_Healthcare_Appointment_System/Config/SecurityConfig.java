package com.appointment.system.Smart_Healthcare_Appointment_System.Config;

import com.appointment.system.Smart_Healthcare_Appointment_System.Model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Import needed for stateless
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 1. Disable CSRF (standard for token-based APIs)
                .csrf(csrf -> csrf.disable())

                // 2. Configure session management to be stateless (essential for JWT/Bearer Tokens)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Define authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all methods (GET/POST/PUT/DELETE) for these paths without authentication
                        .requestMatchers("/api/users/**", "/api/patients/**", "/api/doctors/**", "/api/specializations/**").permitAll()

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}