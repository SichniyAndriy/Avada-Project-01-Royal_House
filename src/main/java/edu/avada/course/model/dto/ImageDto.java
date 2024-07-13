package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Image;

public record ImageDto(
        String path
) {
    public ImageDto(String path) {
        this.path = path;
    }

    public static ImageDto fromEntity(Image image) {
        return new ImageDto(image.getPath());
    }
}
