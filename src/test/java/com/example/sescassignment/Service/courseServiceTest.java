package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.CourseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class courseServiceTest {
    @MockBean
   private CourseRepo repo;

    @Autowired
    private courseService service;
    private Course course;
    Long ID =  1L;
    String title = "testtitle";
    String description = "test";
    Double fee = 19.99;
    @BeforeEach
    void setUp(){
        Course course = new Course();
        course.setId(ID);
        course.setTitle(title);
        course.setDescription(description);
        course.setFee(fee);

        when(service.findCourseByID(ID))
                .thenReturn(course);
        when(service.SearchCoursebyName(title))
                .thenReturn(List.of(course));

    }

    @Test
    void getAllCourses() {
        Course course1 = new Course();
        course1.setId(2L);
        course1.setTitle("testcourse1");
        course1.setDescription("test description");
        course1.setFee(20.00);

        Course course2 = new Course();
        course2.setId(3L);
        course2.setTitle("testcourse2");
        course2.setDescription("test description");
        course2.setFee(20.00);

        when(service.getAllCourses()).thenReturn(List.of(course1, course2));
        List<Course> courseList = service.getAllCourses();


        assertEquals(2, courseList.size());
    }

    @Test
    @DisplayName("Testing 'searchCoursebyName' with valid data")
    void searchCoursebyName() {
        List<Course> results = service.SearchCoursebyName(title);
        assertEquals(1, results.size());
    }
    @Test
    @DisplayName("Testing 'searchCoursebyName' with missing data")
    void searchCoursebyNamewithMissingdata() {
        List<Course> results = service.SearchCoursebyName("asds");
        assertEquals(0, results.size());
    }
    @Test
    @DisplayName("Testing 'searchCoursebyName' with null data")
    void searchCoursebyNamewithnulldata() {
        List<Course> results = service.SearchCoursebyName(null);
        assertEquals(0, results.size());
    }

    @Test
    @DisplayName("Testing 'FindCourseByID' Function with valid data")
    void findCourseByID() {
        Course result = service.findCourseByID(ID);
        assertAll(() -> assertEquals(ID, result.getId()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(description, result.getDescription()),
                () -> assertEquals(fee, result.getFee()));

    }


    @Test
    @DisplayName("Testing 'FindCourseByID' with Null data expecting exception")
    void findCourseByIDwthNulldata(){
        assertThrows(RuntimeException.class, () -> service.findCourseByID(null),
                "Error: ID is Null");
    }
}