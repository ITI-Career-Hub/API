package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>
                                        {
}
