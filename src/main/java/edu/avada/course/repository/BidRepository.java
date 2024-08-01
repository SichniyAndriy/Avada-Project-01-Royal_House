package edu.avada.course.repository;

import edu.avada.course.model.entity.Bid;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends PagingAndSortingRepository<Bid, Long> {
    Bid findById(Long id);
    void deleteById(Long id);
    Bid save(Bid bid);
}
