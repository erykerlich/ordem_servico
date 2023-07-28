package com.eryk.os.config;


import com.eryk.os.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Bean
    public Boolean instanciaDb() {
        if(ddl.equals("create")) {
            this.dbService.instanciaDB();
        }
        return false;
    }

}
