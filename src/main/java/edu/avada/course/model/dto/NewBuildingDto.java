package edu.avada.course.model.dto;

import edu.avada.course.model.entity.NewBuilding.Description;
import edu.avada.course.model.entity.NewBuilding.Location;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import java.util.List;

public record NewBuildingDto(
        String title,
        Description description,
        Location location,
        NewBuildStatus status,
        AddressDto address,
        String panoramaPath,
        List<BannerDto> banners,
        List<InfographicDto> infographics,
        List<UnitDto> units
) {
    public NewBuildingDto(
            String title,
            Description description,
            Location location,
            NewBuildStatus status,
            AddressDto address,
            String panoramaPath,
            List<BannerDto> banners,
            List<InfographicDto> infographics,
            List<UnitDto> units
    ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
        this.address = address;
        this.panoramaPath = panoramaPath;
        this.banners = banners;
        this.infographics = infographics;
        this.units = units;
    }
}
