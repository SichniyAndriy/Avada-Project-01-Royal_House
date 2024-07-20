package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Unit.UnitType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record UnitDto(
        UnitType type,
        BigDecimal square,
        BigDecimal totalPrice,
        BigDecimal pricePerSqM,
        Integer rooms,
        Integer floor,
        Integer totalFloors,
        LocalDate date,
        Integer flatNumber,
        AddressDto address,
        List<ImageDto> imagesDto
) {
    public UnitDto(
            UnitType type,
            BigDecimal square,
            BigDecimal totalPrice,
            BigDecimal pricePerSqM,
            Integer rooms,
            Integer floor,
            Integer totalFloors,
            LocalDate date,
            Integer flatNumber,
            AddressDto address,
            List<ImageDto> imagesDto
    ) {
        this.type = type;
        this.square = square;
        this.totalPrice = totalPrice;
        this.pricePerSqM = pricePerSqM;
        this.rooms = rooms;
        this.floor = floor;
        this.totalFloors = totalFloors;
        this.date = date;
        this.flatNumber = flatNumber;
        this.address = address;
        this.imagesDto = imagesDto;
    }
}
