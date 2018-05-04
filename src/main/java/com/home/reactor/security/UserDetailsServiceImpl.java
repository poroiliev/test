package com.home.reactor.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

//@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private String hiu = "asd";

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserDetails userDetails = new User("me", "$2a$10$.eglE.JJNa3fnY1lEx4nZ.DAYErBJfMnw4Dm45RNDObXCdpj57RdO", Collections.singletonList(new SimpleGrantedAuthority("x")));
        return Mono.just(userDetails);
    }
}
