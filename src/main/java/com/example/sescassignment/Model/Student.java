package com.example.sescassignment.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String external_id;
    private String firstname;
    private String lastname;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_Student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
            @EqualsAndHashCode.Exclude
            @ToString.Exclude
    Set<Course> coursesEnrolledIn;

    public void enrollInCourse(Course course){
        if(coursesEnrolledIn == null){
            coursesEnrolledIn = new HashSet<>();
        }
        coursesEnrolledIn.add(course);
    }
}
