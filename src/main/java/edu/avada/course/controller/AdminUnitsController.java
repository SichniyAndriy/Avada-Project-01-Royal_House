package edu.avada.course.controller;

import edu.avada.course.model.entity.Unit;
import edu.avada.course.service.AdminUnitService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/units")
public class AdminUnitsController {
    private final AdminUnitService adminUnitService;

    public AdminUnitsController(
            @Autowired AdminUnitService adminUnitService
    ) {
        this.adminUnitService = adminUnitService;
    }

    @GetMapping
    public String getAllUnits(Model model) {
        Set<Unit> allUnits = adminUnitService.getAllUnits();
        model.addAttribute("units", allUnits);
        return "admin/units";
    }

    @GetMapping("/show/{id}")
    public String showUnitById(
            @PathVariable long id,
            Model model
    ) {
        Unit unitById = adminUnitService.getUnitById(id);
        model.addAttribute("unit", unitById);
        return "admin/unit_card";
    }
}
