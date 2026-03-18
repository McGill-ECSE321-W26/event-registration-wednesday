package ca.mcgill.ecse321.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PersonCreationDto {
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @Email
    @NotBlank(message = "Email cannot be empty or null.")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @NotBlank(message = "Password cannot be empty or null")
    private String password;

    // Needed by Jackson to deserialize JSON
    @SuppressWarnings("unused")
    public PersonCreationDto() {
    }

    public PersonCreationDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}