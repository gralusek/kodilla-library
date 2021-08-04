package com.library.client;

import com.library.config.OpenWeatherAPIConfig;
import com.library.Dto.OpenWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class OpenWeatherClient {

    @Autowired
    private OpenWeatherAPIConfig weatherAPIConfig;

    @Autowired
    private final RestTemplate restTemplate;

    public URI buildURL() {
        URI url = UriComponentsBuilder.fromHttpUrl(
                weatherAPIConfig.getWeatherAPIEndpoint())
                .queryParam("q", "poznan")
                .queryParam("appid", weatherAPIConfig.getWeatherAPIKey())
                .build()
                .encode()
                .toUri();
        return url;
    }

    public OpenWeatherDto getTemperature() {

        try {
            OpenWeatherDto response = restTemplate.getForObject(buildURL(), OpenWeatherDto.class);
            return response;
        } catch (RestClientException e) {
            return null;
        }
    };
}
