package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Person createPerson(@Valid PersonCreationDto personCreationDto) {
        LocalDate today = LocalDate.now();
        Person newPerson = new Person(
                personCreationDto.getName(),
                personCreationDto.getEmail(),
                personCreationDto.getPassword(),
                today
        );
        return personRepository.save(newPerson);
    }

    public Person findPersonById(int id) {
        Person personFromDB = personRepository.findPersonById(id);
        if (personFromDB == null) {
            throw new EventRegistrationException(HttpStatus.NOT_FOUND, "No person found with id " + id);
        }
        return personFromDB;
    }
}