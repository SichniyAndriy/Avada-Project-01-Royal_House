package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface AdminCompanyServService {
    List<AdminCompanyServiceDto> findAll();

    Page<AdminCompanyServiceDto> getPageCompanyServices(int page, int size);

    AdminCompanyServiceDto getCompanyServiceById(long id);

    void deleteCompanyServiceById(long id);

    void changeCompanyServiceStatusById(long id);

    void updateCompanyService(AdminCompanyServiceDto companyService);

    long add(AdminCompanyServiceDto newCompanyService);
}
