package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Infographic;
import edu.avada.course.model.entity.Infographic.InfographicType;

public record InfographicDto(
        String path,
        String description,
        InfographicType type
) {
    public InfographicDto(
            String path,
            String description,
            InfographicType type
    ) {
        this.path = path;
        this.description = description;
        this.type = type;
    }

    public static InfographicDto fromEntity(Infographic infographic) {
        return new InfographicDto(
                infographic.getPath(),
                infographic.getDescription(),
                infographic.getType()
        );
    }
}
