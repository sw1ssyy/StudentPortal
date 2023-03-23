package com.example.sescassignment.Controller;

import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Service.accountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class accountController {
    private final accountService service;

    public accountController(accountService service) {
        this.service = service;

    }


    @GetMapping(value = "/login")
    public String accountLogin(Model model){
        model.addAttribute("account",new Account());
        return "login";
    }
    @PostMapping(value = "/login")
    public String checkLogin(Account account){
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Account Exists");
            return "home";
        }
        else
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

}
