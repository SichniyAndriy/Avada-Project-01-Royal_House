package edu.avada.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about_company")
public class AboutController {
    @GetMapping
    public String aboutCompany() {
        return "client/about_company";
    }
}
