package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import org.springframework.data.domain.Page;

public interface AdminCompanyServService {
    Page<AdminCompanyServiceDto> getPageCompanyServices(int page, int size);

    AdminCompanyServiceDto getCompanyServiceById(long id);

    void deleteCompanyServiceById(long id);

    void changeCompanyServiceStatusById(long id);

    void updateCompanyService(AdminCompanyServiceDto companyService);

    long add(AdminCompanyServiceDto newCompanyService);
}
