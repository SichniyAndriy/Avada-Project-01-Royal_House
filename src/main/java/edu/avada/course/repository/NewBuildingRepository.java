package edu.avada.course.repository;

import edu.avada.course.model.entity.NewBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewBuildingRepository extends JpaRepository<NewBuilding, Long> {
}
