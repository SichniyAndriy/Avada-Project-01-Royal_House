package edu.avada.course.model.admindto;

import edu.avada.course.model.entity.Infographic.InfographicType;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link edu.avada.course.model.entity.Infographic}
 */
@Data @NoArgsConstructor
public class AdminInfographicDto implements Serializable {
    private Long id;
    private String path;
    private String description;
    private InfographicType type;
}
