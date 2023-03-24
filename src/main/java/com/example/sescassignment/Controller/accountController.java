package com.example.sescassignment.Controller;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Service.accountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class accountController {
    private final accountService service;
    private String currentUser = "";
    public accountController(accountService service) {
        this.service = service;

    }
    @GetMapping(value = "/login")
    public String accountLogin(Model model){
        model.addAttribute("account",new Account());
        return "login";
    }
    @PostMapping(value = "/login")
    public String checkLogin(Account account ,Model model) {
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Account: '" + account.getUsername() + "' Exists!");
            currentUser = account.getUsername();
            return getHomePage(model);
        }
        else
            System.out.println("Account: '" + account.getUsername() + "' Doesn't Exist!");
        return "login-failed";
    }
    @GetMapping(value = "/signup")
    public String accountRegister(Model model){
        model.addAttribute("account", new Account());
        return "signup";
    }
    @PostMapping("/signup")
    public String accountRegisterSuccess( Account account, Model model) {
        //If the account exists
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Error: Account Already Exists");
            return "signup-failed";

        }
        //If account does not exist
            service.createNewAccount(account);
            model.addAttribute("account", account);
            return "signup-success";
    }
    @GetMapping(value = "/home")
    public String getHomePage(Model model){
       Account savedaccount = service.findAccountByUsername(currentUser);
        model.addAttribute("user",savedaccount);
        return "home";
    }
    @GetMapping(value = "home/edit/{id}")
    public String getEditProfilePage(@PathVariable Long id, Model model ){
        Account savedAccount = service.findAccountByUsername(currentUser);
        model.addAttribute("user", savedAccount);
        return "edit-profile";
    }
    @PutMapping(value = "home/edit/{id}")
    public ResponseEntity<Account> putEditProfile( @PathVariable Long id, @RequestBody Account account){
        return service.updateAccount(id, account);
    }

}
