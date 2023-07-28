package com.eryk.os.config;


import com.eryk.os.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    @Bean
    public void instanciaDb() {
        this.dbService.instanciaDB();
    }

}
