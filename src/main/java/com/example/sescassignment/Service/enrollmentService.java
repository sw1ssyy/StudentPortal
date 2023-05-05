package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class enrollmentService {
    private final AccountRepo repo;
    private final intergrationService service;

    private final accountService accService;

    /**
     * Example of Dependency Injection
     * @param repo - Instance of Account Repository created by Spring Boot Framework
     * @param service - Instance of the integration service created by Spring Boot Framework
     * @param accService - Instance of the account service created by Spring Boot Framework
     */
    public enrollmentService(AccountRepo repo, intergrationService service, accountService accService) {
        this.repo = repo;
        this.service = service;
        this.accService = accService;
    }

    /**
     * Method used to enroll an account onto a course
     * @param account - account enrolling
     * @param course - course being enrolled into
     */
    public void enrollStudentInCourse(Account account, Course course){
        account.enrollInCourse(course);
        repo.save(account);

    }


    /**
     * Method used to create an invoice after an account has enrolled
     * @param account - account getting the invoice
     * @param course - account being enrolled into
     * @return the integration service creating the invoice that will be sent to the finance portal
     */

    public Invoice CreateEnrollmentInvoice(Account account, Course course){
        Invoice i = new Invoice();
        i.setAmount(course.getFee());
        LocalDate currentDate = LocalDate.now();
        i.setDueDate(currentDate.plusYears(1));
        i.setType(Invoice.Type.TUITION_FEES);
        i.setAccount(account);
        return service.createCourseFeeInvoice(i);
    }

    /**
     * Boolean Status to check if an account has any existing payments on the finance portal and therefore can graduate or not
     * @param account - account being queried
     * @return result of the search via boolean value
     */
    public Boolean canGraduate(Account account){
        Account selectedAccount = accService.findAccountByUsername(account.getUsername());
        Account FinanceAccount = service.getStudentAccount(selectedAccount.getStudentId());
        return FinanceAccount.getHasOutstandingBalance();
    }
}
