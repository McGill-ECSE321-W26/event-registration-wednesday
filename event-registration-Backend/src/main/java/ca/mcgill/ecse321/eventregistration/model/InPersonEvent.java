package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import jakarta.persistence.Entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

// line 13 "model.ump"
// line 56 "model.ump"
@Entity
public class InPersonEvent extends Event {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //InPersonEvent Attributes
    private String address;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public InPersonEvent(String aName, LocalDate aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit, String aAddress) {
        super(aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
        address = aAddress;
    }

    // Hibernate needs a no-arg constructor, but this can be protected
    protected InPersonEvent() {
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setAddress(String aAddress) {
        boolean wasSet = false;
        address = aAddress;
        wasSet = true;
        return wasSet;
    }

    public String getAddress() {
        return address;
    }

    public void delete() {
        super.delete();
    }


    public String toString() {
        return super.toString() + "[" +
                "address" + ":" + getAddress() + "]";
    }
}