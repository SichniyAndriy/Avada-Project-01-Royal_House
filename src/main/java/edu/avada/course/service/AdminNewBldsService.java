package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminNewBuildingDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface AdminNewBldsService {
    List<AdminNewBuildingDto> getAllNewBuildings();

    Page<AdminNewBuildingDto> getPageNewBlds(int page, int size);

    AdminNewBuildingDto getNewBldById(long id);

    void deleteNewBldById(long id);

    void changeNewBldStatusById(long id);

    void updateNewBld(AdminNewBuildingDto newBuilding);

    long add(AdminNewBuildingDto newBuilding);
}
