package com.home.reactor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix="reactor.app")
public class ApplicationConfiguration {

    private String version;
    private List<String> servers;

}
