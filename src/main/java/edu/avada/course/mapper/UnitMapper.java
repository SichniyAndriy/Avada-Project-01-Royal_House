package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.dto.UnitDto;
import edu.avada.course.model.entity.Unit;
import java.util.Optional;

public class UnitMapper {
    public static AdminUnitDto fromEntityToAdminDto(Unit unit) {
        AdminUnitDto newAdminUnitDto = new AdminUnitDto();
        newAdminUnitDto.setId(unit.getId());
        newAdminUnitDto.setType(unit.getType());
        newAdminUnitDto.setSquare(unit.getSquare());
        newAdminUnitDto.setTotalPrice(unit.getTotalPrice());
        newAdminUnitDto.setPricePerSqM(unit.getPricePerSqM());
        newAdminUnitDto.setRooms(unit.getRooms());
        newAdminUnitDto.setFloor(unit.getFloor());
        newAdminUnitDto.setTotalFloors(unit.getTotalFloors());
        newAdminUnitDto.setDate(unit.getDate());
        newAdminUnitDto.setFlatNumber(unit.getFlatNumber());
        if (unit.getNewBuilding() != null) {
            newAdminUnitDto.setNewBuilding(unit.getNewBuilding().getId());
        }
        newAdminUnitDto.setAddress(AddressMapper.fromEntityToAdminDto(unit.getAddress()));
        newAdminUnitDto.setImages(unit.getImages().stream().map(ImageMapper::fromEntityToAdminDto).toList());
        return newAdminUnitDto;
    }

    public static UnitDto fromEntityToDto(Unit unit) {
        return new UnitDto(
                unit.getType(),
                unit.getSquare(),
                unit.getTotalPrice(),
                unit.getPricePerSqM(),
                unit.getRooms(),
                unit.getFloor(),
                unit.getTotalFloors(),
                unit.getDate(),
                unit.getFlatNumber(),
                AddressMapper.fromEntityToDto(unit.getAddress()),
                unit.getImages().stream().map(ImageMapper::fromEntityToDto).toList()
        );
    }

    public static Unit fromAdminDtoToEntity(AdminUnitDto adminUnitDto) {
        Unit newUnit = new Unit();
        newUnit.setId(adminUnitDto.getId());
        newUnit.setType(adminUnitDto.getType());
        newUnit.setSquare(adminUnitDto.getSquare());
        newUnit.setTotalPrice(adminUnitDto.getTotalPrice());
        newUnit.setPricePerSqM(adminUnitDto.getPricePerSqM());
        newUnit.setRooms(adminUnitDto.getRooms());
        newUnit.setFloor(adminUnitDto.getFloor());
        newUnit.setTotalFloors(adminUnitDto.getTotalFloors());
        newUnit.setDate(adminUnitDto.getDate());
        newUnit.setFlatNumber(adminUnitDto.getFlatNumber());
        newUnit.setAddress(AddressMapper.fromAdminDtoToEntity(adminUnitDto.getAddress()));
        newUnit.setImages(adminUnitDto.getImages().stream().map(ImageMapper::fromAdminDtoToEntity).toList());

        Optional.ofNullable(newUnit.getImages()).ifPresent(
                items -> items.forEach(image -> image.setUnit(newUnit))
        );
        return newUnit;
    }
}
