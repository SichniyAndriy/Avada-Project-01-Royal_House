package edu.avada.course.service.impl;

import edu.avada.course.mapper.NewBuildingMapper;
import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.service.AdminNewBldsService;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
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
    public Set<AdminNewBuildingDto> getAllNewBlds() {
        getAllNewBldsFromDb();
        return newBuildings.values().parallelStream()
                .map(NewBuildingMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(AdminNewBuildingDto::getId))
                ));
    }

    @Override
    public AdminNewBuildingDto getNewBldById(long id) {
        return NewBuildingMapper.fromEntityToAdminDto(newBuildings.get(id));
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
    public void updateNewBld(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = convertToEntity(adminNewBuildingDto);
        long id = newBuildingRepository.save(newBuilding).getId();
        newBuildings.put(id, newBuilding);
    }

    @Override
    public long add(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = convertToEntity(adminNewBuildingDto);
        long id = newBuildingRepository.save(newBuilding).getId();
        if (id > 0) {
            newBuildings.put(id, newBuilding);
        }
        return id;
    }

    private void getAllNewBldsFromDb() {
        newBuildings = newBuildingRepository
                .findAll()
                .parallelStream()
                .collect(Collectors.toMap(NewBuilding::getId, val -> val));
    }

    private NewBuilding convertToEntity(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = NewBuildingMapper.fromAdminDtoToEntity(adminNewBuildingDto);
        Optional.of(newBuilding.getBanners()).ifPresent(
                banners -> banners.forEach(banner -> banner.setNewBuilding(newBuilding))
        );
        Optional.of(newBuilding.getInfographics()).ifPresent(
                infographics -> infographics.forEach(infographic -> infographic.setNewBuilding(newBuilding))
        );
        Optional.of(newBuilding.getUnits()).ifPresent(
                units -> units.forEach(unit -> unit.setNewBuilding(newBuilding))
        );
        return newBuilding;
    }
}
