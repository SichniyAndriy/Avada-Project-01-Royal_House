package edu.avada.course.model.dto;

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
}
