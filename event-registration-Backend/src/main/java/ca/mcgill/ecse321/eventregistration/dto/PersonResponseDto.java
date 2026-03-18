package ca.mcgill.ecse321.eventregistration.dto;


import ca.mcgill.ecse321.eventregistration.model.Person;

import java.time.LocalDate;

public class PersonResponseDto {
    private int id;
    private String name;
    private String email;
    private LocalDate creationDate;

    // Needed by Jackson to deserialize JSON
    @SuppressWarnings("unused")
    public PersonResponseDto() {
    }

    public PersonResponseDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.creationDate = person.getCreationDate();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}