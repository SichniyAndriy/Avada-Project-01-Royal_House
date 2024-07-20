package edu.avada.course.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "banners")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banner_gen")
    @SequenceGenerator(name = "banner_gen", sequenceName = "banner_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name="path", length = 1024)
    private String path;

    @ManyToOne(targetEntity = NewBuilding.class)
    @PrimaryKeyJoinColumn(name = "new_building_id", referencedColumnName = "id")
    private NewBuilding newBuilding;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Banner banner = (Banner) object;
        return Objects.equals(id, banner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
