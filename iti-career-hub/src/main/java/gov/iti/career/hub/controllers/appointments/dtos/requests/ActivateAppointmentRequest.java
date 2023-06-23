package gov.iti.career.hub.controllers.appointments.dtos.requests;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Appointment} entity
 */
public record ActivateAppointmentRequest(LocalDate appointmentDate, Integer roomId) implements Serializable {
}