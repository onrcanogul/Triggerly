package com.triggerly.market.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.kafka")
public record MarketKafkaProps(String bootstrapServers) { }