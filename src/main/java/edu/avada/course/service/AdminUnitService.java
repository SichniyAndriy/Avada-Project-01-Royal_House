package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminUnitDto;
import java.util.Set;

public interface AdminUnitService {
    //--------------------- UNITS PART ---------------------\\
    Set<AdminUnitDto> getAllUnits();

    AdminUnitDto getUnitById(long id);

    void deleteUnitById(long id);

    long add(AdminUnitDto unit);

    void updateUnit(AdminUnitDto adminUnitDto);
}
