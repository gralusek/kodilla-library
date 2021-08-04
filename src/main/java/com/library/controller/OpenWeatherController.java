package com.library.controller;

import com.library.Dto.OpenWeatherDto;
import com.library.client.OpenWeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class OpenWeatherController {

    private final OpenWeatherClient openWeatherClient;

    @GetMapping("getTemperature")
    public void getTemperature() {

        OpenWeatherDto temp = openWeatherClient.getTemperature();
        System.out.println("test" + temp.getTemp());
    }

}
