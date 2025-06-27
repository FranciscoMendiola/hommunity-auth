package com.syrion.hommunity_api.config.Security;

import com.syrion.hommunity_api.config.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtFilter;

    public SecurityConfig(JwtAuthFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                // Usuario
                .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/**").hasAnyAuthority("Administrador", "Residente")

                // Zona
                .requestMatchers(HttpMethod.POST, "/zona/**").hasAuthority("Administrador")

                // Invitado
                .requestMatchers(HttpMethod.GET, "/invitado/**").hasAnyAuthority("Residente", "Administrador")
                .requestMatchers(HttpMethod.POST, "/invitado").hasAnyAuthority("Residente", "Administrador")
                .requestMatchers(HttpMethod.PATCH, "/invitado/**").hasAuthority("Administrador")


                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            // Filtro JWT
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

