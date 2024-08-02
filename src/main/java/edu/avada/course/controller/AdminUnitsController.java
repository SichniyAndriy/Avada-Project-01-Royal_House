package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminAddressDto;
import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.entity.Unit.UnitType;
import edu.avada.course.service.AdminAddressService;
import edu.avada.course.service.AdminUnitService;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/units")
public class AdminUnitsController {
    private final AdminUnitService adminUnitService;
    private final AdminAddressService adminAddressService;
    public AdminUnitsController(
            @Autowired AdminUnitService adminUnitService,
            @Autowired AdminAddressService adminAddressService
    ) {
        this.adminUnitService = adminUnitService;
        this.adminAddressService = adminAddressService;
    }

    @GetMapping
    public String getAllUnits(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        Page<AdminUnitDto> unitsPage = adminUnitService.pageUnits(page, size);
        List<AdminAddressDto> addresses = adminAddressService.getAllAddresses();
        model.addAttribute("unitPage", unitsPage);
        model.addAttribute("addresses", addresses);
        return "admin/units";
    }

    @GetMapping("/show/{id}")
    public String showUnitById(
            @PathVariable long id,
            Model model
    ) {
        AdminUnitDto adminUnitDtoById = adminUnitService.getUnitById(id);
        model.addAttribute("unit", adminUnitDtoById);
        return "admin/unit_card";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUnitById(
            @PathVariable long id
    ) {
        adminUnitService.deleteUnitById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-unit")
    public ResponseEntity<HttpStatus> updateUnit(
          @RequestBody AdminUnitDto adminUnitDto
    ) {
        adminUnitService.updateUnit(adminUnitDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/image/save")
    public ResponseEntity<String> setImage(
            @RequestParam("new-image") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        String unitImagePath = Controllers.saveFile(
                path,
                multipartFile.getOriginalFilename(),
                timestamp,
                ext,
                multipartFile.getBytes());
        return ResponseEntity.ok(unitImagePath);
    }

    @PostMapping("/add-new")
    public ResponseEntity<HttpStatus> addNewUnit(
            @RequestParam("unitType") String unitType,
            @RequestParam("square") BigDecimal square,
            @RequestParam("address") String address,
            @RequestParam("totalPrice") BigDecimal totalPrice,
            @RequestParam("pricePerSqM") BigDecimal pricePerSqM,
            @RequestParam("rooms") int rooms,
            @RequestParam("floor") int floor,
            @RequestParam("totalFloors") int totalFloors,
            @RequestParam("flatNumber") int flatNumber,
            @RequestParam(name = "newBuilding", required = false) @Nullable Long newBuilding,
            @RequestParam("date") LocalDate date
    ) {
        AdminUnitDto adminUnitDto = new AdminUnitDto();
        adminUnitDto.setType(UnitType.valueOf(unitType));
        adminUnitDto.setSquare(square);
        String[] strings = address.split("[-,:;]");
        AdminAddressDto adminAddressDto = new AdminAddressDto();
        if (strings.length != 3) {
            throw new IllegalArgumentException();
        }
        adminAddressDto.setCity(strings[0].trim());
        adminAddressDto.setStreet(strings[1].trim());
        adminAddressDto.setHouseNumber(strings[2].trim());
        adminUnitDto.setAddress(adminAddressDto);
        adminUnitDto.setTotalPrice(totalPrice);
        adminUnitDto.setPricePerSqM(pricePerSqM);
        adminUnitDto.setRooms(rooms);
        adminUnitDto.setFloor(floor);
        adminUnitDto.setTotalFloors(totalFloors);
        adminUnitDto.setFlatNumber(flatNumber);
        adminUnitDto.setDate(date);
        adminUnitDto.setNewBuilding(newBuilding);
        adminUnitService.add(adminUnitDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
