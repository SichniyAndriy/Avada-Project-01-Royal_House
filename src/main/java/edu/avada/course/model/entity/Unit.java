package edu.avada.course.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter @Setter @NoArgsConstructor @Accessors(chain = true)// Lombok
@Entity @Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "units_gen")
    @SequenceGenerator(name = "units_gen", sequenceName = "units_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private UnitType type = UnitType.FLAT;

    @Column(name = "square", nullable = false, precision = 9, scale = 3)
    private BigDecimal square;

    @Column(name = "total_price", nullable = false, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "price_per_sq_m", nullable = false, scale = 2)
    private BigDecimal pricePerSqM;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "total_floors")
    private Integer totalFloors;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "flat_number")
    private Integer flatNumber;

    @ManyToOne(targetEntity = Address.class)
    @PrimaryKeyJoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(targetEntity = NewBuilding.class)
    @PrimaryKeyJoinColumn(name = "new_building_id", referencedColumnName = "id")
    private NewBuilding newBuilding;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Image.class, mappedBy = "unit")
    @PrimaryKeyJoinColumn(name = "image_id")
    private List<Image> images = new ArrayList<>();

    public enum UnitType{
        FLAT,
        HOUSE,
        COMMERCIAL,
        LAND
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Unit unit = (Unit) object;
        return Objects.equals(id, unit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
