package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminBidDto;
import java.util.Set;

public interface AdminBidService {
    //--------------------- BIDS PART ---------------------\\
    Set<AdminBidDto> getAllBids();

    AdminBidDto getBidById(long id);

    void deleteBidById(long id);

    void changeBidStatusById(long i);

    long add(AdminBidDto adminBidDto);
}
