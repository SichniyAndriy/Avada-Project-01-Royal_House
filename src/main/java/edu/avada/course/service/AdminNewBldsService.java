package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import java.util.Set;

public interface AdminNewBldsService {
    Set<AdminNewBuildingDto> getAllNewBlds();

    AdminNewBuildingDto getNewBldById(long id);

    void deleteNewBldById(long id);

    void changeNewBldStatusById(long id);

    void updateNewBld(AdminNewBuildingDto newBuilding);

    long add(AdminNewBuildingDto newBuilding);
}
