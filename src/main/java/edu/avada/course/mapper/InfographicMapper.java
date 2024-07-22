package edu.avada.course.mapper;

import edu.avada.course.model.admindto.AdminInfographicDto;
import edu.avada.course.model.dto.InfographicDto;
import edu.avada.course.model.entity.Infographic;

public class InfographicMapper {
    public static AdminInfographicDto fromEntityToAdminDto(Infographic infographic) {
        AdminInfographicDto newAdminInfographicDto = new AdminInfographicDto();
        newAdminInfographicDto.setId(infographic.getId());
        newAdminInfographicDto.setPath(infographic.getPath());
        newAdminInfographicDto.setDescription(infographic.getDescription());
        newAdminInfographicDto.setType(infographic.getType());
        return newAdminInfographicDto;
    }

    public static InfographicDto fromEntityToDto(Infographic infographic) {
        return new InfographicDto(
                infographic.getPath(),
                infographic.getDescription(),
                infographic.getType()
        );
    }

    public static Infographic fromAdminDtoToEntity(AdminInfographicDto adminInfographicDto) {
        Infographic newInfographic = new Infographic();
        newInfographic.setId(adminInfographicDto.getId());
        newInfographic.setPath(adminInfographicDto.getPath());
        newInfographic.setDescription(adminInfographicDto.getDescription());
        newInfographic.setType(adminInfographicDto.getType());
        return newInfographic;
    }
}
