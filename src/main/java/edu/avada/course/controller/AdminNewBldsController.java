package edu.avada.course.controller;

import edu.avada.course.model.entity.Address;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.Location;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.service.AdminAddressService;
import edu.avada.course.service.AdminNewBldsService;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("admin/new-blds")
public class AdminNewBldsController {
    private final AdminNewBldsService adminNewBldsService;
    private final AdminAddressService adminAddressService;

    public AdminNewBldsController(
            @Autowired AdminNewBldsService adminNewBldsService,
            @Autowired AdminAddressService adminAddressService
    ) {
        this.adminNewBldsService = adminNewBldsService;
        this.adminAddressService = adminAddressService;
    }

    @GetMapping
    public String showNewBlds(Model model) {
        Set<NewBuilding> allNewBlds = adminNewBldsService.getAllNewBlds();
        model.addAttribute("newblds", allNewBlds);
        return "admin/new_blds";
    }

    @GetMapping("/show-newbld-card/{id}")
    public String showNewBld(@PathVariable long id, Model model) {
        NewBuilding newBuildingById = adminNewBldsService.getNewBldById(id);
        Optional.of(newBuildingById.getInfographics()).ifPresent(item -> item.forEach(el -> el.setNewBuilding(null)));
        Optional.of(newBuildingById.getBanners()).ifPresent(item -> item.forEach(el -> el.setNewBuilding(null)));
        model.addAttribute("newbld", newBuildingById);
        return "admin/new_bld_card";
    }

    @GetMapping("/change-new-bld-status/{id}")
    public String changeNewBldStatus(@PathVariable long id) {
        adminNewBldsService.changeNewBldStatusById(id);
        return "forward:/admin/new-blds";
    }

    @GetMapping("/delete-new-bld/{id}")
    public String deleteNewBld(@PathVariable int id){
        adminNewBldsService.deleteNewBldById(id);
        return "forward:/admin/new-blds";
    }

    @PostMapping("/update-new-bld")
    public ResponseEntity<HttpStatus> addInfographic(@RequestBody NewBuilding newBuilding) {
        for(var banner: newBuilding.getBanners()) {
            banner.setNewBuilding(newBuilding);
        }
        for (var infographic: newBuilding.getInfographics()) {
            infographic.setNewBuilding(newBuilding);
        }
        adminNewBldsService.updateNewBld(newBuilding);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/banners/add-new")
    @ResponseBody
    public ResponseEntity<String> setBanner(
            @RequestParam("new-banner") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        String result = Controllers.saveFile(
                path, multipartFile.getOriginalFilename(), timestamp, ext,
                multipartFile.getBytes()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/infographics/add-new")
    @ResponseBody
    public ResponseEntity<String> setInfographic(
            @RequestParam("new-infographic") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        String result = Controllers.saveFile(
                path, multipartFile.getOriginalFilename(), timestamp, ext,
                multipartFile.getBytes()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/panoramas/add-new")
    @ResponseBody
    public ResponseEntity<String> setPanorama(
            @RequestParam("new-panorama") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        String result = Controllers.saveFile(
                path, multipartFile.getOriginalFilename(), timestamp, ext,
                multipartFile.getBytes()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add-new")
    public String addNewBld(
            @RequestParam("title") String title,
            @RequestParam("address") String address
    ) {
        NewBuilding newBuilding = new NewBuilding();
        newBuilding.setTitle(title);
        String[] strings = address.split("[.,:;]");
        Address addressObj = new Address();
        if (strings.length != 3) {
            throw new IllegalArgumentException();
        }
        addressObj.setCity(strings[0].replace(" ", ""));
        addressObj.setStreet(strings[1].replace(" ", ""));
        addressObj.setHouseNumber(strings[2].replace(" ", ""));
        adminAddressService.add(addressObj);
        newBuilding.setAddress(addressObj);
        newBuilding.setStatus(NewBuildStatus.OFF);
        newBuilding.setLocation(new Location());
        adminNewBldsService.add(newBuilding);
        return "redirect:/admin/new-blds";
    }
}
