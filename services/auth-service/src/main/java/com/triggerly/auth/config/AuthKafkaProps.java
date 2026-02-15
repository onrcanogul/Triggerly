package com.triggerly.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka")
public record AuthKafkaProps(String bootstrapServers) { }