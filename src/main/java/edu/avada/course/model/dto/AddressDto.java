package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Address;

public record AddressDto(
    String city,
    String street,
    String houseNumber
) {
    public AddressDto(
            String city,
            String street,
            String houseNumber
    ) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public static AddressDto fromEntity(Address address) {
        return new AddressDto(
                address.getCity(),
                address.getStreet(),
                address.getHouseNumber()
        );
    }

}
