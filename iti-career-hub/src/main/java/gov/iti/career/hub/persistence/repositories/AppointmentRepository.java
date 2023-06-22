package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    List<Appointment> findByCompany_Id(@NonNull Integer id);
}
