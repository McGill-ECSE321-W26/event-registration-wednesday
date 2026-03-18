package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
/*
 * MockitoSettings with Strictness.STRICT_STUBS is used to ensure that all stubs created during testing are used,
 * helping to identify unnecessary stubs and improve test reliability.
 * It ensures that any stubs that are set up but not used in the test will cause the test to fail,
 * promoting cleaner and more maintainable test code.
 */
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PersonServiceTests {

    private static final String VALID_NAME = "Byron Chen";
    private static final String VALID_EMAIL = "byron@gmail.com";
    private static final String VALID_PASSWORD = "ByronC123456#";
    private static final LocalDate TODAY = LocalDate.now();

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testFindPersonByValidId() {
        // Arrange
        Person createdPerson = new Person(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, TODAY);
        when(personRepository.findPersonById(11)).thenReturn(createdPerson);

        // Act
        Person p = personService.findPersonById(11);

        // Assert
        assertNotNull(p);
        assertEquals(createdPerson.getName(), p.getName());
        assertEquals(createdPerson.getEmail(), p.getEmail());
        assertEquals(createdPerson.getPassword(), p.getPassword());
        assertEquals(createdPerson.getCreationDate(), p.getCreationDate());
    }

    @Test
    public void testFindPersonByInvalidId() {
        // Arrange
        when(personRepository.findPersonById(11)).thenReturn(null);

        // Act
        EventRegistrationException e = assertThrows(
                EventRegistrationException.class,
                () -> personService.findPersonById(11)
        );
        assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        assertEquals("No person found with id 11", e.getMessage());

        /*
         * assertThrows basically works like this:
         *  try {
         *      service.findPersonById(42);
         * 		fail("Nothing was thrown");
         * 	} catch (EventRegistrationException e) {
         * 	    assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
         * 		assertEquals("There is no person with ID 42", e.getMessage());
         * 	}
         */
    }

    @Test
    public void createValidPerson() {
        // Arrange
        // Mock the save() method to return the Person object passed to it as an argument when called
        when(personRepository.save(any(Person.class))).thenAnswer(
                (InvocationOnMock invocation) -> invocation.getArgument(0));

        // Act
        PersonCreationDto createdPerson = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);
        Person p = personService.createPerson(createdPerson);

        // Assert
        assertNotNull(p);
        assertEquals(VALID_NAME, p.getName());
        assertEquals(VALID_EMAIL, p.getEmail());
        assertEquals(VALID_PASSWORD, p.getPassword());
        assertEquals(TODAY, p.getCreationDate());

        // Check that the save() method was called with a correct argument
        verify(personRepository, times(1))
                .save(argThat((Person pp) -> VALID_NAME.equals(pp.getName())
                        && VALID_EMAIL.equals(pp.getEmail())));
    }
}