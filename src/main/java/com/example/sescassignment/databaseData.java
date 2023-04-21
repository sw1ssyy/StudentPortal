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

            Course Robotics = new Course();
            Robotics.setTitle("Robotics");
            Robotics.setDescription("Learn how to make Robots!!!!");
            Robotics.setFee(50.00);

            Course DataAnalytics = new Course();
            DataAnalytics.setTitle(" Applied Data Analytics");
            DataAnalytics.setDescription(" Learn Data Science with Python and R ");
            DataAnalytics.setFee(40.00);

            Course MobileApplications = new Course();
            MobileApplications.setTitle("Developing Mobile Applications");
            MobileApplications.setDescription("Make Mobile Apps with Android Studio");
            MobileApplications.setFee(35.00);

            Course NetworkManagement = new Course();
            NetworkManagement.setTitle("Network Management");
            NetworkManagement.setDescription("Learning about Managing Existing Networks");
            NetworkManagement.setFee(35.00);

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
            courseRepo.saveAllAndFlush(Set.of(SESC,CloudComputing,Dissertation,NetworkManagement, Robotics,MobileApplications, DataAnalytics));
        };
    }

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }
}
