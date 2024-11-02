package com.example.spring_timesheet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/projects/**").hasAuthority(Role.ADMIN.getName())
                        .requestMatchers("/projects/**").hasAuthority("admin")
//                        .requestMatchers("/timesheets/**").hasAnyAuthority(Role.USER.getName(),Role.ADMIN .getName())
                        .requestMatchers("/timesheets/**").hasAnyAuthority("admin","user")
                        .anyRequest().authenticated()  )
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
