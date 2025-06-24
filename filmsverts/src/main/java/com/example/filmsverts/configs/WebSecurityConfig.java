package com.example.filmsverts.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.filmsverts.services.CustomAdminDetailsService;
import com.example.filmsverts.services.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final CustomAdminDetailsService adminDetailsService;
    private final CustomUserDetailsService userDetailsService;

    public WebSecurityConfig(CustomAdminDetailsService adminDetailsService,
                             CustomUserDetailsService userDetailsService) {
        this.adminDetailsService = adminDetailsService;
        this.userDetailsService = userDetailsService;
    }

    // PASSWORD ENCODER CHO USER (BCRYPT)
    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // PASSWORD ENCODER CHO ADMIN (PLAIN TEXT)
    @Bean
    public PasswordEncoder adminPasswordEncoder() {
        return new AdminPlainTextPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .securityMatcher("/user/**", "/process-user-login")
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/login", "/user/register", "/process-user-login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/user/**").hasRole("USER")
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/user/login")
            .loginProcessingUrl("/process-user-login")
            .defaultSuccessUrl("/user/profile", true)
            .failureUrl("/user/login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/user/logout")
            .logoutSuccessUrl("/user/login?logout=true")
            .permitAll()
        )
        .authenticationProvider(userAuthenticationProvider());
        return http.build();
    }
    
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .securityMatcher("/admin/**", "/admin/login", "/process-admin-login", "/admin/profile")
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/login", "/process-admin-login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**", "/admin/profile", "/admin/dashboard").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/admin/login")
            .loginProcessingUrl("/process-admin-login")
            .defaultSuccessUrl("/admin/profile", true)  // Sửa thành đường dẫn /admin/dashboard
            .failureUrl("/admin/login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/admin/logout")
            .logoutSuccessUrl("/admin/login?logout=true")
            .permitAll()
        )
        .authenticationProvider(adminAuthenticationProvider());
        return http.build();
    }
    
    @Bean
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/", "/css/**", "/js/**", "/images/**")
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
    
    // ADMIN AUTHENTICATION PROVIDER - DÙNG PLAIN TEXT ENCODER
    @SuppressWarnings("deprecation")
	@Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService);
        provider.setPasswordEncoder(adminPasswordEncoder()); // PLAIN TEXT
        return provider;
    }

    // USER AUTHENTICATION PROVIDER - DÙNG BCRYPT ENCODER
    @SuppressWarnings("deprecation")
	@Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(userPasswordEncoder()); // BCRYPT
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration,
            DaoAuthenticationProvider userAuthenticationProvider,
            DaoAuthenticationProvider adminAuthenticationProvider
    ) throws Exception {
        return new ProviderManager(
                List.of(userAuthenticationProvider, adminAuthenticationProvider)
        );
    }
}