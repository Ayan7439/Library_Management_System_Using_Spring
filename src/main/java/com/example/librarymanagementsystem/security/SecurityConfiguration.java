package com.example.librarymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**").hasAuthority("ROLE_USER") // ✅ Fix role names
                .requestMatchers("/employee/**").hasAuthority("ROLE_EMPLOYEE")
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/login/**", "/register/**", "/logout/**").permitAll()
                .requestMatchers("/CSS/**", "/Images/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            // .formLogin(form -> form
            //     .loginPage("/login")
            //     .permitAll()
            // )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/redirectByRole", true)
                .permitAll()
            )

            .csrf(csrf -> csrf.disable()) // ✅ Fix CSRF ignoring issue
            .headers(headers -> headers.frameOptions().disable()); // ✅ Fix frame options

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
