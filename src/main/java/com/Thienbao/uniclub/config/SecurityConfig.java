package com.Thienbao.uniclub.config;

import com.Thienbao.uniclub.filter.CustomFilterSecurity;
import com.Thienbao.uniclub.security.CustomAuthenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager (HttpSecurity http, CustomAuthenProvider customAuthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,CustomFilterSecurity customFilterSecurity, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors ->cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(author -> {
                    author.requestMatchers(HttpMethod.POST,"/product").hasRole("ADMIN");
                    author.requestMatchers(HttpMethod.POST,"/category","/tag").hasRole("ADMIN");
                    author.requestMatchers(HttpMethod.PUT,"/product/update").hasRole("ADMIN");
                    author.requestMatchers("/auth/**","/file/**").permitAll();
                    author.requestMatchers(HttpMethod.GET,"/product","/category/all","/tag/all","/color/all").permitAll();
                    author.anyRequest().authenticated();
                })
                .addFilterBefore(customFilterSecurity, UsernamePasswordAuthenticationFilter.class)
                .build();
    };
}