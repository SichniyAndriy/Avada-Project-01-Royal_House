package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import java.util.Set;

public interface AdminCompanyServService {
    Set<AdminCompanyServiceDto> getAllCompanyServices();

    AdminCompanyServiceDto getCompanyServiceById(long id);

    void deleteCompanyServiceById(long id);

    void changeCompanyServiceStatusById(long id);

    void updateCompanyService(AdminCompanyServiceDto companyService);

    long add(AdminCompanyServiceDto newCompanyService);
}
