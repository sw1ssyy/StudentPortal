package com.example.sescassignment.Controller;

import com.example.sescassignment.Service.accountService;
import org.springframework.stereotype.Controller;

@Controller
public class accountController {
    private final accountService service;

    public accountController(accountService service) {
        this.service = service;
    }
}
