package com.example.sescassignment.Service;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
 public class accountServiceTest {

    private Account account;
    Long id = 1L;
    String username = "testuser";
    String password = "testpass";
    String studentID = "c1234567";
    Boolean oustandingbalance = false;

    @MockBean
    private AccountRepo accountRepo;

   @Autowired
    private accountService accountService;

   @BeforeEach
   void setUp(){
       Account account = new Account();
       account.setId(id);
       account.setUsername(username);
       account.setPassword(password);
       account.setStudentId(studentID);
       account.setHasOutstandingBalance(oustandingbalance);
       Mockito.when(accountService.findAccountByUsername(username))
               .thenReturn(account);
       Mockito.when(accountService.checkAccountExists(username, password))
               .thenReturn(true);


   }

    @Test
    @DisplayName("Testing 'FindbyUsername' Function with correct data")
     void testFindbyUsername() {
            Account accountExists = accountService.findAccountByUsername(username);
            assertEquals(username, accountExists.getUsername());
    }

    @Test
    @DisplayName("Testing 'FindbyUsername' Function with null data expecting exception")
     void testFindbyUsernamewithNull() {
       assertThrows(RuntimeException.class, () -> accountService.findAccountByUsername(null),
               "Error User is empty");
    }

    @Test
    @DisplayName("Testing 'checkAccountExists' Function with null data expecting exception")

    void testcheckAccount(){

       Boolean exists = accountService.checkAccountExists(username,password);
       assertEquals(true, exists);
    }
    @Test
    @DisplayName("Testing 'checkAccountExists' Function with incorrect username data expecting false")
    void testcheckAccountwithFalseUsernamedata(){
        String newusername = "asdas";
        Boolean exists = accountService.checkAccountExists(newusername,password);
        assertEquals(false, exists);
    }

    @Test
    @DisplayName("Testing 'checkAccountExists' Function with incorrect username data expecting false")
    void testcheckAccountwithFalsePassworddata(){
        String newpassword = "passadad";
        Boolean exists = accountService.checkAccountExists(username,newpassword);
        assertEquals(false, exists);
    }





}
