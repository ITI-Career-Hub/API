package gov.iti.career.hub.controllers.students.dtos.responses;

import gov.iti.career.hub.controllers.Response;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record UpdateStudentResponse(Integer id, String username, String email, String firstName, String lastName, String college,
                                    String phoneNumber, Short intakeNumber, Short graduationYear, Integer departmentId,
                                    String departmentName) implements Serializable, Response {
}