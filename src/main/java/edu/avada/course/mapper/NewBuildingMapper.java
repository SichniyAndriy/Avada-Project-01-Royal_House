package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.model.dto.NewBuildingDto;
import edu.avada.course.model.entity.NewBuilding;

public class NewBuildingMapper {
    public static AdminNewBuildingDto fromEntityToAdminDto(NewBuilding newBuilding) {
        AdminNewBuildingDto newAdminNewBuildingDto = new AdminNewBuildingDto();
        newAdminNewBuildingDto.setId(newBuilding.getId());
        newAdminNewBuildingDto.setTitle(newBuilding.getTitle());
        newAdminNewBuildingDto.setLocation(newBuilding.getLocation());
        newAdminNewBuildingDto.setDescription(newBuilding.getDescription());
        newAdminNewBuildingDto.setAddress(AddressMapper.fromEntityToAdminDto(newBuilding.getAddress()));
        newAdminNewBuildingDto.setPanoramaPath(newBuilding.getPanoramaPath());
        newAdminNewBuildingDto.setBanners(newBuilding.getBanners().stream()
                .map(BannerMapper::fromEntityToAdminDto).toList());
        newAdminNewBuildingDto.setInfographics(newBuilding.getInfographics().stream()
                .map(InfographicMapper::fromEntityToAdminDto).toList());
        newAdminNewBuildingDto.setUnits(newBuilding.getUnits().stream()
                .map(UnitMapper::fromEntityToAdminDto).toList());
        return newAdminNewBuildingDto;
    }

    public static NewBuildingDto fromEntityToDto(NewBuilding newBuilding) {
        return new NewBuildingDto(
                newBuilding.getTitle(),
                newBuilding.getDescription(),
                newBuilding.getLocation(),
                newBuilding.getStatus(),
                AddressMapper.fromEntityToDto(newBuilding.getAddress()),
                newBuilding.getPanoramaPath(),
                newBuilding.getBanners().stream().map(BannerMapper::fromEntityToDto).toList(),
                newBuilding.getInfographics().stream().map(InfographicMapper::fromEntityToDto).toList(),
                newBuilding.getUnits().stream().map(UnitMapper::fromEntityToDto).toList()
        );
    }

    public static NewBuilding fromAdminDtoToEntity(AdminNewBuildingDto newAdminNewBuildingDto) {
        NewBuilding newBuilding = new NewBuilding();
        newBuilding.setId(newAdminNewBuildingDto.getId());
        newBuilding.setTitle(newAdminNewBuildingDto.getTitle());
        newBuilding.setDescription(newAdminNewBuildingDto.getDescription());
        newBuilding.setLocation(newAdminNewBuildingDto.getLocation());
        newBuilding.setStatus(newAdminNewBuildingDto.getStatus());
        AddressMapper.fromAdminDtoToEntity(newAdminNewBuildingDto.getAddress());
        newBuilding.setPanoramaPath(newAdminNewBuildingDto.getPanoramaPath());
        newBuilding.setBanners(newAdminNewBuildingDto.getBanners().stream().map(BannerMapper::fromAdminDtoToEntity).toList());
        newBuilding.setInfographics(newAdminNewBuildingDto.getInfographics().stream()
                .map(InfographicMapper::fromAdminDtoToEntity).toList());
        newBuilding.setUnits(newAdminNewBuildingDto.getUnits().stream().map(UnitMapper::fromAdminDtoToEntity).toList());
        return newBuilding;
    }

    public static NewBuilding fromDtoToEntity(NewBuildingDto newBuildingDto) {
        NewBuilding newBuilding = new NewBuilding();
        newBuilding.setTitle(newBuildingDto.title());
        newBuilding.setDescription(newBuildingDto.description());
        newBuilding.setLocation(newBuildingDto.location());
        newBuilding.setStatus(newBuildingDto.status());
        newBuilding.setAddress(AddressMapper.fromDtoToEntity(newBuildingDto.address()));
        newBuilding.setPanoramaPath(newBuildingDto.panoramaPath());
        newBuilding.setBanners(newBuildingDto.banners().stream().map(BannerMapper::fromDtoToEntity).toList());
        newBuilding.setInfographics(newBuildingDto.infographics().stream().map(InfographicMapper::fromDtoToEntity).toList());
        newBuilding.setUnits(newBuildingDto.units().stream().map(UnitMapper::fromDtoToEntity).toList());
        return newBuilding;
    }
}
