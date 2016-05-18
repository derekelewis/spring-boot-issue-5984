package com.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateFactory {

    @Bean
    public RestTemplate testRestTemplate(@Qualifier("testObjectMapper") ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        httpMessageConverters.add(mappingJackson2HttpMessageConverter);
        httpMessageConverters.add(new ByteArrayHttpMessageConverter());
        httpMessageConverters.add(new StringHttpMessageConverter());
        httpMessageConverters.add(new ResourceHttpMessageConverter());
        httpMessageConverters.add(new SourceHttpMessageConverter());
        httpMessageConverters.add(new FormHttpMessageConverter());
        httpMessageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        restTemplate.setMessageConverters(httpMessageConverters);

        return restTemplate;
    }
}
