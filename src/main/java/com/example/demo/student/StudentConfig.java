package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student shachin = new Student(
                    "Shachin",
                    "Sha@gmail.com",
                    LocalDate.of(2004, Month.MAY, 18)

            );

            Student kevin = new Student(
                    "Kevin",
                    "kev@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 1)

            );


            repository.saveAll(List.of(shachin, kevin));
        };
    }

}
