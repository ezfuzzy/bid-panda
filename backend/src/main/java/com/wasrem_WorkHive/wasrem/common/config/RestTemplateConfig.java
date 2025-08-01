package com.wasrem_WorkHive.wasrem.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // 연결 타임아웃 설정 (5초)
        factory.setConnectTimeout(5000);

        // 읽기 타임아웃 설정 (10초)
        factory.setReadTimeout(10000);

        return new RestTemplate(factory);
    }
}
