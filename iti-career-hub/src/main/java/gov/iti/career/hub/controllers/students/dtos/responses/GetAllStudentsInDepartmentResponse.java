package gov.iti.career.hub.controllers.students.dtos.responses;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record GetAllStudentsInDepartmentResponse(Integer id, String username, String email, String resumeUrl, String pictureUrl,
                                                 String firstName, String lastName, String college, String phoneNumber,
                                                 Short intakeNumber, Short graduationYear)
implements Serializable {
}