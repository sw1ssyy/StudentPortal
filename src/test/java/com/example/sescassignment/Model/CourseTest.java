package com.example.sescassignment.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CourseTest {

    @Test
    @DisplayName("Testing if new course object can be set")
    void creatingCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Title");
        course.setDescription("Test Description");
        course.setFee(25.00);

        assertAll(() -> assertEquals(1L, course.getId()),
                () -> assertEquals("Test Title", course.getTitle()),
                () -> assertEquals("Test Description", course.getDescription()),
                () -> assertEquals(25.00, course.getFee()));

    }

    }

