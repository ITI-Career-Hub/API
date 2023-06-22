package gov.iti.career.hub.controllers.appointments.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Attendance} entity
 */
public record GetAttendanceResponse(Integer id, Integer studentId, Long appointmentId,
                                    AttendanceStatus attendanceStatus) implements Serializable {
}