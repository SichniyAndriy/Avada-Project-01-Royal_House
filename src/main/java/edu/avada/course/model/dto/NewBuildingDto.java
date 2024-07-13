package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Address;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.Description;
import edu.avada.course.model.entity.NewBuilding.Location;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import java.util.List;

public record NewBuildingDto(
        String title,
        Description description,
        Location location,
        NewBuildStatus status,
        Address address,
        String panoramaPath,
        List<BannerDto> bannersDto,
        List<InfographicDto> infographicsDto,
        List<UnitDto> unitsDto
) {
    public NewBuildingDto(
            String title,
            Description description,
            Location location,
            NewBuildStatus status,
            Address address,
            String panoramaPath,
            List<BannerDto> bannersDto,
            List<InfographicDto> infographicsDto,
            List<UnitDto> unitsDto
    ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.address = address;
        this.panoramaPath = panoramaPath;
        this.status = status;
        this.bannersDto = bannersDto;
        this.infographicsDto = infographicsDto;
        this.unitsDto = unitsDto;
    }

    public static NewBuildingDto fromEntity(NewBuilding newBuilding) {
        return new NewBuildingDto(
                newBuilding.getTitle(),
                newBuilding.getDescription(),
                newBuilding.getLocation(),
                newBuilding.getStatus(),
                newBuilding.getAddress(),
                newBuilding.getPanoramaPath(),
                newBuilding.getBanners().stream().map(BannerDto::fromEntity).toList(),
                newBuilding.getInfographics().stream().map(InfographicDto::fromEntity).toList(),
                newBuilding.getUnits().stream().map(UnitDto::fromEntity).toList()
        );
    }
}
