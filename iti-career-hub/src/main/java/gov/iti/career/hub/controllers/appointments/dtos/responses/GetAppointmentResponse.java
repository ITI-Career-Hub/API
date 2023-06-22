package gov.iti.career.hub.controllers.appointments.dtos.responses;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Event;
import gov.iti.career.hub.persistence.entities.enums.InterviewType;

public record GetAppointmentResponse(Integer id,String appointmentName, LocalDate appointmentDate,
                                     InterviewType interviewType, String[] interviewers, Integer roomId, String roomName,
                                     String interviewNotes, Integer departmentId, String departmentName,
                                     Integer companyId,String companyName, Integer eventId,String eventName)
implements Serializable {
    
}
