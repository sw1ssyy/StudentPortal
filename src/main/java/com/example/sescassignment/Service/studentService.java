package com.example.sescassignment.Service;

import com.example.sescassignment.Model.StudentRepo;
import org.springframework.stereotype.Component;

@Component
public class studentService {
    private final StudentRepo repo;

    public studentService(StudentRepo repo) {
        this.repo = repo;
    }
}
