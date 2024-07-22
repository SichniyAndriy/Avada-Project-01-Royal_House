package edu.avada.course.service;

import edu.avada.course.model.admindto.AdminAddressDto;
import edu.avada.course.model.entity.Address;

public interface AdminAddressService {
    Address getAnyAddress();

    long add(AdminAddressDto adminAddressDto);
}
