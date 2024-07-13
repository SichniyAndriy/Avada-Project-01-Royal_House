package edu.avada.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping
    public String indexPage() {
        return "index";
    }

    @ModelAttribute("username")
    public String getUsername() {
        return "Vasya";
    }
}
