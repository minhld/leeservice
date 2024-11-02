package com.minhle.leeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {
    @Value(value = "${spring.data.cassandra.cluster}")
    private String cluster;
    @Value(value = "${spring.data.cassandra.port}")
    private String port;

}
