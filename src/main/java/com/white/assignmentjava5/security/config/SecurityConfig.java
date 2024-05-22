package com.white.assignmentjava5.security.config;

import com.white.assignmentjava5.repository.INhanVienRepository;
import com.white.assignmentjava5.security.role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(INhanVienRepository userRepository,
                                                 PasswordEncoder passwordEncoder) {
        return (u)-> userRepository.findByTenDangNhap(u)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        {
//                            auth.requestMatchers("/login").permitAll();
//                            auth.requestMatchers("/logout").permitAll();
//                            auth.requestMatchers("/admin/**").hasAuthority(Role.ADMIN.name());
                            auth.requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.name());
                            auth.anyRequest().authenticated();
                        })
                .formLogin(formLogin -> formLogin
                        .permitAll()
                        .defaultSuccessUrl("/web/trang-chu"))
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .build();
    }
}
