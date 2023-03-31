package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class accountService {
    private final AccountRepo repo;
    private final RestTemplate restTemplate;

    public accountService(AccountRepo repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }
    public Boolean checkAccountExists(String username, String password){
        return repo.existsAccountByUsernameAndPassword(username,password);
    }
    public void createNewAccount(Account account) {
        account.setStudentId(createNewStudentID());
        account.setHasOutstandingBalance(false);
         repo.save(account);
    }
    public void createFinanceAccount(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String>accountData = new HashMap<>();
        accountData.put("studentId", account.getStudentId());
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(accountData, headers);
        String URL = "http://localhost:8081/accounts/";
        restTemplate.postForObject(URL, requestEntity, Account.class);

    }

    public void createLibraryAccount(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String,String> accountData = new HashMap<>();
        accountData.put("studentId", account.getStudentId());
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(accountData, headers);
        String URL = "http://localhost:80/api/register";
        restTemplate.postForObject(URL, requestEntity,Account.class);

    }

    public Account findAccountByUsername(String user){
        return repo.findAccountByUsername(user);
    }


    public void updateAccount(@PathVariable Long id, Account account){
        Account updatedAccount = repo.findAccountById(id);
        if(updatedAccount == null){
            throw new RuntimeException("Account: '" + account.getUsername() + "' Does not Exist");
        }
        updatedAccount.setUsername(account.getUsername());
        updatedAccount.setPassword(account.getPassword());
         repo.save(updatedAccount);
    }
    public ResponseEntity<Account> updateAccountJSON(@PathVariable long id, Account account) {
        Account updatedAccount = repo.findAccountById(id);
        if (updatedAccount == null){
            throw new RuntimeException("Account: '" + account.getUsername() + "' Does not Exist ");

        }
        updatedAccount.setUsername(account.getUsername());
        updatedAccount.setPassword(account.getPassword());
        repo.save(updatedAccount);
        return ResponseEntity.ok(updatedAccount);
    }



    private String createNewStudentID() {
        Random r = new Random();
        String studentID = "c";
        for (int i = 0; i < 7; i++) {

            int num = r.nextInt(9);
            studentID = studentID.concat(String.valueOf(num));
        }
        return studentID;
    }


}

