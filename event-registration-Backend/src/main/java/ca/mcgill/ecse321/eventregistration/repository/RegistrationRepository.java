package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration, Registration.Key> {
    Registration findRegistrationByKey(Registration.Key key);

    Iterable<Registration> findByKeyRegistrantIdOrderByKeyEventId(int registrantId);
}
