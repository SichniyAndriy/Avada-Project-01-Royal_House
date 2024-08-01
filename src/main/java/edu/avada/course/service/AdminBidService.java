package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminBidDto;
import org.springframework.data.domain.Page;

public interface AdminBidService {
    //--------------------- BIDS PART ---------------------\\
    Page<AdminBidDto> getBidPage(int page, int size);

    AdminBidDto getBidById(long id);

    void deleteBidById(long id);

    void changeBidStatusById(long i);

    long add(AdminBidDto adminBidDto);
}
