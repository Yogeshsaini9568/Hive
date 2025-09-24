package com.hive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${api.base.url:http://localhost:2003}")
    private String apiBaseUrl;

    @Value("${api.user.path:/user}")
    private String userApiPath;

    @Value("${api.property.path:/property}")
    private String propertyApiPath;

    @Value("${api.owner.path:/owner}")
    private String ownerApiPath;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public String getUserApiPath() {
        return userApiPath;
    }

    public String getPropertyApiPath() {
        return propertyApiPath;
    }

    public String getOwnerApiPath() {
        return ownerApiPath;
    }

    public String getUserApiUrl() {
        return apiBaseUrl + userApiPath;
    }

    public String getPropertyApiUrl() {
        return apiBaseUrl + propertyApiPath;
    }

    public String getOwnerApiUrl() {
        return apiBaseUrl + ownerApiPath;
    }
} 