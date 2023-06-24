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

    @Query("""
            SELECT a FROM Appointment a
            WHERE a.event.id = :eventId
            AND a.company.id = :companyId
        """)
    Collection<Appointment> getAllAppointmentsByCompanyAndEvent(Integer companyId, Integer eventId);

    @Query("""
            SELECT a FROM Appointment a
            WHERE a.department.id = :departmentId
            AND a.event.id = :eventId
            """)
    Collection<Appointment> findAppointmentForDepartmentInEvent(Integer departmentId, Integer eventId);
}
