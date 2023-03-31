package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class enrollmentService {
    private final AccountRepo repo;
    private final intergrationService service;

    private final accountService accService;

    public enrollmentService(AccountRepo repo, intergrationService service, accountService accService) {
        this.repo = repo;
        this.service = service;
        this.accService = accService;
    }
    public void enrollStudentInCourse(Account account, Course course){
        account.enrollInCourse(course);
        repo.save(account);

    }

    public Invoice CreateEnrollmentInvoice(Account account, Course course){
        Invoice i = new Invoice();
        i.setAmount(course.getFee());
        LocalDate currentDate = LocalDate.now();
        i.setDueDate(currentDate.plusYears(1));
        i.setType(Invoice.Type.TUITION_FEES);
        i.setAccount(account);
        return service.createCourseFeeInvoice(i);
    }

    public Boolean canGraduate(Account account){
        Account selectedAccount = accService.findAccountByUsername(account.getUsername());
        Account FinanceAccount = service.getStudentAccount(selectedAccount.getStudentId());
        return FinanceAccount.getHasOutstandingBalance();
    }
}
