package com.example.sescassignment.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    @DisplayName("Creating a New account")
    void CreatingAccount(){
        Account account =new Account();
        account.setId(1L);
        account.setUsername("testuser");
        account.setPassword("test");
        account.setStudentId("c1234567");
        account.setHasOutstandingBalance(false);

        assertAll(() -> assertEquals(1L, account.getId()),
                () -> assertEquals("testuser", account.getUsername()),
                () -> assertEquals("test", account.getPassword()),
                () -> assertEquals("c1234567", account.getStudentId()),
                () -> assertFalse(account.getHasOutstandingBalance()));
    }
    @Test
    @DisplayName("Enrolling into a Course")
    void EnrollingIntoCourse(){
        Account account = new Account();
        account.setId(1L);
        account.setUsername("testuser");
        account.setPassword("test");
        account.setStudentId("c1234567");
        account.setHasOutstandingBalance(false);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Title");
        course.setDescription("Test Description");
        course.setFee(25.00);

        account.enrollInCourse(course);

        assertEquals(1, account.getCoursesEnrolledIn().size());
    }

}