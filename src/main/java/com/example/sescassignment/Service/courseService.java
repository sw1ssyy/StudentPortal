package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.CourseRepo;
import com.example.sescassignment.Model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class courseService {
    private final CourseRepo repo;
    /**
     * Example of Dependency Injection
     * @param repo - Instance of Course Repo created by Spring Framework
     */
    public courseService(CourseRepo repo) {
        this.repo = repo;
    }

    /**
     * Method used to retrieve a list of all courses
     * @return List of all courses
     */
    public List<Course> getAllCourses(){
        return repo.findAll();
    }

    /**
     * Method used to retrieve a list of courses with a specific title
     * @param title - keyword used to search the database
     * @return - List of courses found
     */
    public List<Course>SearchCoursebyName(String title){
        if(title == null || title.equals("")){
            return repo.findAll();
        }
        return repo.findCourseByTitle(title);
    }

    /**
     * Method used to find a specific course by its course ID value
     * @param id - course ID value being searched
     */
    public Course findCourseByID(Long id){
        if(id == null){
            throw new RuntimeException("Error: ID is Null");
        }
        return repo.findCourseById(id);
    }


}
