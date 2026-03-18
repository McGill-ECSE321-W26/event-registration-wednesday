package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import org.springframework.data.repository.CrudRepository;

public interface InPersonEventRepository extends CrudRepository<InPersonEvent, Integer> {
    InPersonEvent findEventById(int id);
}
