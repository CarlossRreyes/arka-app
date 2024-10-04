package com.arka.app_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arka.app_services.config.filters.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
        
    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
            .csrf( csrf -> csrf.disable())
            .authorizeHttpRequests((auth) -> auth                
                .requestMatchers("/administration/category/client/**").permitAll()
                .requestMatchers("/administration/product/client/**").permitAll()
                .requestMatchers("/administration/package/client/**").permitAll()
                .requestMatchers("/administration/business/client/**").permitAll()
                .requestMatchers("/administration/package/**").hasAnyAuthority("READ_PRIVILEGE", "WRITE_PRIVILEGE")
                .requestMatchers("/administration/product/**").hasAnyAuthority("READ_PRIVILEGE", "WRITE_PRIVILEGE")
                .requestMatchers("/administration/category/**").hasAnyAuthority("READ_PRIVILEGE", "WRITE_PRIVILEGE")
                .requestMatchers("/administration/seed/**").permitAll()
                .requestMatchers("/auth/access/**").permitAll()                                            

                .anyRequest()
                .authenticated()
            )
            .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider( authenticationProvider )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        
    }

}