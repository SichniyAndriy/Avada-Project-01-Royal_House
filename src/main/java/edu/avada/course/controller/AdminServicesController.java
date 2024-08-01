package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import edu.avada.course.model.entity.CompanyService.ServiceStatus;
import edu.avada.course.service.AdminCompanyServService;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
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
@RequestMapping("admin/services")
public class AdminServicesController {
    private final AdminCompanyServService adminCompanyServService;
    private final Properties properties = new Properties();
    private final String SERVICE_BANNER_PATH_KEY = "service_banner";
    private final String PICTURES_PATH = "src/main/resources/support_files/pictures_paths";

    public AdminServicesController(
            @Autowired AdminCompanyServService adminCompanyServService
    ) {
        this.adminCompanyServService = adminCompanyServService;
    }

    @GetMapping
    public String getAllServices(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ){
        Page<AdminCompanyServiceDto> pageCompanyServices =
                adminCompanyServService.getPageCompanyServices(page, size);
        model.addAttribute("pageServices", pageCompanyServices);
        return "admin/services";
    }

    @GetMapping("/service-card/{id}")
    public String showService(
            @PathVariable long id,
            Model model
    ) {
        try {
            properties.load(new FileReader(PICTURES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String serviceBannerPath = properties.getProperty(SERVICE_BANNER_PATH_KEY);
        AdminCompanyServiceDto adminCompanyServiceDtoById = adminCompanyServService.getCompanyServiceById(id);
        model.addAttribute("service", adminCompanyServiceDtoById);
        model.addAttribute("serviceBannerPath", serviceBannerPath);
        return "admin/service_card";
    }

    @GetMapping("/change-service-status/{id}")
    public String changeServiceStatus(
            @PathVariable long id
    ) {
        adminCompanyServService.changeCompanyServiceStatusById(id);
        return "forward:/admin/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(
            @PathVariable int id
    ) {
        adminCompanyServService.deleteCompanyServiceById(id);
        return "forward:/admin/services";
    }

    @PostMapping("/update-service")
    public ResponseEntity<HttpStatus> editService(
            @RequestBody AdminCompanyServiceDto adminCompanyServiceDto
    ) {
        adminCompanyServService.updateCompanyService(adminCompanyServiceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/banner/save")
    public ResponseEntity<String> setMainBanner(
            @RequestParam("new-banner") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        String serviceBannerPath = Controllers.saveFile(
                path,
                multipartFile.getOriginalFilename(),
                timestamp,
                ext,
                multipartFile.getBytes());
        properties.put(SERVICE_BANNER_PATH_KEY, serviceBannerPath);
        properties.store(new FileWriter(PICTURES_PATH), null);
        return ResponseEntity.ok(serviceBannerPath);
    }

    @PostMapping("/previews/save")
    public ResponseEntity<String> savePreview(
            @RequestParam("new-preview") MultipartFile multipartFile,
            @RequestParam("path") String path,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("ext") String ext
    ) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String servicePreviewPath = Controllers.saveFile(
                path,
                multipartFile.getOriginalFilename(),
                timestamp,
                ext,
                multipartFile.getBytes()
        );
        return ResponseEntity.ok(servicePreviewPath);
    }

    @PostMapping("/add-new")
    public ResponseEntity<HttpStatus> addNewService(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("date") String date
    ) {
        AdminCompanyServiceDto adminCompanyServiceDto = new AdminCompanyServiceDto();
        adminCompanyServiceDto.setTitle(title);
        adminCompanyServiceDto.setDescription(description);
        adminCompanyServiceDto.setStatus(ServiceStatus.YES);
        adminCompanyServiceDto.setDate(LocalDate.parse(date));
        adminCompanyServService.add(adminCompanyServiceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
