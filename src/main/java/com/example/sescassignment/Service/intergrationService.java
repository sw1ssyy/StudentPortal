package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class intergrationService {
    private final RestTemplate template;

    public intergrationService(RestTemplate template) {
        this.template = template;
    }

    public Account getStudentAccount(String studentID){
        return template.getForObject("http://localhost:8081/accounts/student" + studentID, Account.class);
    }

    public Invoice createCourseFeeInvoice(Invoice invoice){
        return template.postForObject("http:/localhost:8081/invoices/", invoice , Invoice.class);
    }

    public void createFinanceAccount(Account account){
        template.postForObject("http://localhost:8081/accounts/", account.getStudentID(), Account.class);
    }


}
