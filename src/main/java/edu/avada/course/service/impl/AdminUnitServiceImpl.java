package edu.avada.course.service.impl;

import edu.avada.course.mapper.UnitMapper;
import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.entity.Address;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.Unit;
import edu.avada.course.repository.AddressRepository;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.AdminUnitService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminUnitServiceImpl implements AdminUnitService {
    private final UnitRepository unitRepository;
    private final NewBuildingRepository newBuildingRepository;
    private final AddressRepository addressRepository;

    public AdminUnitServiceImpl(
            @Autowired UnitRepository unitRepository,
            @Autowired NewBuildingRepository newBuildingRepository,
            @Autowired AddressRepository addressRepository
    ) {
        this.unitRepository = unitRepository;
        this.newBuildingRepository = newBuildingRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Page<AdminUnitDto> pageUnits(int page, int size) {
        Page<Unit> unitPage = unitRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
        return unitPage.map(UnitMapper::fromEntityToAdminDto);
    }

    @Override
    public AdminUnitDto getUnitById(long id) {
        Unit byId = unitRepository.findById(id);
        return UnitMapper.fromEntityToAdminDto(byId);
    }

    @Override
    public long add(AdminUnitDto adminUnitDto) {
        Unit unit = UnitMapper.fromAdminDtoToEntity(adminUnitDto);
        Address address = addressRepository.findByCityAndStreetAndHouseNumber(
                adminUnitDto.getAddress().getCity(),
                adminUnitDto.getAddress().getStreet(),
                adminUnitDto.getAddress().getHouseNumber()
        );
        if (address == null) {
            address = addressRepository.save(unit.getAddress());
        }
        unit.setAddress(address);
        Optional.ofNullable(adminUnitDto.getNewBuilding()).ifPresent(item -> {
            Optional<NewBuilding> newBuilding = newBuildingRepository.findById(item);
            newBuilding.ifPresent(bldFromDb -> {
                unit.setNewBuilding(bldFromDb);
                bldFromDb.getUnits().add(unit);
                newBuildingRepository.save(bldFromDb);
            });
        });
        return unitRepository.save(unit).getId();
    }

    @Override
    public void deleteUnitById(long id) {
        unitRepository.deleteById(id);
    }

    @Override
    public void updateUnit(AdminUnitDto adminUnitDto) {
        Unit unit = UnitMapper.fromAdminDtoToEntity(adminUnitDto);
        Optional.ofNullable(adminUnitDto.getNewBuilding()).ifPresent(item -> {
            Optional<NewBuilding> newBuilding = newBuildingRepository.findById(item);
            newBuilding.ifPresent(bldFromDb -> {
                unit.setNewBuilding(bldFromDb);
                bldFromDb.getUnits().add(unit);
                newBuildingRepository.save(bldFromDb);
            });
        });
        unitRepository.save(unit);
    }
}
