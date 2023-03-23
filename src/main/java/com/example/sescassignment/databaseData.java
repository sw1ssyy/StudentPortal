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
    CommandLineRunner initDatabase(CourseRepo courseRepo, StudentRepo studentRepo, AccountRepo accountRepo) {
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

            Student John = new Student();
            John.setFirstname("John");
            John.setLastname("Wick");
            John.setExternal_id("c3949549");
            John.enrollInCourse(SESC);
            John.enrollInCourse(Dissertation);

            Student Alex = new Student();
            Alex.setFirstname("Alex");
            Alex.setLastname("Richards");
            Alex.setExternal_id("c3685291");
            Alex.enrollInCourse(SESC);
            Alex.enrollInCourse(CloudComputing);

            Student Rachel = new Student();
            Rachel.setFirstname("Rachel");
            Rachel.setLastname("Williams");
            Rachel.setExternal_id("c4582910");
            Rachel.enrollInCourse(CloudComputing);
            Rachel.enrollInCourse(Dissertation);


            studentRepo.saveAllAndFlush(Set.of(John, Alex, Rachel));

            Account mister = new Account();
            mister.setUsername("Mister");
            mister.setPassword("test");
            mister.setStudentID("c3538468");

            Account richards = new Account();
            richards.setUsername("richards");
            richards.setPassword("test2");
            richards.setStudentID("c3231361");

            accountRepo.saveAllAndFlush(Set.of(mister,richards));
        };
    }

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }
}
