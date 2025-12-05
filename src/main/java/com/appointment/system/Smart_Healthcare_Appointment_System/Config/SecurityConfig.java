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
                        // Allow POST requests for user registration/login without authentication
                        .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/patients/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/patients/**").permitAll()

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                );

        // NOTE: We have removed .httpBasic() here.
        // The actual token validation filter (e.g., JWT filter) will need to be added separately
        // but this setup correctly disables sessions, which is the first step.

        return http.build();
    }

}