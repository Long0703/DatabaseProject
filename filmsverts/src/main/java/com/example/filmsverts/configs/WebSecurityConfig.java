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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .securityMatcher("/user/**", "/user/login", "/user/register", "/process-user-login", "/user/profile")
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/login", "/user/register", "/process-user-login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/user/**", "/user/profile").hasRole("USER")
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/user/login")
            .loginProcessingUrl("/process-user-login")
            .defaultSuccessUrl("/user/profile", true)  // Sửa thành đường dẫn /user/profile thay vì /user-profile
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
        .securityMatcher("/admin/**", "/admin-login", "/admin-register", "/process-admin-login", "/admin-profile")
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin-login", "/admin-register", "/process-admin-login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**", "/admin/profile", "/admin/dashboard").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/admin-login")
            .loginProcessingUrl("/process-admin-login")
            .defaultSuccessUrl("/admin/dashboard", true)  // Sửa thành đường dẫn /admin/dashboard
            .failureUrl("/admin-login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/admin/logout")
            .logoutSuccessUrl("/admin-login?logout=true")
            .permitAll()
        )
        .authenticationProvider(adminAuthenticationProvider());
        return http.build();
    }
    
    // Cấu hình cho các đường dẫn công khai
    @Bean
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/", "/css/**", "/js/**", "/images/**")  // Các đường dẫn công khai
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
    
 // AuthenticationProvider cho admin
    @SuppressWarnings("deprecation")
    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // AuthenticationProvider cho user
    @SuppressWarnings("deprecation")
    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Cấu hình Authenticationadmin
    @Bean
    public AuthenticationManager authenticationadmin(
            AuthenticationConfiguration authenticationConfiguration,
            DaoAuthenticationProvider userAuthenticationProvider,
            DaoAuthenticationProvider adminAuthenticationProvider
    ) throws Exception {
        return new ProviderManager(
                List.of(userAuthenticationProvider, adminAuthenticationProvider)
        );
    }
}
