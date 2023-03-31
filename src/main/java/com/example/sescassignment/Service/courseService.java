package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.CourseRepo;
import com.example.sescassignment.Model.Invoice;
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

    public List<Course> SearchCoursebyName(String title){
        if(title == null || title.equals("")){
            return repo.findAll();
        }
        return repo.findCourseByTitle(title);
    }
    public Course findCourseByID(Long id){
        return repo.findCourseById(id);
    }

    public void processInvoice(Invoice invoice){

    }
}
