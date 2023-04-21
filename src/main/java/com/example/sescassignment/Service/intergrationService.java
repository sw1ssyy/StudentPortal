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
        return template.getForObject("http://127.0.0.1:8000/api/accounts/search/" + studentID, Account.class);
    }

    public Invoice createCourseFeeInvoice(Invoice invoice){
        return template.postForObject("http://127.0.0.1:8000/api/invoice/create", invoice , Invoice.class);
    }





}
