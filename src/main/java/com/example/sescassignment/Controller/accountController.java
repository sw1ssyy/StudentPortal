package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Service.accountService;
import com.example.sescassignment.Service.intergrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class accountController {
    private final accountService service;
    private final intergrationService service2;

    private String currentUser = "";
    public accountController(accountService service, intergrationService service2) {
        this.service = service;


        this.service2 = service2;
    }
    @GetMapping(value = "/login")
    public String accountLogin(Model model){
        model.addAttribute("account",new Account());
        return "login";
    }
    @PostMapping(value = "/login")
    public String checkLogin(Account account ,Model model) {
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Account: '" + account.getUsername() + "' Login Success!");
            currentUser = account.getUsername();
            return getHomePage(model);
        }
        else
            System.out.println("Account: '" + account.getUsername() + "' Login Failed!");
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
            service2.createFinanceAccount(account);
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
    @PostMapping(value = "home/edit/{id}")
    public String putEditProfile(Model model, @PathVariable Long id,  Account account ){
         service.updateAccount(id, account);
        currentUser = account.getUsername();
         return getHomePage(model);
    }

    @GetMapping(value = "/enrollment")
    public String getEnrollmentPage(){
        return "enrollment";
    }

}
