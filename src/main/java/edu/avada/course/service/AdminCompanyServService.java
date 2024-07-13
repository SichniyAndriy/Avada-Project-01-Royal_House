package edu.avada.course.service;

import edu.avada.course.model.entity.CompanyService;
import java.util.Set;

public interface AdminCompanyServService {
    Set<CompanyService> getAllCompanyServices();

    CompanyService getCompanyServiceById(long id);

    void deleteCompanyServiceById(long id);

    void changeCompanyServiceStatusById(long id);

    void updateCompanyService(CompanyService companyService);
}
