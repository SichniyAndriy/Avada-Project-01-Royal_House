package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import edu.avada.course.model.dto.CompanyServiceDto;
import edu.avada.course.model.entity.CompanyService;

public class CompanyServiceMapper {
    public static AdminCompanyServiceDto fromEntityToAdminDto(CompanyService companyService) {
        AdminCompanyServiceDto adminCompanyServiceDto = new AdminCompanyServiceDto();
        adminCompanyServiceDto.setId(companyService.getId());
        adminCompanyServiceDto.setTitle(companyService.getTitle());
        adminCompanyServiceDto.setDescription(companyService.getDescription());
        adminCompanyServiceDto.setDate(companyService.getDate());
        adminCompanyServiceDto.setStatus(companyService.getStatus());
        adminCompanyServiceDto.setImagePath(companyService.getImagePath());
        return adminCompanyServiceDto;
    }

    public static CompanyServiceDto fromEntityToDto(CompanyService companyService) {
        return new CompanyServiceDto(
                companyService.getTitle(),
                companyService.getDescription(),
                companyService.getDate(),
                companyService.getStatus(),
                companyService.getImagePath()
        );
    }

    public static CompanyService fromAdminDtoToEntity(AdminCompanyServiceDto adminCompanyServiceDto) {
        CompanyService newService = new CompanyService();
        newService.setId(adminCompanyServiceDto.getId());
        newService.setTitle(adminCompanyServiceDto.getTitle());
        newService.setDescription(adminCompanyServiceDto.getDescription());
        newService.setStatus(adminCompanyServiceDto.getStatus());
        newService.setDate(adminCompanyServiceDto.getDate());
        newService.setImagePath(adminCompanyServiceDto.getImagePath());
        return newService;
    }

    public static CompanyService fromDtoToEntity(CompanyServiceDto serviceDto) {
        CompanyService newService = new CompanyService();
        newService.setTitle(serviceDto.title());
        newService.setDescription(serviceDto.description());
        newService.setStatus(serviceDto.status());
        newService.setDate(serviceDto.date());
        newService.setImagePath(serviceDto.imagePath());
        return newService;
    }
}
