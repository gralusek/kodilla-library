package com.library.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OpenWeatherAPIConfig {

    @Value("${openWeather.api.key}")
    private String weatherAPIKey;

    @Value("${openWeather.api.endpoint}")
    private String weatherAPIEndpoint;

}
