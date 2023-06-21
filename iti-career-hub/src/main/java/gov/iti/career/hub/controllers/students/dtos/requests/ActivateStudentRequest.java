package gov.iti.career.hub.controllers.students.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record ActivateStudentRequest(String password, String email, String firstName, String lastName, String college,
                                     String phoneNumber, Short graduationYear) implements Serializable {
}