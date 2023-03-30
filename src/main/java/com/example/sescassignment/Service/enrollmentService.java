package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.Invoice;

import java.time.LocalDate;


public class enrollmentService {
    private final AccountRepo repo;
    private final intergrationService service;

    public enrollmentService(AccountRepo repo,intergrationService service) {
        this.repo = repo;
        this.service = service;
    }
    public void enrollStudentInCourse(Account account, Course course){
        account.enrollInCourse(course);
        Invoice i = new Invoice();
        i.setAmount(course.getFee());
        LocalDate currentDate = LocalDate.now();
        i.setDueDate(currentDate.plusYears(1));
        i.setType(Invoice.Type.TUITION_FEES);
        i.setAccount(account);
        service.createCourseFeeInvoice(i);
        repo.save(account);
    }
}
