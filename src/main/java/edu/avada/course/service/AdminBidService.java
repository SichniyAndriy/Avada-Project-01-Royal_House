package edu.avada.course.service;

import edu.avada.course.model.entity.Bid;
import java.util.Set;

public interface AdminBidService {
    //--------------------- BIDS PART ---------------------\\
    Set<Bid> getAllBids();

    Bid getBidById(long id);

    void deleteBidById(long id);

    void changeBidStatusById(long i);
}
