package edu.avada.course.model.admindto;

import edu.avada.course.model.entity.CompanyService.ServiceStatus;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class AdminCompanyServiceDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private ServiceStatus status;
    private String imagePath;
}
