package com.home.reactor.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("me",
                "$2a$10$.eglE.JJNa3fnY1lEx4nZ.DAYErBJfMnw4Dm45RNDObXCdpj57RdO",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_x")));
        return Mono.just(token);
    }
}
