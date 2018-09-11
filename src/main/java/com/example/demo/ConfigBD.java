package com.example.demo;

import org.pac4j.http.client.indirect.FormClient;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.sql.profile.service.DbProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBD {
    @Autowired
    DataSource dataSource;
    public ConfigBD(){}
    @Bean
    public Config config() {
        DbProfileService dbProfileService = new DbProfileService(dataSource);

        final FormClient formClient = new FormClient("http://localhost:4200", dbProfileService);
        final Clients clients = new Clients("http://localhost:8080", formClient);
        final Config configuration = new Config(clients);
        return configuration;
    }
}
