package gov.iti.career.hub.controllers.appointments.dtos.requests;

import java.io.Serializable;
import java.time.*;
import java.util.List;
import java.util.Set;

import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Event;
import gov.iti.career.hub.persistence.entities.enums.InterviewType;

public record AddAppointmentRequest(String appointmentName, LocalDate appointmentDate, InterviewType interviewType, String[] interviewers, String room, String interviewNotes,
                                    Integer departmentId, Integer companyId, Integer eventId, List<Integer> studentIds) implements Serializable {
    
}
