package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class intergrationService {
    private final RestTemplate template;

    /**
     * Example of Dependency Injection
     * @param template - instance of the restTemplate created by SpringBoot
     */
    public intergrationService(RestTemplate template) {
        this.template = template;
    }

    /**
     * Method used to create  GET Request that will search a specific accounts status on the finance portal
     * @param studentID - studentID being searched
     * @return - Account object found on the finance portal
     */
    public Account getStudentAccount(String studentID){
        return template.getForObject("http://127.0.0.1:8000/api/accounts/search/" + studentID, Account.class);
    }

    /**
     * Method used to create a POST request to create an Invoice after enrollng on a course
     * @param invoice - Invoice being sent to the finance portal
     * @return - Finished Invoice created by Finance portal
     */
    public Invoice createCourseFeeInvoice(Invoice invoice){
        return template.postForObject("http://127.0.0.1:8000/api/invoice/create", invoice , Invoice.class);
    }





}
