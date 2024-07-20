package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminAddressDto;
import edu.avada.course.model.dto.AddressDto;
import edu.avada.course.model.entity.Address;

public class AddressMapper {
    public static AdminAddressDto fromEntityToAdminDto(Address address) {
        AdminAddressDto newAddressAdminDto = new AdminAddressDto();
        newAddressAdminDto.setId(address.getId());
        newAddressAdminDto.setCity(address.getCity());
        newAddressAdminDto.setStreet(address.getStreet());
        newAddressAdminDto.setHouseNumber(address.getHouseNumber());
        return newAddressAdminDto;
    }

    public static AddressDto fromEntityToDto(Address address) {
        return new AddressDto(address.getCity(), address.getStreet(), address.getHouseNumber());
    }

    public static Address fromAdminDtoToEntity(AdminAddressDto adminAddressDto) {
        Address newAddress = new Address();
        newAddress.setId(adminAddressDto.getId());
        newAddress.setCity(adminAddressDto.getCity());
        newAddress.setStreet(adminAddressDto.getStreet());
        newAddress.setHouseNumber(adminAddressDto.getHouseNumber());
        return newAddress;
    }

    public static Address fromDtoToEntity(AddressDto addressDto) {
        Address newAddress = new Address();
        newAddress.setCity(addressDto.city());
        newAddress.setStreet(addressDto.street());
        newAddress.setHouseNumber(addressDto.houseNumber());
        return newAddress;
    }
}
