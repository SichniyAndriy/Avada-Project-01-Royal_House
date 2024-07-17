package edu.avada.course.service;

import edu.avada.course.model.entity.Unit;
import java.util.Set;

public interface AdminUnitService {
    //--------------------- UNITS PART ---------------------\\
    Set<Unit> getAllUnits();

    Unit getUnitById(long id);

    long add(Unit unit);
}
