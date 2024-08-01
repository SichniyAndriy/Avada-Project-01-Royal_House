package edu.avada.course.service.impl;

import edu.avada.course.mapper.BidMapper;
import edu.avada.course.model.admindto.AdminBidDto;
import edu.avada.course.model.entity.Bid;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.repository.BidRepository;
import edu.avada.course.service.AdminBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminBidServiceImpl implements AdminBidService {
    private final BidRepository bidRepository;

    public AdminBidServiceImpl(
            @Autowired BidRepository bidRepository
    ){
        this.bidRepository = bidRepository;
    }

    @Override
    public Page<AdminBidDto> getBidPage(int page, int size) {
        Page<Bid> bidPage = bidRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        return bidPage.map(BidMapper::fromEntityToAdminDto);
    }

    @Override
    public AdminBidDto getBidById(long id) {
        Bid byId = bidRepository.findById(id);
        return BidMapper.fromEntityToAdminDto(byId);
    }

    @Override
    public void deleteBidById(long id) {
        bidRepository.deleteById(id);
    }

    @Override
    public void changeBidStatusById(long id) {
        Bid bid = bidRepository.findById(id);
        BidStatus newStatus =
                bid.getStatus() == BidStatus.ANSWERED ? BidStatus.NEW : BidStatus.ANSWERED;
        bid.setStatus(newStatus);
        bidRepository.save(bid);
    }

    public long add(AdminBidDto adminBidDto) {
        Bid bid = BidMapper.fromAdminDtoToEntity(adminBidDto);
        return bidRepository.save(bid).getId();
    }
}
