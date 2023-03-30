package com.example.sescassignment.Controller;
import com.example.sescassignment.Model.Course;
import com.example.sescassignment.Service.courseService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class courseController {
    private final courseService service;

    public courseController(courseService service) {
        this.service = service;
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
}
