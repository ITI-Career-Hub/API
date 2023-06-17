package gov.iti.career.hub.controllers.register.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record RegisterStudentRequest(String username, Short intakeNumber, String email,
                                     Integer departmentId) implements Serializable {
}