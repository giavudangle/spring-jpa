package com.selftaught.selftaught.student;


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
            Student v1= new Student(
                    1L,
                    "VuDang",
                    "vudang3123@lazada.com",
                    LocalDate.of(2000, Month.FEBRUARY,22)
            );

            Student v2 = new Student(
                    2L,
                    "VuDang",
                    "VuDang123@lazada.com",
                    LocalDate.of(2000, Month.JANUARY,11)
            );
            repository.saveAll(
                    List.of(v1,v2)
            );
        };
    }
}
