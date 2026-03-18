package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponseDto createPerson(@RequestBody PersonCreationDto personCreationDto) {
        Person newPerson = personService.createPerson(personCreationDto);
        return new PersonResponseDto(newPerson);
    }

    @GetMapping("/person/{id}")
    public PersonResponseDto findPersonById(@PathVariable int id){
        Person person = personService.findPersonById(id);
        return new PersonResponseDto(person);
    }

}