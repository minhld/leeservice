package com.minhle.leeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9142);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping()
            throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}