package edu.avada.course.model.entity;

import edu.avada.course.model.dto.InfographicDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Entity @Table(name = "infographics")
public class Infographic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_graph_gen")
    @SequenceGenerator(name = "info_graph_gen", sequenceName = "info_graph_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name="path", length = 1024, nullable = false, unique = true)
    private String path;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private InfographicType type;

    @ManyToOne(targetEntity = NewBuilding.class)
    @PrimaryKeyJoinColumn(name = "new_building_id", referencedColumnName = "id")
    private NewBuilding newBuilding;

    public static Infographic fromDto(InfographicDto infographicDto) {
        Infographic newInfographic = new Infographic();
        newInfographic.setPath(infographicDto.path());
        newInfographic.setDescription(infographicDto.description());
        newInfographic.setType(infographicDto.type());
        return newInfographic;
    }

    public enum InfographicType {
        ABOUT,
        INFRASTRUCTURE,
        FLATS
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Infographic that = (Infographic) object;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
