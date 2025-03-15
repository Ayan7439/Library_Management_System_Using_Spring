// package com.example.librarymanagementsystem.security;

// import jakarta.sql.DataSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.provisioning.JdbcUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfiguration {

//     private final DataSource dataSource;

//     public SecurityConfiguration(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return new JdbcUserDetailsManager(dataSource);
//     }

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/user/**").hasRole("USER")
//                 .requestMatchers("/employee/**").hasRole("EMPLOYEE")
//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/login/**", "/register/**", "/logout/**").permitAll()
//                 .requestMatchers("/CSS/**", "/Images/**").permitAll()
//                 .requestMatchers("/h2-console/**").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/login")
//                 .permitAll()
//             )
//             .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Disable CSRF for H2 Console
//             .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Disable X-Frame-Options for H2 Console

//         return http.build();
//     }
// }






package com.example.librarymanagementsystem.security;

import javax.sql.DataSource; // ✅ Fix import

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    @Primary
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
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()) // ✅ Fix CSRF ignoring issue
            .headers(headers -> headers.frameOptions().disable()); // ✅ Fix frame options

        return http.build();
    }
}
