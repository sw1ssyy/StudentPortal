package com.example.sescassignment.Service;

import com.example.sescassignment.Model.AccountRepo;
import org.springframework.stereotype.Component;

@Component
public class accountService {
    private final AccountRepo repo;

    public accountService(AccountRepo repo) {
        this.repo = repo;
    }
}
