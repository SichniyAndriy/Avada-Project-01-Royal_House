package edu.avada.course.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about-company")
public class AboutController {
    @GetMapping
    public String aboutCompany(Model model) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader(Controllers.ABOUT_FILE_PATH));
        model.addAttribute("title", prop.getProperty("title"));
        model.addAttribute("desc", prop.getProperty("desc"));
        model.addAttribute("bannerUrl", prop.getProperty("bannerUrl"));
        return "client/about_company";
    }
}
