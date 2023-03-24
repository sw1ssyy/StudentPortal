package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
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
    public Account createNewAccount(Account account) {
        account.setStudentID(createNewStudentID());
        return repo.save(account);
    }
    public Account createFinanceAccount(String studentID){
        return restTemplate.postForObject("localhost:8081/accounts/",studentID, Account.class);
    }

    public Account findAccountByUsername(String user){
        return repo.findAccountByUsername(user);
    }

    public ResponseEntity<Account> updateAccount(@PathVariable long id, Account account) {
        Account updatedAccount = repo.findAccountById(id);
        if (updatedAccount == null){
            throw new RuntimeException("Account: '" + account.getUsername() + "' Does not Exist ");

        }
        updatedAccount.setUsername(account.getUsername());
        updatedAccount.setPassword(account.getPassword());
        updatedAccount.setStudentID(account.getStudentID());
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

