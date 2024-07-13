package edu.avada.course.service.impl;

import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.service.AdminNewBldsService;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminNewBldsServiceImpl implements AdminNewBldsService {
    private final NewBuildingRepository newBuildingRepository;
    private Map<Long, NewBuilding> newBuildings;

    public AdminNewBldsServiceImpl(
            @Autowired NewBuildingRepository newBuildingRepository
    ) {
        this.newBuildingRepository = newBuildingRepository;
    }

    @Override
    public Set<NewBuilding> getAllNewBlds() {
        if (newBuildings == null) {
            getAllNewBldsFromDb();
        }
        return newBuildings.values().parallelStream().collect(Collectors.toSet());
    }

    @Override
    public NewBuilding getNewBldById(long id) {
        return newBuildings.get(id);
    }

    @Override
    public void deleteNewBldById(long id) {
        NewBuilding newBuilding = newBuildings.get(id);
        newBuildings.remove(id);
        newBuildingRepository.delete(newBuilding);
    }

    @Override
    public void changeNewBldStatusById(long id) {
        NewBuilding newBuilding = newBuildings.get(id);
        NewBuildStatus newStatus =
                newBuilding.getStatus() == NewBuildStatus.ACTIVE ? NewBuildStatus.OFF: NewBuildStatus.ACTIVE;
        newBuilding.setStatus(newStatus);
        newBuildingRepository.save(newBuilding);
    }

    @Override
    public void updateNewBld(NewBuilding newBuilding) {
        newBuildings.put(newBuilding.getId(), newBuilding);
        newBuildingRepository.save(newBuilding);
    }

    private void getAllNewBldsFromDb() {
        newBuildings = newBuildingRepository
                .findAll()
                .parallelStream()
                .collect(Collectors.toMap(NewBuilding::getId, val -> val));
    }
}
