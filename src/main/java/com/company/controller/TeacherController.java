package com.company.controller;

import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by chenyj on 16/1/14.
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "getstudents", method = RequestMethod.GET)
    public ModelAndView getStudents(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("teacher");
        List<Student> students = studentService.getByTeacher(name);
        modelAndView.addObject("students", students);
        modelAndView.addObject("teacher", name);
        return modelAndView;
    }
}
