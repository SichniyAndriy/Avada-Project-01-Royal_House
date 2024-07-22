package edu.avada.course.service.impl;

import edu.avada.course.mapper.CompanyServiceMapper;
import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import edu.avada.course.model.entity.CompanyService;
import edu.avada.course.model.entity.CompanyService.ServiceStatus;
import edu.avada.course.repository.CompanyServiceRepository;
import edu.avada.course.service.AdminCompanyServService;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCompanyServServiceImpl implements AdminCompanyServService {
    private final CompanyServiceRepository companyServiceRepository;
    private Map<Long, CompanyService> companyServices;

    public AdminCompanyServServiceImpl(
            @Autowired CompanyServiceRepository companyServiceRepository
    ) {
        this.companyServiceRepository = companyServiceRepository;
    }

    @Override
    public Set<AdminCompanyServiceDto> getAllCompanyServices() {
        if (companyServices == null) {
            getAllCompanyServicesFromDb();
        }
        return companyServices.values().parallelStream()
                .map(CompanyServiceMapper::fromEntityToAdminDto)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(AdminCompanyServiceDto::getId))
                ));
    }

    @Override
    public AdminCompanyServiceDto getCompanyServiceById(long id) {
        return CompanyServiceMapper.fromEntityToAdminDto(companyServices.get(id));
    }

    @Override
    public void deleteCompanyServiceById(long id) {
        CompanyService companyService = companyServices.get(id);
        companyServices.remove(id);
        companyServiceRepository.delete(companyService);
    }

    @Override
    public void changeCompanyServiceStatusById(long id) {
        CompanyService companyService = companyServices.get(id);
        ServiceStatus newStatus =
                companyService.getStatus() == ServiceStatus.YES ? ServiceStatus.NO : ServiceStatus.YES;
        companyService.setStatus(newStatus);
        companyServiceRepository.save(companyService);
    }

    @Override
    public void updateCompanyService(AdminCompanyServiceDto adminCompanyServiceDto) {
        CompanyService companyService = CompanyServiceMapper.fromAdminDtoToEntity(adminCompanyServiceDto);
        companyServices.put(companyService.getId(), companyService);
        companyServiceRepository.save(companyService);
    }

    @Override
    public long add(AdminCompanyServiceDto adminCompanyServiceDto) {
        CompanyService companyService = CompanyServiceMapper.fromAdminDtoToEntity(adminCompanyServiceDto);
        Long id = companyServiceRepository.save(companyService).getId();
        if (id > 0) {
            companyServices.put(id, companyService);
        }
        return id;
    }

    private void getAllCompanyServicesFromDb() {
        companyServices = companyServiceRepository
                .findAll()
                .parallelStream()
                .collect(Collectors.toConcurrentMap(CompanyService::getId, val -> val));
    }

}
