package edu.avada.course.service.impl;

import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.service.NewBldsService;
import org.springframework.beans.factory.annotation.Autowired;

public class NewBldsServiceImpl implements NewBldsService {
    private NewBuildingRepository newBuildingRepository;

    public NewBldsServiceImpl(
            @Autowired NewBuildingRepository newBuildingRepository
    ) {
        this.newBuildingRepository = newBuildingRepository;
    }
}
