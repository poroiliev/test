package com.home.reactor.controllers;

import com.home.reactor.config.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private ApplicationConfiguration config;

    @Autowired
    public TestController(ApplicationConfiguration config) {
        this.config = config;
    }

    @RequestMapping(path = "/")
    public Mono<String> hello() {
        return ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
            Authentication authentication = securityContext.getAuthentication();
            return Mono.just(config.getVersion() + " " + config.getServers().toString());
        });

    }

}
