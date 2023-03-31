package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Service.accountService;
import com.example.sescassignment.Service.enrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;


@Controller
public class accountController {
    private final accountService service;
    private final enrollmentService enrollService;

    public String currentUser = "";
    public accountController(accountService service, enrollmentService enrollService) {
        this.service = service;
        this.enrollService = enrollService;
    }
    @GetMapping(value = "/login")
    public ModelAndView accountLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("account", new Account());

        return modelAndView;
    }
    @PostMapping(value = "/login")
    public ModelAndView checkLogin(Account account) {
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Account: '" + account.getUsername() + "' Login Success!");
            currentUser = account.getUsername();
            return getHomePage();
        }
        else
            System.out.println("Account: '" + account.getUsername() + "' Login Failed!");
        return new ModelAndView("login-failed");
    }
    @GetMapping(value = "/signup")
    public ModelAndView accountRegister() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }
        @PostMapping(value = "/signup")
    public ModelAndView accountRegisterSuccess(  Account account) {
        //If the account exists
        if(service.checkAccountExists(account.getUsername(), account.getPassword())){
            System.out.println("Error: Account Already Exists");
            return new ModelAndView("signup-failed");
        }
        //If account does not exist
            service.createNewAccount(account);
            service.createFinanceAccount(account);
            service.createLibraryAccount(account);
            ModelAndView modelAndView = new ModelAndView("signup-success");
            modelAndView.addObject("account", account);
            return modelAndView;
    }
    @GetMapping(value = "/home")
    public ModelAndView getHomePage(){
       Account savedaccount = service.findAccountByUsername(currentUser);
       ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user",savedaccount);
        return modelAndView;
    }
    @GetMapping(value = "home/edit/{id}")
    public String getEditProfilePage(@PathVariable Long id, Model model ){
        Account savedAccount = service.findAccountByUsername(currentUser);
        model.addAttribute("user", savedAccount);
        return "edit-profile";
    }
    @PostMapping(value = "home/edit/{id}")
    public ModelAndView putEditProfile(@PathVariable Long id,  Account account ){
         service.updateAccount(id, account);
        currentUser = account.getUsername();
         return getHomePage();
    }

    @GetMapping(value = "/graduation")
    public ModelAndView viewGraduation(){
        Account account = service.findAccountByUsername(currentUser);
        Boolean GradStatus = enrollService.canGraduate(account);
        ModelAndView modelAndView = new ModelAndView("Graduation");
        modelAndView.addObject("status", GradStatus);
        return modelAndView;
    }


    @GetMapping(value = "/enrollment")
    public ModelAndView viewAllEnrollments() {
        Account user = service.findAccountByUsername(currentUser);
       Set<Course> coursesSet = user.getCoursesEnrolledIn();
        List<Course> courseList = coursesSet.stream().toList();
        ModelAndView modelAndView = new ModelAndView("myCourses");
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }



}
