package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.Student;
import com.example.sescassignment.Model.StudentRepo;

public class enrollmentService {
    private final StudentRepo repo;

    public enrollmentService(StudentRepo repo) {
        this.repo = repo;
    }
    public void enrollStudentInCourse(Student student, Course course){
        student.enrollInCourse(course);
        repo.save(student);
    }
}
