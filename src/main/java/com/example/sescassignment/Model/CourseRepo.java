package com.example.sescassignment.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findCourseByTitle(String courseTitle);
}
