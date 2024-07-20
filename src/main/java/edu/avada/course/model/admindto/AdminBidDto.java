package edu.avada.course.model.admindto;

import edu.avada.course.model.entity.Bid.BidStatus;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor// Lombok
public class AdminBidDto implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String comment;
    private LocalDate date;
    private BidStatus status;
}
