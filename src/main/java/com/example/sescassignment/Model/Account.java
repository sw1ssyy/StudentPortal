package com.example.sescassignment.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String studentID;

    private String username;
    private String password;



}
