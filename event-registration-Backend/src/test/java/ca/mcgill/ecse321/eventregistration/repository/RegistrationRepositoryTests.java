package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegistrationRepositoryTests {

    private final LocalDate TODAY = LocalDate.of(2024, 12, 15);

    private final Person MIKE = new Person(
            "Mike",
            "mike@mail.mcgill.ca",
            "mike123",
            TODAY);

    private final InPersonEvent CONCERT = new InPersonEvent(
            "Concert",
            TODAY,
            Time.valueOf("18:00:00"),
            Time.valueOf("21:00:00"),
            100,
            "1234 Main St, Montreal, QC");

    private final InPersonEvent CONCERT2 = new InPersonEvent(
            "Concert2",
            TODAY,
            Time.valueOf("19:00:00"),
            Time.valueOf("22:00:00"),
            200,
            "4321 Main St, Montreal, QC");

    @Autowired
    private RegistrationRepository regRepo;
    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private EventRepository eventRepo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        regRepo.deleteAll();
        personRepo.deleteAll();
        eventRepo.deleteAll();
    }

    @Test
    public void testCreateAndReadRegistration() {
        // Arrange
        personRepo.save(MIKE);

        eventRepo.save(CONCERT);

        Registration registration = new Registration(new Registration.Key(MIKE, CONCERT));
        regRepo.save(registration);

        // Act
        Registration registrationFromDb = regRepo.findRegistrationByKey(registration.getKey());

        // Assert
        assertNotNull(registrationFromDb);
        assertNotNull(registrationFromDb.getKey());
        assertNotNull(registrationFromDb.getKey().getRegistrant());
        assertEquals(registrationFromDb.getKey().getRegistrant().getId(), MIKE.getId());
        assertNotNull(registrationFromDb.getKey().getEvent());
        assertEquals(registrationFromDb.getKey().getEvent().getId(), CONCERT.getId());
    }

    @Test
    public void testFindRegistrationsByRegistrantId() {
        // Arrange
        personRepo.save(MIKE);
        eventRepo.save(CONCERT);
        eventRepo.save(CONCERT2);

        Registration registration = new Registration(new Registration.Key(MIKE, CONCERT));
        regRepo.save(registration);

        Registration registration2 = new Registration(new Registration.Key(MIKE, CONCERT2));
        regRepo.save(registration2);

        // Act
        List<Registration> registrationsFromDb = (List<Registration>) regRepo.findByKeyRegistrantIdOrderByKeyEventId(MIKE.getId());

        // Assert
        int count = 0;
        for (Registration r : registrationsFromDb) {
            assertNotNull(r);
            assertNotNull(r.getKey());
            assertNotNull(r.getKey().getRegistrant());
            assertEquals(r.getKey().getRegistrant().getId(), MIKE.getId());
            assertNotNull(r.getKey().getEvent());
            count++;
        }
        assertEquals(2, count);
        assertEquals(CONCERT.getId(), registrationsFromDb.get(0).getKey().getEvent().getId());
        assertEquals(CONCERT2.getId(), registrationsFromDb.get(1).getKey().getEvent().getId());
    }
}
