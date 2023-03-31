package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Account;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Model.Invoice;
import com.example.sescassignment.Service.accountService;
import com.example.sescassignment.Service.courseService;
import com.example.sescassignment.Service.enrollmentService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class courseController {
    private final courseService service;
    private final accountController controller;
    private final accountService accservice;

    private final enrollmentService enrollService;


    public courseController(courseService service, accountController controller, accountService accservice, enrollmentService enrollService) {
        this.service = service;
        this.controller = controller;
        this.accservice = accservice;
        this.enrollService = enrollService;
    }

    @GetMapping(value = "/courses")
    public ModelAndView viewCourses(@Param("keyword")String keyword){
        List<Course> courseList = service.SearchCoursebyName(keyword);
        ModelAndView modelAndView = new ModelAndView("Courses");
        modelAndView.addObject("courseList", courseList);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }
    @GetMapping(value = "/courses/{id}")
    public ModelAndView viewSingleCourse(@PathVariable Long id){
        Course selectedCourse = service.findCourseByID(id);
        ModelAndView modelAndView = new ModelAndView("CourseDetail");
        modelAndView.addObject("course", selectedCourse);
        return modelAndView;
    }
    @GetMapping(value = "courses/{id}/enroll")
    public ModelAndView viewEnrollment(@PathVariable Long id){
        Account account = findAccount();
        Course selectedCourse = service.findCourseByID(id);
        enrollService.enrollStudentInCourse(account,selectedCourse);
        Invoice response = enrollService.CreateEnrollmentInvoice(account,selectedCourse);
        ModelAndView modelAndView = new ModelAndView("enrollment-confirmed");
        modelAndView.addObject("response", response);
        modelAndView.addObject("course", selectedCourse);
        modelAndView.addObject("user", account);
        return modelAndView;
    }

    public Account findAccount(){
        String currentAccount = controller.currentUser;
        return accservice.findAccountByUsername(currentAccount);
    }
}
