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

    /**
     * Example of Dependency Injection
     * @param repo - Instance of Account Repo created by Spring Framework
     * @param restTemplate - Instance of the restTemplate created by Spring Framework
     */
    public accountService(AccountRepo repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    /**
     * Method used to check if an account Exists within the database by username and password
     * @param username - username being searched
     * @param password - password being searched
     * @return boolean status of the search
     */
    public Boolean checkAccountExists(String username, String password){
        return repo.existsAccountByUsernameAndPassword(username,password);
    }

    /**
     * Method used to check if an account Exists within the database by username and password
     * @param username - username being searched
     * @return boolean status of the search
     */
    public Boolean checkAccountExistsbyUsername(String username ){
        return repo.existsAccountByUsername(username);
    }

    /**
     * Method used to create an account within the student portal
     * @param account - new account created
     */
    public void createNewAccount(Account account) {
        account.setStudentId(createNewStudentID());
        account.setHasOutstandingBalance(false);
        if(account == null){
            throw new RuntimeException("ERROR: Missing Critical Data");
        }
         repo.save(account);
    }

    /**
     * Method used to create an account within the Finance portal
     * @param account - account object used with the creation
     */
    public void createFinanceAccount(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String>accountData = new HashMap<>();
        accountData.put("studentId", account.getStudentId());
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(accountData, headers);
        String URL = "http://127.0.0.1:8000/api/accounts/create";
        restTemplate.postForObject(URL, requestEntity, Account.class);

    }

    /**
     * Method used to create an account within the Library Portal
     * @param account - account object used with the creation
     */

    public void createLibraryAccount(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String,String> accountData = new HashMap<>();
        accountData.put("studentId", account.getStudentId());
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(accountData, headers);
        String URL = "http://localhost:9025/api/account";
        restTemplate.postForObject(URL, requestEntity,Account.class);

    }

    /**
     * Method used to search and retreive an account from the account table within the database
     * @param user - username of the account
     * @return - account found within the database
     */
    public Account findAccountByUsername(String user){
        if(user == null){
            throw new RuntimeException("Error User is empty");
        }
        return repo.findAccountByUsername(user);
    }

    /**
     * Method used to edit account details
     * @param id - Path variable used within the URL
     * @param account - Account being edited
     */
    public void updateAccount(@PathVariable Long id, Account account){
        Account updatedAccount = repo.findAccountById(id);
        if(updatedAccount == null){
            throw new RuntimeException("Account: '" + account.getUsername() + "' Does not Exist");
        }
        updatedAccount.setUsername(account.getUsername());
        updatedAccount.setPassword(account.getPassword());
         repo.save(updatedAccount);
    }

    /**
     * Method used to create an account's StudentID when they Sign up
     * @return an 7 digit of numbers that will be added to the letter "c" to for a student ID
     * AN example of this would be "c3238132"
     */

    public String createNewStudentID() {
        Random r = new Random();
        String studentID = "c";
        for (int i = 0; i < 7; i++) {

            int num = r.nextInt(9);
            studentID = studentID.concat(String.valueOf(num));
        }
        return studentID;
    }


}

