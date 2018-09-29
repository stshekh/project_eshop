package com.gmail.sshekh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:database.properties", "classpath:page.properties"})
@ComponentScan(basePackages = {
        "com.gmail.sshekh.service",
        "com.gmail.sshekh.dao",
        "com.gmail.sshekh.config",
        "com.gmail.sshekh.controllers"
})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
