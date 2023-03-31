package com.example.sescassignment;

import com.example.sescassignment.Model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Configuration
public class databaseData {
    @Bean
    CommandLineRunner initDatabase(CourseRepo courseRepo, AccountRepo accountRepo) {
        return args -> {
            Course SESC = new Course();
            SESC.setTitle("SESC");
            SESC.setDescription("Software Engineering for Service Computing");
            SESC.setFee(10.00);


            Course Dissertation = new Course();
            Dissertation.setTitle("Dissertation");
            Dissertation.setDescription("Final Year Module for Master Students");
            Dissertation.setFee(15.00);

            Course CloudComputing = new Course();
            CloudComputing.setTitle("Cloud Computing");
            CloudComputing.setDescription("Cloud Services Development");
            CloudComputing.setFee(20.00);


            Account mister = new Account();
            mister.setUsername("Mister");
            mister.setPassword("test");
            mister.setStudentId("c3538468");
            mister.setHasOutstandingBalance(false);

            Account richards = new Account();
            richards.setUsername("richards");
            richards.setPassword("test2");
            richards.setStudentId("c3231361");
            richards.setHasOutstandingBalance(false);

            accountRepo.saveAllAndFlush(Set.of(mister,richards));
            courseRepo.saveAllAndFlush(Set.of(SESC,CloudComputing,Dissertation));
        };
    }

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }
}
