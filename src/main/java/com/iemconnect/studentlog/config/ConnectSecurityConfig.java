package com.iemconnect.studentlog.config;

import com.iemconnect.studentlog.security.ConnectJWTAuthEntryPoint;
import com.iemconnect.studentlog.security.ConnectJWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ConnectSecurityConfig {
    @Autowired
    private ConnectJWTAuthEntryPoint connectJWTAuthEntryPoint;

    @Autowired
    private ConnectJWTAuthFilter connectJWTAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.disable())
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/college/*").authenticated()
                        .requestMatchers("/home/**").authenticated()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                    )
                    .exceptionHandling(i -> i.authenticationEntryPoint(connectJWTAuthEntryPoint))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                    
        httpSecurity.addFilterBefore(connectJWTAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
