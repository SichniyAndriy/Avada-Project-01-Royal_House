package edu.avada.course.repository;

import edu.avada.course.model.entity.Unit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends PagingAndSortingRepository<Unit, Long> {
    Unit findById(long id);
    void deleteById(long id);
    Unit save(Unit unit);
}
