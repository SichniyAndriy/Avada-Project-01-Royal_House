package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminBidDto;
import edu.avada.course.model.dto.BidDto;
import edu.avada.course.model.entity.Bid;

public class BidMapper {
    public static AdminBidDto fromEntityToAdminDto(Bid bid) {
        AdminBidDto newAdminBidDto = new AdminBidDto();
        newAdminBidDto.setId(bid.getId());
        newAdminBidDto.setName(bid.getName());
        newAdminBidDto.setPhone(bid.getPhone());
        newAdminBidDto.setEmail(bid.getEmail());
        newAdminBidDto.setComment(bid.getComment());
        newAdminBidDto.setDate(bid.getDate());
        newAdminBidDto.setStatus(bid.getStatus());
        return newAdminBidDto;
    }

    public static BidDto fromEntityToDto(Bid bid) {
        return new BidDto(
                bid.getName(),
                bid.getPhone(),
                bid.getEmail(),
                bid.getComment(),
                bid.getDate(),
                bid.getStatus()
        );
    }

    public static Bid fromAdminDtoToEntity(AdminBidDto adminBidDto) {
        Bid newBid = new Bid();
        newBid.setId(adminBidDto.getId());
        newBid.setName(adminBidDto.getName());
        newBid.setPhone(adminBidDto.getPhone());
        newBid.setEmail(adminBidDto.getEmail());
        newBid.setComment(adminBidDto.getComment());
        newBid.setDate(adminBidDto.getDate());
        newBid.setStatus(adminBidDto.getStatus());
        return newBid;
    }

    public static Bid fromDtoToEntity(BidDto bidDto) {
        Bid newBid = new Bid();
        newBid.setName(bidDto.name());
        newBid.setPhone(bidDto.phone());
        newBid.setEmail(bidDto.email());
        newBid.setComment(bidDto.comment());
        newBid.setDate(bidDto.date());
        newBid.setStatus(bidDto.status());
        return newBid;
    }
}
