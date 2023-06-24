package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Attendance;
import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>
                                                , ListPagingAndSortingRepository<Attendance, Integer> {
    List<Attendance> findByStudentIdAndAppointmentIsApprovedTrue(Integer studentId);

    @Modifying
    @Transactional
    @Query("UPDATE Attendance a SET a.attendanceStatus = :newStatus WHERE a.id = :attendanceId")
    void updateAttendanceStatus(@Param("attendanceId") Integer attendanceId, @Param("newStatus") AttendanceStatus newStatus);
}
