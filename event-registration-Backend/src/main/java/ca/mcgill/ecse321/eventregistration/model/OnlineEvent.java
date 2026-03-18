package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import jakarta.persistence.Entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

// line 21 "model.ump"
// line 73 "model.ump"
@Entity
public class OnlineEvent extends Event {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //OnlineEvent Attributes
    private String url;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public OnlineEvent(String aName, LocalDate aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit, String aUrl) {
        super(aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
        url = aUrl;
    }

    // Hibernate needs a no-arg constructor, but this can be protected
    protected OnlineEvent() {
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setUrl(String aUrl) {
        boolean wasSet = false;
        url = aUrl;
        wasSet = true;
        return wasSet;
    }

    public String getUrl() {
        return url;
    }

    public void delete() {
        super.delete();
    }


    public String toString() {
        return super.toString() + "[" +
                "url" + ":" + getUrl() + "]";
    }
}