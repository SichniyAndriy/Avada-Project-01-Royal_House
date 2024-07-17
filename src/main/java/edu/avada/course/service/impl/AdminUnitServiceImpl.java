package edu.avada.course.service.impl;

import edu.avada.course.model.entity.Unit;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.AdminUnitService;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUnitServiceImpl implements AdminUnitService {
    private final UnitRepository unitRepository;
    private Map<Long, Unit> units;

    public AdminUnitServiceImpl(
            @Autowired UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Set<Unit> getAllUnits() {
        if (units == null) {
            getAllUnitsFromDB();
        }
        return units.values()
                .parallelStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Unit getUnitById(long id) {
        if (units == null) {
            getAllUnitsFromDB();
        }
        return units.get(id);
    }

    @Override
    public long add(Unit unit) {
        long id = unitRepository.save(unit).getId();
        if (id > 0) {
            units.put(id, unit);
        }
        return id;
    }

    private void getAllUnitsFromDB() {
        units = unitRepository.findAll()
                .parallelStream()
                .collect(Collectors.toConcurrentMap(Unit::getId, val -> val));
    }
}
