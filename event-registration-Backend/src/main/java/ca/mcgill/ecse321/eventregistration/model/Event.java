package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 * @@@skipcppcompile - Contains Java code
 * @@@skipphpcompile - Contains Java code
 * @@@skiprubycompile - Contains Java code
 * @@@skippythoncompile - Contains Java code
 * Positioning
 */
// line 2 "model.ump"
// line 51 "model.ump"
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Event Attributes
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private int registrationLimit;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Event(String aName, LocalDate aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit) {
        name = aName;
        date = aDate;
        startTime = aStartTime;
        endTime = aEndTime;
        registrationLimit = aRegistrationLimit;
    }

    // Hibernate needs a no-arg constructor, but this can be protected
    public Event() {
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

    public boolean setDate(LocalDate aDate) {
        boolean wasSet = false;
        date = aDate;
        wasSet = true;
        return wasSet;
    }

    public boolean setStartTime(Time aStartTime) {
        boolean wasSet = false;
        startTime = aStartTime;
        wasSet = true;
        return wasSet;
    }

    public boolean setEndTime(Time aEndTime) {
        boolean wasSet = false;
        endTime = aEndTime;
        wasSet = true;
        return wasSet;
    }

    public boolean setRegistrationLimit(int aRegistrationLimit) {
        boolean wasSet = false;
        registrationLimit = aRegistrationLimit;
        wasSet = true;
        return wasSet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public int getRegistrationLimit() {
        return registrationLimit;
    }

    public void delete() {
    }


    public String toString() {
        return super.toString() + "[" +
                "id" + ":" + getId() + "," +
                "name" + ":" + getName() + "," +
                "registrationLimit" + ":" + getRegistrationLimit() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this) ? getDate().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this) ? getStartTime().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this) ? getEndTime().toString().replaceAll("  ", "    ") : "this" : "null");
    }
}