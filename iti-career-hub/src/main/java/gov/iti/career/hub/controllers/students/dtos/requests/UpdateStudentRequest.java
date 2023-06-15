package gov.iti.career.hub.controllers.students.dtos.requests;

import gov.iti.career.hub.controllers.Request;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record UpdateStudentRequest(
        @Size(min = 8, max = 50, message = "Username cannot be less than 8 or exceed 50 characters")
        String username,
        @Email
        String email,
        @Size(min = 8, max = 15, message = "First name cannot be less than 8 or exceed 15 characters")
        String firstName,
        @Size(min = 8, max = 15, message = "Last name cannot be less than 8 or exceed 15 characters")
        String lastName,
        @Size(min = 10, max = 80, message = "College cannot be less than 10 or exceed 80 characters")
        String college,

        @Pattern(regexp = "^01[0125][0-9]{8}$", message = "Invalid phone number")
        String phoneNumber,
        @Min(value = 1, message = "Intake number must exceed 1")
        Short intakeNumber,
        @Min(value = 1980, message = "Graduation year must exceed 1980")
        Short graduationYear)
        implements Serializable , Request {
}