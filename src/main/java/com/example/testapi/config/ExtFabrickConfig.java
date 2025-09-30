package com.example.testapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class ExtFabrickConfig {

    @Value("${external.api.key}")
    private String apiKey;

    @Value("${external.auth.schema}")
    private String authSchema;

    @Value("${external.client.baseurl}")
    private String baseUrl;



    @Bean
    public RestClient fabrickRestClient(RestClient.Builder builder, PropertyResolver propertyResolver) {
        return builder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Api-Key", apiKey)
                .defaultHeader("Auth-Schema", authSchema)
                .build();
    }
}
