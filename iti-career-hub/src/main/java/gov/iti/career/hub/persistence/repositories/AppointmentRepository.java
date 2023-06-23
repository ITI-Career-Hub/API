package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

    @Query("""
                SELECT a FROM Appointment a
                WHERE a.appointmentDate = :date
            """)
    Collection<Appointment> findByAppointmentDate(LocalDate date);
}
