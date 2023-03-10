package ru.marasanov.neptune.banking.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.marasanov.neptune.banking.security.AccountDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final AccountDetailsService accountDetailsService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, AccountDetailsService accountDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.accountDetailsService = accountDetailsService;
    }

    /**
     * Виды аутентификации:
     * Базовая (httpBasic) – аутентификация GET-запросом с передачей credentials в хедере запроса
     * Через форму (formLogin) – аутентификация POST-запросом с передачей credentials в теле запроса
     * Auth 2.0 – advanced тема
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(daoAuthenticationProvider())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeRequests(auth -> {
                auth.antMatchers("/api/v1/registration").permitAll();
                auth.antMatchers("/api/v1/account**").hasRole("CLIENT");
                auth.antMatchers("/api/v1/admin**").hasRole("ADMIN");
            })
            .formLogin();
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(accountDetailsService);
        return provider;
    }
}
