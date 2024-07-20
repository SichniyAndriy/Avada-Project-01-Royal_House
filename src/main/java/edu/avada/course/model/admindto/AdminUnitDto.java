package edu.avada.course.model.admindto;

import edu.avada.course.model.entity.Unit.UnitType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DTO for {@link edu.avada.course.model.entity.Unit}
 */
@Data
@Accessors(chain = true)
public class AdminUnitDto implements Serializable {
    private Long id;
    private UnitType type = UnitType.FLAT;
    private BigDecimal square;
    private BigDecimal totalPrice;
    private BigDecimal pricePerSqM;
    private Integer rooms;
    private Integer floor;
    private Integer totalFloors;
    private LocalDate date;
    private Integer flatNumber;
    private AdminAddressDto address;
    private List<AdminImageDto> images = new ArrayList<>();
}
