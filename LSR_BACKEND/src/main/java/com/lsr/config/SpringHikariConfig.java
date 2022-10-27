package com.lsr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class SpringHikariConfig extends HikariConfig {

}