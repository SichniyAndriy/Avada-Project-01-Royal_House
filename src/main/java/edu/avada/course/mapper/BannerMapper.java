package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminBannerDto;
import edu.avada.course.model.dto.BannerDto;
import edu.avada.course.model.entity.Banner;

public class BannerMapper {
    public static AdminBannerDto fromEntityToAdminDto(Banner banner) {
        AdminBannerDto newAdminBannerDto = new AdminBannerDto();
        newAdminBannerDto.setId(banner.getId());
        newAdminBannerDto.setPath(banner.getPath());
        return newAdminBannerDto;
    }

    public static BannerDto fromEntityToDto(Banner banner) {
        return new BannerDto(banner.getPath());
    }

    public static Banner fromAdminDtoToEntity(AdminBannerDto adminBannerDto) {
        Banner newBanner = new Banner();
        newBanner.setId(adminBannerDto.getId());
        newBanner.setPath(adminBannerDto.getPath());
        return newBanner;
    }
}
