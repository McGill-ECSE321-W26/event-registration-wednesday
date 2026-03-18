package ca.mcgill.ecse321.eventregistration.integration;


import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * The webEnvironment = WebEnvironment.RANDOM_PORT option indicates that the embedded server will start
 * on a random available port. This ensures that tests do not interfere with each other or
 * with other applications running on the default port (8080).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

/*
 * This annotation is used to specify the order in which test methods are run.
 * MethodOrderer.OrderAnnotation.class means that the methods will be executed
 * based on the @Order annotation on each test method.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
 * This annotation is used to specify the lifecycle of the test instance.
 * With PER_CLASS, a single instance is used for all methods,
 * which allows for instance variables to be shared across tests.
 *
 * Without @TestInstance(Lifecycle.PER_CLASS), validId would be re-initialized for each test method,
 * making it impossible to share data between methods.
 * This would mean testFindPersonByValidId() would not know the ID created in testCreateValidPerson() from this.createdPersonId.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonIntegrationTests {

    /*
     * A Spring utility class designed for testing RESTful web services.
     * This class can be used to send HTTP requests like GET, POST, PUT, etc.,
     * to test REST APIs without needing an actual client (e.g., Postman or a browser).
     */
    @Autowired
    private TestRestTemplate client;

    private int createdPersonId;

    private static final String VALID_NAME = "Byron Chen";
    private static final String VALID_EMAIL = "byron@gmail.com";
    private static final String VALID_PASSWORD = "ByronC123456#";
    private static final LocalDate TODAY = LocalDate.now();

    @Autowired
    private PersonRepository personRepository;

    // Have to clear the database before and after running the tests to ensure that the tests are independent.
    @BeforeAll
    @AfterAll
    public void clearDatabase() {
        personRepository.deleteAll();
    }

    @Test
    @Order(0)
    public void testCreateValidPerson() {
        // Arrange
        PersonCreationDto body = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

        // Act
        ResponseEntity<PersonResponseDto> response = client.postForEntity("/person", body, PersonResponseDto.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0, "the ID should be a positive int");
        this.createdPersonId = response.getBody().getId();
        assertEquals(body.getName(), response.getBody().getName());
        assertEquals(body.getEmail(), response.getBody().getEmail());
        assertEquals(TODAY, response.getBody().getCreationDate());
    }

    @Test
    @Order(1)
    public void testCreatePersonShortPassword() {
        // Arrange
        String shortPassword = "Short1!";
        PersonCreationDto body = new PersonCreationDto(VALID_NAME, VALID_EMAIL, shortPassword);

        // Act
        ResponseEntity<ErrorDto> response = client.postForEntity("/person", body, ErrorDto.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertIterableEquals(
                List.of("Password must be at least 8 characters long."),
                response.getBody().getErrors()
        );
    }

    @Test
    @Order(2)
    public void testFindPersonByValidId() {
        // Arrange
        String url = String.format("/person/%d", this.createdPersonId);

        // Act
        ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.createdPersonId, response.getBody().getId());
        assertEquals(VALID_NAME, response.getBody().getName());
        assertEquals(VALID_EMAIL, response.getBody().getEmail());
        assertEquals(LocalDate.now(), response.getBody().getCreationDate());
    }
}