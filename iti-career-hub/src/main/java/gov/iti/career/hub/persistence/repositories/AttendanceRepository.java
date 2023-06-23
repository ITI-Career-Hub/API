package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Attendance;
import gov.iti.career.hub.persistence.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>
                                                , ListPagingAndSortingRepository<Attendance, Integer> {
    List<Attendance> findByStudentIdAndAppointmentIsApprovedTrue(Integer studentId);
}
