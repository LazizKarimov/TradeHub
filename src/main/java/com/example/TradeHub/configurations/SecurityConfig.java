package com.example.TradeHub.configurations;

import com.example.TradeHub.service.CustomUserDetailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
// позволяет настраивать безопасность приложения, определять права доступа и другие аспекты безопасности.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Метод, который создает цепочку фильтров безопасности для обработки HTTP-запросов.
     * <p>
     * http.authorizeHttpRequests((requests) -> requests ... ): Настройка прав доступа к HTTP-запросам,
     * где определены разрешенные и аутентифицированные запросы.
     * <p>
     * formLogin((form) -> form.loginPage("/login").permitAll()): Настройка формы входа,
     * где указывается страница входа и разрешаются все запросы на нее.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/product/", "/images/", "/registration")
                        .permitAll().anyRequest().authenticated()).
                formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());
        return http.build();
    }

    /**
     * Метод, который возвращает менеджер аутентификации для обработки аутентификационных запросов.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Метод, который возвращает объект для кодирования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}