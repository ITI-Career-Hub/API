package gov.iti.career.hub.controllers.appointments.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
import gov.iti.career.hub.persistence.entities.enums.InterviewType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Attendance} entity
 */
public record AttendanceResponse(Integer id, Integer studentId, Long appointmentId,
                                 LocalDate appointmentAppointmentDate, InterviewType appointmentInterviewType,
                                 Integer appointmentRoomId, String appointmentRoomName, Integer appointmentCompanyId,
                                 String appointmentCompanyCompanyName,
                                 AttendanceStatus attendanceStatus) implements Serializable {
}