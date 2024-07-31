package edu.avada.course.service.impl;

import edu.avada.course.mapper.AddressMapper;
import edu.avada.course.mapper.UnitMapper;
import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.entity.Unit;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.AdminAddressService;
import edu.avada.course.service.AdminUnitService;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUnitServiceImpl implements AdminUnitService {
    private final UnitRepository unitRepository;
    private final AdminAddressService adminAddressService;
    private Map<Long, Unit> units;

    public AdminUnitServiceImpl(
            @Autowired UnitRepository unitRepository,
            @Autowired AdminAddressService adminAddressService
    ) {
        this.unitRepository = unitRepository;
        this.adminAddressService = adminAddressService;
    }

    @Override
    public Set<AdminUnitDto> getAllUnits() {
        if (units == null) {
            getAllUnitsFromDB();
        }
        return units.values().parallelStream()
                .map(UnitMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(AdminUnitDto::getId))
                ));
    }

    @Override
    public AdminUnitDto getUnitById(long id) {
        if (units == null) {
            getAllUnitsFromDB();
        }
        return UnitMapper.fromEntityToAdminDto(units.get(id));
    }

    @Override
    public long add(AdminUnitDto adminUnitDto) {
        adminUnitDto.setAddress(AddressMapper.fromEntityToAdminDto(adminAddressService.getAnyAddress()));
        Unit unit = UnitMapper.fromAdminDtoToEntity(adminUnitDto);
        long id = unitRepository.save(unit).getId();
        if (id > 0) {
            units.put(id, unit);
        }
        return id;
    }

    @Override
    public void deleteUnitById(long id) {
        unitRepository.deleteById(id);
        units.remove(id);
    }

    @Override
    public void updateUnit(AdminUnitDto adminUnitDto) {
        Unit unit = UnitMapper.fromAdminDtoToEntity(adminUnitDto);
        Optional.ofNullable(unit.getImages()).ifPresent(images -> images.forEach(img -> img.setUnit(unit)));
        units.put(unit.getId(), unit);
        unitRepository.save(unit);
    }

    private void getAllUnitsFromDB() {
        units = unitRepository.findAll()
                .parallelStream()
                .collect(Collectors.toConcurrentMap(Unit::getId, val -> val));
    }
}
