package edu.avada.course.service;

import edu.avada.course.model.entity.Address;

public interface AdminAddressService {
    Address getAnyAddress();

    long add(Address address);
}
