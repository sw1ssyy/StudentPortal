package com.example.sescassignment.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @JsonProperty("studentId")
    private String studentId;
    private Boolean hasOutstandingBalance;
    private String username;
    private String password;




    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Course> coursesEnrolledIn;

    public Account() {

    }

    public void enrollInCourse(Course course){
        if(coursesEnrolledIn == null){
            coursesEnrolledIn = new HashSet<>();
        }
        coursesEnrolledIn.add(course);
    }

    public Account(String studentId) {
        this.studentId = studentId;
    }

}
