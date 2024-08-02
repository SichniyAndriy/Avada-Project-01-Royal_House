package edu.avada.course.service.impl;

import edu.avada.course.mapper.AddressMapper;
import edu.avada.course.model.admindto.AdminAddressDto;
import edu.avada.course.model.entity.Address;
import edu.avada.course.repository.AddressRepository;
import edu.avada.course.service.AdminAddressService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAddressServiceImpl implements AdminAddressService {
    private final AddressRepository addressRepository;
    private final Random RANDOM = new Random();

    private List<Address> addresses;

    public AdminAddressServiceImpl(
            @Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAnyAddress() {
        if (addresses == null || addresses.isEmpty()) {
            addresses = addressRepository.findAll().stream().collect(Collectors.toCollection(ArrayList::new));
        }
        int i = RANDOM.nextInt(addresses.size());
        Address address = addresses.get(i);
        addresses.remove(i);
        return address;
    }

    @Override
    public long add(AdminAddressDto adminAddressDto) {
        Address address = AddressMapper.fromAdminDtoToEntity(adminAddressDto);
        return addressRepository.save(address).getId();
    }

    public Address getAddressById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<AdminAddressDto> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream()
                .map(AddressMapper::fromEntityToAdminDto)
                .sorted(Comparator.comparing(AdminAddressDto::getCity)
                        .thenComparing(AdminAddressDto::getStreet)
                        .thenComparing(AdminAddressDto::getHouseNumber))
                .toList();
    }
}
