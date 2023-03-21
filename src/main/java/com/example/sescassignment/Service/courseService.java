package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.CourseRepo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class courseService {
    private final CourseRepo repo;

    public courseService(CourseRepo repo) {
        this.repo = repo;
    }

    public List<Course> getAllCourses(){
        return repo.findAll();
    }
}
