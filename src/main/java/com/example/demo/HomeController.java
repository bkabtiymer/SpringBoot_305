package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository coursRepository;

    @RequestMapping("/")
    public String index(Model model){
//        First create an actor
        Student student= new Student();
        student.setFirstname("Melissa");
        student.setLastname("Mcarthy");

//        Now create a movie
        Course course = new Course();
        course.setTitle("Math");
        course.setInstructor("Dave chapelle");
        course.setCredits(3);

//        Add the movie to the empty list
        Set<Course> courses= new HashSet<Course>(); //Courses= new set of courses
        courses.add(course);

        course =new Course();
        course.setTitle("English");
        course.setInstructor("Jennifer Yifty");
        course.setCredits(4);
        courses.add(course);
//        Add the list of movies to the actor's movie list
        student.setCourses(courses);

//        Save the actor to the database
        studentRepository.save(student);

//        Grad all the actors from the database and send them to the template
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }
}
