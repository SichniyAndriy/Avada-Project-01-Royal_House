package edu.avada.course.repository;

import edu.avada.course.model.entity.CompanyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyServiceRepository extends JpaRepository<CompanyService, Long> {
}
