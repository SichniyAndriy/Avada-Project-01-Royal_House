package edu.avada.course.service.impl;

import edu.avada.course.model.entity.Bid;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.repository.BidRepository;
import edu.avada.course.service.AdminBidService;
import java.util.Map;
import java.util.Set;
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
    public Set<Bid> getAllBids() {
        if (bids == null) {
            getAllBidsFromDB();
        }
        return bids.values().parallelStream().collect(Collectors.toSet());
    }

    @Override
    public Bid getBidById(long id) {
        if (bids == null) {
            getAllBidsFromDB();
        }
        return bids.get(id);
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

    private void getAllBidsFromDB() {
        bids = bidRepository.findAll().parallelStream().collect(Collectors.toMap(Bid::getId, val -> val));
    }
}
