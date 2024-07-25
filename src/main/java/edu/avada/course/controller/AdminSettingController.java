package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.service.AdminNewBldsService;
import jakarta.annotation.Nullable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminSettingController {
    private final String CONTACTS_FILE_PATH = "src/main/resources/support_files/contacts";
    private final String BINDING_FILE_PATH = "src/main/resources/support_files/binding";
    private final String ABOUT_FILE_PATH = "src/main/resources/support_files/about";
    private final AdminNewBldsService adminNewBldsService;

    public AdminSettingController(
            @Autowired AdminNewBldsService adminNewBldsService
    ) {
        this.adminNewBldsService = adminNewBldsService;
    }

    @GetMapping("/contacts")
    public String contacts(Model model) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(CONTACTS_FILE_PATH));

        model.addAttribute("phone", properties.getProperty("phone"));
        model.addAttribute("viber", properties.getProperty("viber"));
        model.addAttribute("telegram", properties.getProperty("telegram"));
        model.addAttribute("email", properties.getProperty("email"));
        model.addAttribute("instagram", properties.getProperty("instagram"));
        model.addAttribute("facebook", properties.getProperty("facebook"));
        model.addAttribute("address", properties.getProperty("address"));

        return "admin/setting/contacts";
    }

    @PostMapping("/contacts")
    public String saveContacts(
            @RequestParam("phone") String phone,
            @RequestParam("viber") String viber,
            @RequestParam("telegram") String telegram,
            @RequestParam("email") String email,
            @RequestParam("instagram") String instagram,
            @RequestParam("facebook") String facebook,
            @RequestParam("address") String address
    ) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("phone", phone);
        properties.setProperty("viber", viber);
        properties.setProperty("telegram", telegram);
        properties.setProperty("email", email);
        properties.setProperty("instagram", instagram);
        properties.setProperty("facebook", facebook);
        properties.setProperty("address", address);
        properties.store(new FileWriter(CONTACTS_FILE_PATH), null);
        return "redirect:/admin/contacts";
    }

    @GetMapping("/binding")
    public String binding(Model model) throws IOException {
        List<String> titleList =
                adminNewBldsService.getAllNewBlds().stream().map(AdminNewBuildingDto::getTitle).toList();

        model.addAttribute("titleList", titleList);
        Properties properties = new Properties();
        properties.load(new FileReader(BINDING_FILE_PATH));
        for(var entry : properties.entrySet()) {
            model.addAttribute((String) entry.getKey(), entry.getValue());
        }
        return "admin/setting/binding";
    }

    @PostMapping("/binding/save")
    public String saveBinding(
            @RequestBody List<String> fieldList
    ) throws IOException {
        Properties properties = new Properties();
        int i = 0;
        for(var item : fieldList) {
            properties.setProperty("field" + (++i), item);
        }
        properties.store(new FileWriter(BINDING_FILE_PATH), null );
        return "redirect:/admin/binding";
    }


    @GetMapping("/secondary")
    public String secondary() {
        return "admin/setting/secondary";
    }

    @GetMapping("/about")
    public String about(Model model) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(ABOUT_FILE_PATH));
        for(var entry : properties.entrySet()) {
            model.addAttribute((String) entry.getKey(), entry.getValue());
        }
        return "admin/setting/about";
    }

    @PostMapping("/about")
    public String saveAbout(
            @RequestParam("title") String title,
            @RequestParam("desc") String desc,
            @Nullable @RequestParam(name = "bannerUrl", required = false) String bannerUrl
    ) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(ABOUT_FILE_PATH));
        properties.setProperty("title", title);
        properties.setProperty("desc", desc);
        Optional.ofNullable(bannerUrl).ifPresent(url -> properties.setProperty("bannerUrl", url));
        properties.store(new FileWriter(ABOUT_FILE_PATH), null);
        return "redirect:/admin/contacts";
    }

    @PostMapping("about/save-banner")
    public ResponseEntity<String> saveAboutBanner(
            @RequestParam("new-about-banner")MultipartFile multipartFile,
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

}
