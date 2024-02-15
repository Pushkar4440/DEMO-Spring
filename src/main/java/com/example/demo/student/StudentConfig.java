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
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student pushkar = new Student(
                    "Pushkar",
                    "pushkar4440@gmail.com",
                    LocalDate.of(2002, Month.FEBRUARY,02)
            );

            Student tushar = new Student(
                    "Tushar",
                    "tushar@gmail.com",
                    LocalDate.of(1999, Month.MAY,01)
            );
            repository.saveAll(
                    List.of(pushkar, tushar)
            );
        };
    }
}
