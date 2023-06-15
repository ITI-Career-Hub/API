package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface EventRepository extends JpaRepository<Event, Integer>
                                            ,ListPagingAndSortingRepository<Event, Integer> {
}
