package edu.avada.course.controller;

import edu.avada.course.model.entity.Unit;
import edu.avada.course.model.entity.Unit.UnitType;
import edu.avada.course.service.AdminUnitService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/add-new")
    public String addNewUnit(
            @RequestParam("unitType") String unitType,
            @RequestParam("square") BigDecimal square,
            @RequestParam("totalPrice") BigDecimal totalPrice,
            @RequestParam("pricePerSqM") BigDecimal pricePerSqM,
            @RequestParam("rooms") int rooms,
            @RequestParam("floor") int floor,
            @RequestParam("totalFloors") int totalFloors,
            @RequestParam("date") LocalDate date
    ) {
        Unit newUnit = new Unit();
        newUnit.setType(UnitType.valueOf(unitType));
        newUnit.setSquare(square);
        newUnit.setTotalPrice(totalPrice);
        newUnit.setPricePerSqM(pricePerSqM);
        newUnit.setRooms(rooms);
        newUnit.setFloor(floor);
        newUnit.setTotalFloors(totalFloors);
        newUnit.setDate(date);
        adminUnitService.add(newUnit);
        return "redirect:/admin/units";
    }
}
