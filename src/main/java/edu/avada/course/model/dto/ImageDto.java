package edu.avada.course.model.dto;

public record ImageDto(
        String path
) {
    public ImageDto(String path) {
        this.path = path;
    }
}
