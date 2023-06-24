package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Integer>
                                            ,ListPagingAndSortingRepository<Event, Integer> {

    @Query("""
            SELECT e FROM Appointment a
            JOIN Event e
            ON e.id = a.event.id
            WHERE a.company.id = :companyId
        """)
    Collection<Event> getEventsForCompany(Integer companyId);

    @Query("""
            SELECT e FROM Appointment a
            JOIN Event e
            ON e.id = a.event.id
            WHERE a.department.id = :departmentId
        """)
    Collection<Event> getEventsForDepartment(Integer departmentId);
}
