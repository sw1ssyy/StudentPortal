package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Service.courseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class courseController {
    private final courseService service;

    public courseController(courseService service) {
        this.service = service;
    }

    @GetMapping(value = "/courses")
    public String viewCourses(Model model){
        List<Course> courseList = service.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "Courses";
    }
}
