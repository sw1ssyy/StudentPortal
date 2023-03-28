package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;

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
