package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminUnitDto;
import java.util.List;

public interface SecondaryService {
    List<AdminUnitDto> getAllSecondaryUnits();

    AdminUnitDto getSecondaryUnitById(long id);
}
