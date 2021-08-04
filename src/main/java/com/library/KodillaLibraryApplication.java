package com.library;

import com.library.client.OpenWeatherClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KodillaLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodillaLibraryApplication.class, args);
    }

}
