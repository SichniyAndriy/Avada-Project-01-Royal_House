package edu.avada.course;

import edu.avada.course.model.dto.AddressDto;
import edu.avada.course.model.dto.BannerDto;
import edu.avada.course.model.dto.BidDto;
import edu.avada.course.model.dto.CompanyServiceDto;
import edu.avada.course.model.dto.ImageDto;
import edu.avada.course.model.dto.InfographicDto;
import edu.avada.course.model.dto.NewBuildingDto;
import edu.avada.course.model.dto.UnitDto;
import edu.avada.course.model.entity.Address;
import edu.avada.course.model.entity.Bid;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.model.entity.CompanyService;
import edu.avada.course.model.entity.CompanyService.ServiceStatus;
import edu.avada.course.model.entity.NewBuilding;
import edu.avada.course.model.entity.NewBuilding.Description;
import edu.avada.course.model.entity.NewBuilding.Location;
import edu.avada.course.model.entity.NewBuilding.NewBuildStatus;
import edu.avada.course.model.entity.Unit;
import edu.avada.course.model.entity.Unit.UnitType;
import edu.avada.course.repository.AddressRepository;
import edu.avada.course.repository.BidRepository;
import edu.avada.course.repository.CompanyServiceRepository;
import edu.avada.course.repository.NewBuildingRepository;
import edu.avada.course.repository.UnitRepository;
import edu.avada.course.service.AdminAddressService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUtil {
    private final Faker FAKER = new Faker(Locale.getDefault());
    @Autowired
    private AdminAddressService adminAddressService;

    public void initBids(BidRepository repository, int amount) {
        for (int i = 0; i < amount; i++) {
            BidDto bidDto = new BidDto(
                    FAKER.name().fullName(),
                    FAKER.phoneNumber().cellPhone(),
                    FAKER.internet().emailAddress(),
                    FAKER.lorem().sentence(FAKER.random().nextInt(10, 100)),
                    LocalDateTime.ofInstant(FAKER.timeAndDate().past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()),
                    (FAKER.random().nextBoolean() ? BidStatus.NEW : BidStatus.ANSWERED));
            repository.save(Bid.fromDto(bidDto));
        }
    }

    public void initAddress(AddressRepository repository, int amount) {
        for (int j = 0; j < amount; j++) {
            AddressDto addressDto = new AddressDto(
                    FAKER.address().city(),
                    FAKER.address().streetName(),
                    FAKER.address().buildingNumber()
            );
            repository.save(Address.fromDto(addressDto));
        }
    }

    public void initUnits(
            UnitRepository unitRepository,
            UnitType type,
            int amount
    ) {
        for (int i = 0; i < amount; i++) {
            UnitDto unitDto = new UnitDto(
                    type,
                    BigDecimal.valueOf(FAKER.random().nextDouble() * FAKER.random().nextInt(50, 150)),
                    new BigDecimal(FAKER.commerce().price(1000, 5000).replace(',', '.')),
                    new BigDecimal(FAKER.commerce().price(25000, 250000).replace(',', '.')),
                    FAKER.random().nextInt(1, 10),
                    FAKER.random().nextInt(1, 30),
                    FAKER.random().nextInt(1, 30),
                    LocalDate.ofInstant(FAKER.timeAndDate().past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()),
                    FAKER.random().nextInt(500),
                    adminAddressService.getAnyAddress(),
                    new ArrayList<ImageDto>()
            );
            unitRepository.save(Unit.fromDto(unitDto));
        }
    }

    public void initNewBuildings(NewBuildingRepository newBuildingRepository, int amount) {

        for (int i = 0; i < amount; i++) {
            Description description = new Description();
            description.setAbout(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setLocation(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setInfrastructure(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setFlats(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            NewBuildingDto newBuildingDto = new NewBuildingDto(
                    FAKER.funnyName().name(),
                    description,
                    Location.of(FAKER.address().latitude(), FAKER.address().longitude()),
                    (FAKER.random().nextBoolean() ? NewBuildStatus.ACTIVE: NewBuildStatus.OFF),
                    adminAddressService.getAnyAddress(),
                    null,
                    new ArrayList<BannerDto>(),
                    new ArrayList<InfographicDto>(),
                    new ArrayList<UnitDto>()
            );
            newBuildingRepository.save(NewBuilding.fromDto(newBuildingDto));
        }
    }

    public void initService(CompanyServiceRepository companyServiceRepository, int amount) {
        for (int i = 0; i < amount; ++i) {
            CompanyServiceDto serviceDto = new CompanyServiceDto(
                    FAKER.lorem().characters(10, 15),
                    FAKER.lorem().characters(150, 250),
                    LocalDate.ofInstant(FAKER.timeAndDate().past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()),
                    (FAKER.random().nextBoolean() ? ServiceStatus.YES: ServiceStatus.NO),
                    null
            );
            companyServiceRepository.save(CompanyService.fromDto(serviceDto));
        }
    }
}
