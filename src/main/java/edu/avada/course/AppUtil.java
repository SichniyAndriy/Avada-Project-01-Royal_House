package edu.avada.course;

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
            Bid newBid = new Bid();
            newBid.setName(FAKER.name().fullName());
            newBid.setPhone(FAKER.phoneNumber().cellPhone());
            newBid.setEmail(FAKER.internet().emailAddress());
            newBid.setComment(FAKER.lorem().sentence(FAKER.random().nextInt(10, 100)));
            newBid.setDate(LocalDate.ofInstant(FAKER.timeAndDate()
                    .past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()));
            newBid.setStatus(
                    (FAKER.random().nextBoolean() ? BidStatus.NEW : BidStatus.ANSWERED)
            );
            repository.save(newBid);
        }
    }

    public void initAddress(AddressRepository repository, int amount) {
        for (int j = 0; j < amount; j++) {
            Address newAddress = new Address();
            newAddress.setCity(FAKER.address().city());
            newAddress.setStreet(FAKER.address().streetName());
            newAddress.setHouseNumber(FAKER.address().buildingNumber());
            repository.save(newAddress);
        }
    }

    public void initUnits(
            UnitRepository unitRepository,
            UnitType type,
            int amount
    ) {
        for (int i = 0; i < amount; i++) {
            Unit newUnit = new Unit();
            newUnit.setType(type);
            newUnit.setSquare(BigDecimal.valueOf(FAKER.random().nextDouble() * FAKER.random().nextInt(50, 150)));
            newUnit.setTotalPrice(new BigDecimal(FAKER.commerce().price(1000, 5000).replace(',', '.')));
            newUnit.setPricePerSqM(new BigDecimal(FAKER.commerce().price(25000, 250000).replace(',', '.')));
            newUnit.setRooms(FAKER.random().nextInt(1, 10));
            newUnit.setFloor(FAKER.random().nextInt(1, 30));
            newUnit.setTotalFloors(FAKER.random().nextInt(1, 30));
            newUnit.setDate(LocalDate.ofInstant(FAKER.timeAndDate().past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()));
            newUnit.setFlatNumber(FAKER.random().nextInt(500));
            newUnit.setAddress(adminAddressService.getAnyAddress());
            unitRepository.save(newUnit);
        }
    }

    public void initNewBuildings(NewBuildingRepository newBuildingRepository, int amount) {

        for (int i = 0; i < amount; i++) {
            Description description = new Description();
            description.setAbout(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setLocation(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setInfrastructure(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));
            description.setFlats(FAKER.lorem().sentence(FAKER.random().nextInt(50, 100)));

            NewBuilding newBuilding = new NewBuilding();
            newBuilding.setTitle(FAKER.funnyName().name());
            newBuilding.setLocation(Location.of(FAKER.address().latitude(), FAKER.address().longitude()));
            newBuilding.setStatus(FAKER.random().nextBoolean() ? NewBuildStatus.ACTIVE: NewBuildStatus.OFF);
            newBuilding.setDescription(description);
            newBuilding.setAddress(adminAddressService.getAnyAddress());
            newBuildingRepository.save(newBuilding);
        }
    }

    public void initService(CompanyServiceRepository companyServiceRepository, int amount) {
        for (int i = 0; i < amount; ++i) {
            CompanyService newService = new CompanyService();
            newService.setTitle(FAKER.lorem().characters(10, 15));
            newService.setDescription(FAKER.lorem().characters(150, 250));
            newService.setDate(LocalDate.ofInstant(FAKER.timeAndDate()
                    .past(2000, TimeUnit.DAYS), TimeZone.getDefault().toZoneId()));
            newService.setStatus(FAKER.random().nextBoolean() ? ServiceStatus.YES: ServiceStatus.NO);
            companyServiceRepository.save(newService);
        }
    }
}
