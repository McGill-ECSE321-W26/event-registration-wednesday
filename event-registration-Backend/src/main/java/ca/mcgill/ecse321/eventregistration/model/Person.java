package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

// line 27 "model.ump"
// line 61 "model.ump"
@Entity
public class Person {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Person Attributes
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDate creationDate;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Person(String aName, String aEmail, String aPassword, LocalDate aCreationDate) {
        name = aName;
        email = aEmail;
        password = aPassword;
        creationDate = aCreationDate;
    }

    // Hibernate needs a no-arg constructor, but this can be protected
    protected Person() {
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setEmail(String aEmail) {
        boolean wasSet = false;
        email = aEmail;
        wasSet = true;
        return wasSet;
    }

    public boolean setPassword(String aPassword) {
        boolean wasSet = false;
        password = aPassword;
        wasSet = true;
        return wasSet;
    }

    public boolean setCreationDate(LocalDate aCreationDate) {
        boolean wasSet = false;
        creationDate = aCreationDate;
        wasSet = true;
        return wasSet;
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

    public String getPassword() {
        return password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void delete() {
    }


    public String toString() {
        return super.toString() + "[" +
                "id" + ":" + getId() + "," +
                "name" + ":" + getName() + "," +
                "email" + ":" + getEmail() + "," +
                "password" + ":" + getPassword() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "creationDate" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this) ? getCreationDate().toString().replaceAll("  ", "    ") : "this" : "null");
    }
}