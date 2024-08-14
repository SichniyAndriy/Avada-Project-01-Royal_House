package edu.avada.course.service.impl;

import edu.avada.course.mapper.UnitMapper;
import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.entity.Unit;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.SecondaryService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondaryServiceImpl implements SecondaryService {
    private final UnitRepository unitRepository;

    public SecondaryServiceImpl(
            @Autowired UnitRepository unitRepository
    ) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<AdminUnitDto> getAllSecondaryUnits() {
        return unitRepository.findUnitsByNewBuildingIsNull().stream()
                .map(UnitMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public AdminUnitDto getSecondaryUnitById(long id) {
        Unit byId = unitRepository.findById(id);
        return UnitMapper.fromEntityToAdminDto(byId);
    }
}
