package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Registration {
    @EmbeddedId
    private Key key;

    @OneToOne
    private Category category;

    // Hibernate needs a no-args constructor, but it can be protected
    protected Registration() {
    }

    public Registration(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Registration)) {
            return false;
        }
        Registration that = (Registration) obj;
        return this.key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    // See
    // https://docs.jboss.org/hibernate/orm/6.5/userguide/html_single/Hibernate_User_Guide.html#identifiers-composite
    // for the rules on composite IDs.
    @Embeddable
    public static class Key implements Serializable {
        @ManyToOne
        private Person registrant;
        @ManyToOne
        private Event event;

        public Key() {
            super();
        }

        public Key(Person registrant, Event event) {
            this.registrant = registrant;
            this.event = event;
        }

        public Person getRegistrant() {
            return registrant;
        }

        public Event getEvent() {
            return event;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key that = (Key) obj;
            return this.getRegistrant().getId() == that.getRegistrant().getId()
                    && this.getEvent().getId() == that.getEvent().getId();
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getRegistrant().getId(), this.getEvent().getId());
        }
    }
}