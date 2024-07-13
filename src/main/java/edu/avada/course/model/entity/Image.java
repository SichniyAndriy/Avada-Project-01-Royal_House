package edu.avada.course.model.entity;

import edu.avada.course.model.dto.ImageDto;
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

@Getter @Setter @NoArgsConstructor // Lombok
@Entity @Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_gen")
    @SequenceGenerator(name = "images_gen", sequenceName = "images_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "path", length = 1024, nullable = false, unique = true)
    private String path;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "unit_id")
    private Unit unit;

    public static Image fromDto(ImageDto imageDto) {
        Image newImage = new Image();
        newImage.setPath(imageDto.path());
        return newImage;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Image image = (Image) object;
        return Objects.equals(id, image.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
