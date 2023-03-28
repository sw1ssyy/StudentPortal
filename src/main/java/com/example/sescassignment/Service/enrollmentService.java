package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import com.example.sescassignment.Model.Course;

public class enrollmentService {
    private final AccountRepo repo;

    public enrollmentService(AccountRepo repo) {
        this.repo = repo;
    }
    public void enrollStudentInCourse(Account account, Course course){
        account.enrollInCourse(course);
        repo.save(account);
    }
}
