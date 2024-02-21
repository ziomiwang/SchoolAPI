package org.example.schoolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class SchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApiApplication.class, args);
    }
}
