package edu.avada.course.model.admindto;

import edu.avada.course.model.entity.NewBuilding.Description;
import edu.avada.course.model.entity.NewBuilding.Location;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DTO for {@link edu.avada.course.model.entity.NewBuilding}
 */
@Data
@Accessors(chain = true)
public class AdminNewBuildingDto implements Serializable {
    private Long id;
    private String title;
    private Location location;
    private NewBuildStatus status;
    private Description description;
    private AdminAddressDto address;
    private String panoramaPath;
    private List<AdminBannerDto> banners;
    private List<AdminInfographicDto> infographics;
    private List<AdminUnitDto> units;
}
