package edu.avada.course.repository;

import edu.avada.course.model.entity.CompanyService;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyServiceRepository extends PagingAndSortingRepository<CompanyService, Long> {
    List<CompanyService> findAll();

    CompanyService findById(Long id);

    void deleteById(Long id);

    CompanyService save(CompanyService companyService);
}
