package com.ubs.opsit.interviews.configuration;

import com.ubs.opsit.interviews.domain.BerlinClock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;

/**
 * Spring application configuration and starting point of application.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Instatiate and return BerlinClock object instance.
     * @return BerlinClock
     */
    @Bean
    public BerlinClock berlinClock(){
        return new BerlinClock();
    }
}
