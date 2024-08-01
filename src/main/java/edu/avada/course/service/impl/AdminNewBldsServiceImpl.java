package edu.avada.course.service.impl;

import edu.avada.course.mapper.NewBuildingMapper;
import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.service.AdminNewBldsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminNewBldsServiceImpl implements AdminNewBldsService {
    private final NewBuildingRepository newBuildingRepository;

    public AdminNewBldsServiceImpl(
            @Autowired NewBuildingRepository newBuildingRepository
    ) {
        this.newBuildingRepository = newBuildingRepository;
    }

     @Override
     public List<AdminNewBuildingDto> getAllNewBuildings() {
         return newBuildingRepository.findAll().stream()
                 .map(NewBuildingMapper::fromEntityToAdminDto)
                 .toList();
     }

    @Override
    public Page<AdminNewBuildingDto> getPageNewBlds(int page, int size) {
        Page<NewBuilding> newBuildingPage = newBuildingRepository.findAll(PageRequest.of(page, size));
        Page<AdminNewBuildingDto> adminNewBuildingDtoPage = newBuildingPage.map(NewBuildingMapper::fromEntityToAdminDto);
        return adminNewBuildingDtoPage;
    }

    @Override
    public AdminNewBuildingDto getNewBldById(long id) {
        Optional<NewBuilding> newBuildingOptional = newBuildingRepository.findById(id);
        if (newBuildingOptional.isPresent()) {
            return NewBuildingMapper.fromEntityToAdminDto(newBuildingOptional.get());
        }
        return null;
    }

    @Override
    public void deleteNewBldById(long id) {
        newBuildingRepository.deleteById(id);
    }

    @Override
    public void changeNewBldStatusById(long id) {
        Optional<NewBuilding> newBuildingOptional = newBuildingRepository.findById(id);
        newBuildingOptional.ifPresent(item -> {
            NewBuildStatus status = item.getStatus();
            item.setStatus(status == NewBuildStatus.ACTIVE ? NewBuildStatus.OFF: NewBuildStatus.ACTIVE);
            newBuildingRepository.save(item);
        });
    }

    @Override
    public void updateNewBld(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = convertToEntity(adminNewBuildingDto);
        newBuildingRepository.save(newBuilding);
    }

    @Override
    public long add(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = convertToEntity(adminNewBuildingDto);
        return newBuildingRepository.save(newBuilding).getId();
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
