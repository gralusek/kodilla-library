package com.library.client;

import com.library.config.OpenWeatherAPIConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenWeatherClientTestSuite {

    @Autowired
    private OpenWeatherAPIConfig weatherAPIConfig;


    @Test
    void buildURL() {
        //Given
        String url = UriComponentsBuilder.fromHttpUrl(
                        weatherAPIConfig.getWeatherAPIEndpoint())
                .queryParam("q", "poznan")
                .queryParam("appid", weatherAPIConfig.getWeatherAPIKey())
                .build()
                .encode()
                .toString();

        String finalUrl = "https://api.openweathermap.org/data/2.5/weather?q=poznan&appid=f68e2a4e4c34bb5575e81925b1b50ebe";
        //When&Then
        assertEquals(url, finalUrl);


    }

    @Test
    void getTemperature() {
    }
}