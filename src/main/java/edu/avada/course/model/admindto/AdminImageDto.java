package edu.avada.course.model.admindto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor // Lombok
public class AdminImageDto implements Serializable {
    private Long id;
    private String path;
}
