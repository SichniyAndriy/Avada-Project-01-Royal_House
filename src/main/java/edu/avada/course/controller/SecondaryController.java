package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.service.SecondaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secondary")
public class SecondaryController {
    private final SecondaryService secondaryService;

    public SecondaryController(
            @Autowired SecondaryService secondaryService
    ) {
        this.secondaryService = secondaryService;
    }

    @GetMapping
    public String secondary(Model model) {
        List<AdminUnitDto> secondaryUnits = secondaryService.getAllSecondaryUnits();
        model.addAttribute("units", secondaryUnits);
        return "client/secondary";
    }

    @GetMapping("/{id}")
    public String secondary(
            @PathVariable long id,
            Model model
    ) {
        model.addAttribute("unit", secondaryService.getSecondaryUnitById(id));
        return "client/secondary_card";
    }
}
