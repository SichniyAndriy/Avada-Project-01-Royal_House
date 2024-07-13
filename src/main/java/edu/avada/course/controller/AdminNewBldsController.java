package edu.avada.course.controller;

import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.service.AdminNewBldsService;
import java.io.IOException;
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

    public AdminNewBldsController(
            @Autowired AdminNewBldsService adminNewBldsService
    ) {
        this.adminNewBldsService = adminNewBldsService;
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
    public ResponseEntity addInfographic(@RequestBody NewBuilding newBuilding) {
        adminNewBldsService.updateNewBld(newBuilding);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/banners/set")
    @ResponseBody
    public ResponseEntity setMainBanner(
            @RequestParam("new-banner") MultipartFile multipartFile,
            @RequestParam("newbldIndex") int newBldIndex,
            @RequestParam("bannerIndex") int bannerIndex
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

//        Controllers.saveFile(
//                multipartFile.getOriginalFilename(),
//                multipartFile.getBytes()
//        );
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/panoramas/set")
    @ResponseBody
    public ResponseEntity setPanorama(
            @RequestParam("new-panorama") MultipartFile multipartFile,
            @RequestParam("newBldIndex") int newBldIndex
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
//        Controllers.saveFile(
//                multipartFile.getOriginalFilename(),
//                multipartFile.getBytes()
//        );
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
