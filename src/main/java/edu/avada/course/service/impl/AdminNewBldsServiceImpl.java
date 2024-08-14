package edu.avada.course.service.impl;

import edu.avada.course.mapper.NewBuildingMapper;
import edu.avada.course.model.admindto.AdminNewBuildingDto;
import edu.avada.course.model.entity.Address;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.repository.AddressRepository;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.service.AdminNewBldsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminNewBldsServiceImpl implements AdminNewBldsService {
    private final NewBuildingRepository newBuildingRepository;
    private final AddressRepository addressRepository;

    public AdminNewBldsServiceImpl(
            @Autowired NewBuildingRepository newBuildingRepository,
            AddressRepository addressRepository) {
        this.newBuildingRepository = newBuildingRepository;
        this.addressRepository = addressRepository;
    }
    @Override
    public List<AdminNewBuildingDto> getAllNewBuildings() {
        return newBuildingRepository.findAll().stream()
                .map(NewBuildingMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Page<AdminNewBuildingDto> getPageNewBlds(int page, int size) {
        Page<NewBuilding> newBuildingPage =
                newBuildingRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
        return newBuildingPage.map(NewBuildingMapper::fromEntityToAdminDto);
    }

    @Override
    public AdminNewBuildingDto getNewBldById(long id) {
        Optional<NewBuilding> newBuildingOptional = newBuildingRepository.findById(id);
        return newBuildingOptional
                .map(NewBuildingMapper::fromEntityToAdminDto)
                .orElse(null);
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
            item.setStatus(status == NewBuildStatus.ACTIVE ?
                    NewBuildStatus.OFF: NewBuildStatus.ACTIVE);
            newBuildingRepository.save(item);
        });
    }

    @Override
    public void updateNewBld(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = NewBuildingMapper.fromAdminDtoToEntity(adminNewBuildingDto);
        newBuildingRepository.save(newBuilding);
    }

    @Override
    public long add(AdminNewBuildingDto adminNewBuildingDto) {
        NewBuilding newBuilding = NewBuildingMapper.fromAdminDtoToEntity(adminNewBuildingDto);
        Address address = addressRepository.findByCityAndStreetAndHouseNumber(
                adminNewBuildingDto.getAddress().getCity(),
                adminNewBuildingDto.getAddress().getStreet(),
                adminNewBuildingDto.getAddress().getHouseNumber()
        );
        if (address == null) {
            address = addressRepository.save(newBuilding.getAddress());
        }
        newBuilding.setAddress(address);
        return newBuildingRepository.save(newBuilding).getId();
    }
}
