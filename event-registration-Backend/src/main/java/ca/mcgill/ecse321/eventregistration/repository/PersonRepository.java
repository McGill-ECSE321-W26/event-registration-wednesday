package ca.mcgill.ecse321.eventregistration.repository;

import ca.mcgill.ecse321.eventregistration.model.Person;
import org.springframework.data.repository.CrudRepository;

// CRUDRepository for a Person with Integer as the ID type
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findPersonById(int id);
}
