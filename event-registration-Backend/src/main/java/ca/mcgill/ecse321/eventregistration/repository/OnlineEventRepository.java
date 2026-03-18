package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import org.springframework.data.repository.CrudRepository;

public interface OnlineEventRepository extends CrudRepository<OnlineEvent, Integer> {
    OnlineEvent findEventById(int id);
}
