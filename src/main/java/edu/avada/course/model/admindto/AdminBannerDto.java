package edu.avada.course.model.admindto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class AdminBannerDto implements Serializable {
    private Long id;
    private String path;
}
