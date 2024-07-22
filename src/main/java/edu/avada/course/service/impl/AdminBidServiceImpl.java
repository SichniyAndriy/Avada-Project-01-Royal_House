package edu.avada.course.service.impl;

import edu.avada.course.mapper.BidMapper;
import edu.avada.course.model.admindto.AdminBidDto;
import edu.avada.course.model.entity.Bid;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.repository.BidRepository;
import edu.avada.course.service.AdminBidService;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBidServiceImpl implements AdminBidService {
    private final BidRepository bidRepository;
    private Map<Long, Bid> bids;

    public AdminBidServiceImpl(
            @Autowired BidRepository bidRepository
    ){
        this.bidRepository = bidRepository;
    }

    @Override
    public Set<AdminBidDto> getAllBids() {
        if (bids == null) {
            getAllBidsFromDB();
        }
        return bids.values().parallelStream()
                .map(BidMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(AdminBidDto::getId)))
                );
    }

    @Override
    public AdminBidDto getBidById(long id) {
        if (bids == null) {
            getAllBidsFromDB();
        }
        return BidMapper.fromEntityToAdminDto(bids.get(id));
    }

    @Override
    public void deleteBidById(long id) {
        Bid bid = bids.get(id);
        bids.remove(id);
        bidRepository.delete(bid);
    }

    @Override
    public void changeBidStatusById(long i) {
        Bid bid = bids.get(i);
        BidStatus newStatus = bid.getStatus() == BidStatus.ANSWERED ? BidStatus.NEW : BidStatus.ANSWERED;
        bid.setStatus(newStatus);
        bidRepository.save(bid);
    }

    public long add(AdminBidDto adminBidDto) {
        Bid bid = BidMapper.fromAdminDtoToEntity(adminBidDto);
        long id = bidRepository.save(bid).getId();
        if (id > 0) {
            bids.put(id, bid);
        }
        return id;
    }

    private void getAllBidsFromDB() {
        bids = bidRepository.findAll().parallelStream().collect(Collectors.toMap(Bid::getId, val -> val));
    }
}
