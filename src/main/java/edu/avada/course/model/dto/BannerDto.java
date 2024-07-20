package edu.avada.course.model.dto;

public record BannerDto(
        String path
) {
    public BannerDto(String path) {
        this.path = path;
    }
}
