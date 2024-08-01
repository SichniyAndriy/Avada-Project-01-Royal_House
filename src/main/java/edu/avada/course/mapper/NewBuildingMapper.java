package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.model.dto.NewBuildingDto;
import edu.avada.course.model.entity.NewBuilding;
import java.util.Optional;

public class NewBuildingMapper {
    public static AdminNewBuildingDto fromEntityToAdminDto(NewBuilding newBuilding) {
        AdminNewBuildingDto newAdminNewBuildingDto = new AdminNewBuildingDto();
        newAdminNewBuildingDto.setId(newBuilding.getId());
        newAdminNewBuildingDto.setTitle(newBuilding.getTitle());
        newAdminNewBuildingDto.setLocation(newBuilding.getLocation());
        newAdminNewBuildingDto.setStatus(newBuilding.getStatus());
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
        newBuilding.setLocation(newAdminNewBuildingDto.getLocation());
        newBuilding.setStatus(newAdminNewBuildingDto.getStatus());
        newBuilding.setDescription(newAdminNewBuildingDto.getDescription());
        newBuilding.setAddress(AddressMapper.fromAdminDtoToEntity(newAdminNewBuildingDto.getAddress()));
        newBuilding.setPanoramaPath(newAdminNewBuildingDto.getPanoramaPath());
        newBuilding.setBanners(newAdminNewBuildingDto.getBanners().stream()
                .map(BannerMapper::fromAdminDtoToEntity).toList());
        newBuilding.setInfographics(newAdminNewBuildingDto.getInfographics().stream()
                .map(InfographicMapper::fromAdminDtoToEntity).toList());
        newBuilding.setUnits(newAdminNewBuildingDto.getUnits().stream()
                .map(UnitMapper::fromAdminDtoToEntity).toList());

        Optional.ofNullable(newBuilding.getBanners())
                .ifPresent(items -> items.forEach(banner -> banner.setNewBuilding(newBuilding)));
        Optional.ofNullable(newBuilding.getInfographics())
                .ifPresent(items -> items.forEach(infographic -> infographic.setNewBuilding(newBuilding)));
        Optional.ofNullable(newBuilding.getUnits())
                .ifPresent(items -> items.forEach(unit -> unit.setNewBuilding(newBuilding)));

        return newBuilding;
    }
}
