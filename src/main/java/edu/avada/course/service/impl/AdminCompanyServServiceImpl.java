package edu.avada.course.service.impl;

import edu.avada.course.mapper.CompanyServiceMapper;
import edu.avada.course.model.admindto.AdminCompanyServiceDto;
import edu.avada.course.model.entity.CompanyService;
import edu.avada.course.model.entity.CompanyService.ServiceStatus;
import edu.avada.course.repository.CompanyServiceRepository;
import edu.avada.course.service.AdminCompanyServService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminCompanyServServiceImpl implements AdminCompanyServService {
    private final CompanyServiceRepository companyServiceRepository;

    public AdminCompanyServServiceImpl(
            @Autowired CompanyServiceRepository companyServiceRepository
    ) {
        this.companyServiceRepository = companyServiceRepository;
    }

    @Override
    public List<AdminCompanyServiceDto> findAll() {
        return companyServiceRepository.findAll().stream()
                .map(CompanyServiceMapper::fromEntityToAdminDto)
                .toList();
    }

    @Override
    public Page<AdminCompanyServiceDto> getPageCompanyServices(int page, int size) {
        Page<CompanyService> companyServicePage =
                companyServiceRepository.findAll(PageRequest.of(page, size, Sort.by("title")));
        return companyServicePage.map(CompanyServiceMapper::fromEntityToAdminDto);
    }

    @Override
    public AdminCompanyServiceDto getCompanyServiceById(long id) {
        CompanyService companyService = companyServiceRepository.findById(id);
        return CompanyServiceMapper.fromEntityToAdminDto(companyService);
    }

    @Override
    public void deleteCompanyServiceById(long id) {
        companyServiceRepository.deleteById(id);
    }

    @Override
    public void changeCompanyServiceStatusById(long id) {
        CompanyService companyService = companyServiceRepository.findById(id);
        ServiceStatus newStatus = companyService.getStatus() ==
                ServiceStatus.YES ? ServiceStatus.NO : ServiceStatus.YES;
        companyService.setStatus(newStatus);
        companyServiceRepository.save(companyService);
    }

    @Override
    public void updateCompanyService(AdminCompanyServiceDto adminCompanyServiceDto) {
        CompanyService companyService =
                CompanyServiceMapper.fromAdminDtoToEntity(adminCompanyServiceDto);
        companyServiceRepository.save(companyService);
    }

    @Override
    public long add(AdminCompanyServiceDto adminCompanyServiceDto) {
        CompanyService companyService =
                CompanyServiceMapper.fromAdminDtoToEntity(adminCompanyServiceDto);
        return companyServiceRepository.save(companyService).getId();
    }
}
