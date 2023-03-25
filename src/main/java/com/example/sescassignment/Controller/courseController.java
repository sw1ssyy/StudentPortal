package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Service.courseService;
import org.springframework.data.repository.query.Param;
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
    public String viewCourses(Model model,@Param("keyword")String keyword){
        List<Course> courseList = service.SearchCoursebyName(keyword);
        model.addAttribute("courseList", courseList);
        model.addAttribute("keyword", keyword);
        return "Courses";
    }
}
