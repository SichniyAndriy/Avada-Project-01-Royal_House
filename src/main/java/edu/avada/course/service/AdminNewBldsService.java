package edu.avada.course.service;

import edu.avada.course.model.entity.NewBuilding;
import java.util.Set;

public interface AdminNewBldsService {
    Set<NewBuilding> getAllNewBlds();

    NewBuilding getNewBldById(long id);

    void deleteNewBldById(long id);

    void changeNewBldStatusById(long id);

    void updateNewBld(NewBuilding newBuilding);

    long add(NewBuilding newBuilding);
}
