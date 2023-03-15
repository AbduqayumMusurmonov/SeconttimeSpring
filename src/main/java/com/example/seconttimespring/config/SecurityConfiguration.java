package com.example.seconttimespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                            request.requestMatchers(HttpMethod.GET, "/test").permitAll();
                            request.requestMatchers(HttpMethod.GET, "/api/posts/paging").hasRole("ADMIN");
                            request.requestMatchers(HttpMethod.GET, "/api/posts").hasAnyRole("ADMIN","USER");
//                            request.requestMatchers(HttpMethod.GET, "/api/posts").permitAll();
                            request.requestMatchers(HttpMethod.GET, "/api/register").permitAll();
                            request.requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll();
                            request.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("admin").password(passwordEncoder().encode("1234")).authorities("ADMIN").roles("ADMIN").build();
        UserDetails user1 = User.withUsername("user").password(passwordEncoder().encode("12345")).authorities("USER").roles("USER").build();
        manager.createUser(user);
        manager.createUser(user1);
        return manager;
    }
}
