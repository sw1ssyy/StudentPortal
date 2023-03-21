package com.example.sescassignment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Account {
    private Long id;
    private String studentID;
    private boolean hasOutstandingBalance;



}
