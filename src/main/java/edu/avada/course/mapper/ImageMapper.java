package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminImageDto;
import edu.avada.course.model.dto.ImageDto;
import edu.avada.course.model.entity.Image;

public class ImageMapper {
    public static AdminImageDto fromEntityToAdminDto(Image image) {
        AdminImageDto newAdminImageDto = new AdminImageDto();
        newAdminImageDto.setId(image.getId());
        newAdminImageDto.setPath(image.getPath());
        return newAdminImageDto;
    }

    public static ImageDto fromEntityToDto(Image image) {
        return new ImageDto(image.getPath());
    }

    public static Image fromAdminDtoToEntity(AdminImageDto adminImageDto) {
        Image newImage = new Image();
        newImage.setId(adminImageDto.getId());
        newImage.setPath(adminImageDto.getPath());
        return newImage;
    }
}
