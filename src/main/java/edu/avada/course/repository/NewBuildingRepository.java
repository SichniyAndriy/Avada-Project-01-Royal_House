package edu.avada.course.repository;

import edu.avada.course.model.entity.NewBuilding;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewBuildingRepository extends PagingAndSortingRepository<NewBuilding, Long> {
    List<NewBuilding> findAll();
    Optional<NewBuilding> findById(long id);
    void deleteById(long id);
    NewBuilding save(NewBuilding newBuilding);
}
