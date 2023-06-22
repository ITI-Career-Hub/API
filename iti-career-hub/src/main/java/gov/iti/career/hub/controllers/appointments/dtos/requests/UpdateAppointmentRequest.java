package gov.iti.career.hub.controllers.appointments.dtos.requests;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Event;
import gov.iti.career.hub.persistence.entities.enums.InterviewType;

public record UpdateAppointmentRequest(String appointmentName, LocalDate appointmentDate, InterviewType interviewType, String[] interviewers, String room, String interviewNotes, 
                                    Integer departmentId, Integer companyId, Integer eventId) implements Serializable {
    
}