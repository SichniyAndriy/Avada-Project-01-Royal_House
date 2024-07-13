package edu.avada.course.model.dto;

import edu.avada.course.model.entity.Banner;
import edu.avada.course.model.entity.Banner.BannerType;

public record BannerDto(
        String path,
        Banner.BannerType type
) {
    public BannerDto(
            String path,
            BannerType type
    ) {
        this.path = path;
        this.type = type;
    }

    public static BannerDto fromEntity(Banner banner) {
        return new BannerDto(
                banner.getPath(),
                banner.getType()
        );
    }
}
