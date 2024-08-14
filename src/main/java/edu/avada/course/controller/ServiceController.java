package edu.avada.course.controller;

import edu.avada.course.service.AdminCompanyServService;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final String SERVICE_BANNER_PATH_KEY = "service_banner";
    private final String PICTURES_PATH = "src/main/resources/support_files/pictures_paths";
    private final AdminCompanyServService adminCompanyServService;

    public ServiceController(
            @Autowired AdminCompanyServService adminCompanyServService
    ) {
        this.adminCompanyServService = adminCompanyServService;
    }

    @GetMapping
    public String services(Model model) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader(PICTURES_PATH));
        model.addAttribute("banner", prop.getProperty(SERVICE_BANNER_PATH_KEY));
        model.addAttribute("services", adminCompanyServService.findAll());
        return "client/services";
    }

    @GetMapping("/{id}")
    public String getService(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("service",adminCompanyServService.getCompanyServiceById(id));
        return "client/service_card";
    }
}
