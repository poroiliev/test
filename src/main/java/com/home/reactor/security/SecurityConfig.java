package com.home.reactor.security;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.AuthorizationWebFilter;
import reactor.core.publisher.Mono;

import java.util.Collections;

@EnableWebFluxSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private ReactiveAuthenticationManager authenticationManager;

    public SecurityConfig(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter authentication = new AuthenticationWebFilter(authenticationManager);

        AuthorizationWebFilter authorization = new AuthorizationWebFilter(((authentication1, object) -> {
            System.out.println();
            return Mono.just(new AuthorizationDecision(true));
        }));

        authentication.setAuthenticationConverter((exchange) -> Mono.just(
                new UsernamePasswordAuthenticationToken("me",
                        "$2a$10$.eglE.JJNa3fnY1lEx4nZ.DAYErBJfMnw4Dm45RNDObXCdpj57RdO",
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_x")))));

        return http.csrf()                .disable()
                .exceptionHandling()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeExchange()
                    .pathMatchers("/**").hasRole("x")
                    .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAt(authorization, SecurityWebFiltersOrder.AUTHORIZATION)
                .addFilterAt(authentication, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

