package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.service.AdminNewBldsService;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/new-blds"})
public class NewBldsController {
    private final AdminNewBldsService adminNewBldsService;

    public NewBldsController(
            @Autowired AdminNewBldsService adminNewBldsService
    ) {
        this.adminNewBldsService = adminNewBldsService;
    }

    @GetMapping
    public String indexPage(Model model) throws IOException {
        List<AdminNewBuildingDto> allNewBuildings = adminNewBldsService.getAllNewBuildings();
        Properties properties = new Properties();
        properties.load(new FileReader(Controllers.BINDING_FILE_PATH));

        for (var entry: properties.entrySet()) {
            for (var newBuilding: allNewBuildings) {
                if (newBuilding.getTitle().equals(entry.getValue())) {
                    model.addAttribute((String) entry.getKey(), newBuilding);
                    allNewBuildings.remove(newBuilding);
                    break;
                }
            }
        }
        model.addAttribute("newblds", allNewBuildings);
        return "client/newblds";
    }

    @GetMapping("/show-newbld-card/{id}")
    public String showNewBld(
            @PathVariable Long id,
            Model model) {
        AdminNewBuildingDto newBldById = adminNewBldsService.getNewBldById(id);
        model.addAttribute("newbld", newBldById);
        return "client/newbld_card";
    }
}
