package gov.iti.career.hub.controllers.departments.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.InterviewType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Appointment} entity
 */
public record GetAppointmentsForDepartmentInEvent(Long id, String appointmentName, LocalDate appointmentDate,
                                                  InterviewType interviewType, String[] interviewers, Integer roomId,
                                                  String roomName, String interviewNotes, Integer companyId,
                                                  String companyName, Boolean isApproved) implements Serializable {
}