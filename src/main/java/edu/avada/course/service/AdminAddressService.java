package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminAddressDto;
import edu.avada.course.model.entity.Address;
import java.util.List;

public interface AdminAddressService {
    Address getAnyAddress();

    long add(AdminAddressDto adminAddressDto);

    List<AdminAddressDto> getAllAddresses();

    public Address getAddressById(long id);
}
