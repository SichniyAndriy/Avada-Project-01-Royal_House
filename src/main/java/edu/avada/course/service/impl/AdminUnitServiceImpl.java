package edu.avada.course.service.impl;

import edu.avada.course.mapper.UnitMapper;
import edu.avada.course.model.admindto.AdminUnitDto;
import edu.avada.course.model.entity.Unit;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.AdminUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminUnitServiceImpl implements AdminUnitService {
    private final UnitRepository unitRepository;

    public AdminUnitServiceImpl(
            @Autowired UnitRepository unitRepository
    ) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Page<AdminUnitDto> pageUnits(int page, int size) {
        Page<Unit> unitPage = unitRepository.findAll(PageRequest.of(page, size));
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
        return unitRepository.save(unit).getId();
    }

    @Override
    public void deleteUnitById(long id) {
        unitRepository.deleteById(id);
    }

    @Override
    public void updateUnit(AdminUnitDto adminUnitDto) {
        Unit unit = UnitMapper.fromAdminDtoToEntity(adminUnitDto);
        unitRepository.save(unit);
    }
}
