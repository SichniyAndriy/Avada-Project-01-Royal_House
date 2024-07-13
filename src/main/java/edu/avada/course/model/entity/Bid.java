package edu.avada.course.model.entity;

import edu.avada.course.model.dto.BidDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter @Setter @NoArgsConstructor// Lombok
@Entity @Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bids_gen")
    @SequenceGenerator(name = "bids_gen", sequenceName = "bids_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "comment", length = 1024)
    private String comment;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private BidStatus status;

    public static Bid fromDto(BidDto bidDto) {
        Bid newBid = new Bid();
        newBid.setName(bidDto.name());
        newBid.setPhone(bidDto.phone());
        newBid.setEmail(bidDto.email());
        newBid.setComment(bidDto.comment());
        newBid.setDate(bidDto.date());
        newBid.setStatus(bidDto.status());
        return newBid;
    }

    public enum BidStatus{
        NEW,
        ANSWERED
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Bid bid = (Bid) object;
        return Objects.equals(id, bid.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
