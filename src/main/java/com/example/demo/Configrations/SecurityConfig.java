package com.example.demo.Configrations;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//@Configuration
//public class SecurityConfig  {
//
//    @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> {
//                            try {
//                                requests
//                                        .anyRequest().permitAll()
//                                        .and().cors().disable()
//                                        .csrf().disable();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//        return http.build();
//    }
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/user/signup").permitAll() // Allow access to /user/signup without authentication
//                .anyRequest().authenticated() // Require authentication for all other requests
//                .and()
//                .formLogin(); // Enable form-based login
//
//        return http.build();
//    }


