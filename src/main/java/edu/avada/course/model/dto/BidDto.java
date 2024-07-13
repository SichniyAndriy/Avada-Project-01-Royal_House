package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Bid;
import edu.avada.course.model.entity.Bid.BidStatus;
import java.time.LocalDateTime;

public record BidDto(
        String name,
        String phone,
        String email,
        String comment,
        LocalDateTime date,
        BidStatus status
) {
    public BidDto(
            String name,
            String phone,
            String email,
            String comment,
            LocalDateTime date,
            BidStatus status
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
        this.date = date;
        this.status = status;
    }

    public static BidDto fromEntity(Bid bid) {
        return new BidDto(
                bid.getName(),
                bid.getPhone(),
                bid.getEmail(),
                bid.getComment(),
                bid.getDate(),
                bid.getStatus()
        );
    }
}
