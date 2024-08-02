package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminUnitDto;
import org.springframework.data.domain.Page;

public interface AdminUnitService {
    //--------------------- UNITS PART ---------------------\\
    Page<AdminUnitDto> pageUnits(int page, int size);

    AdminUnitDto getUnitById(long id);

    long add(AdminUnitDto adminUnitDto);

    void deleteUnitById(long id);

    void updateUnit(AdminUnitDto adminUnitDto);
}
