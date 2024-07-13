package edu.avada.course.model.entity;

import edu.avada.course.model.dto.CompanyServiceDto;
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
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "company_services")
public class CompanyService {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_gen")
    @SequenceGenerator(name = "service_gen", sequenceName = "service_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ServiceStatus status;

    @Column(name = "service_image_path")
    private String imagePath;

    public enum ServiceStatus {
        NO,
        YES
    }

    public static CompanyService fromDto(CompanyServiceDto serviceDto) {
        CompanyService newService = new CompanyService();
        newService.setTitle(serviceDto.title());
        newService.setDescription(serviceDto.description());
        newService.setStatus(serviceDto.status());
        newService.setDate(serviceDto.date());
        newService.setImagePath(serviceDto.imagePath());
        return newService;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CompanyService that = (CompanyService) object;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
