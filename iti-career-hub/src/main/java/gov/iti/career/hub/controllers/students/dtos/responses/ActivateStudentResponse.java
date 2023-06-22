package gov.iti.career.hub.controllers.students.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.entities.enums.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record ActivateStudentResponse(Integer id, String username, String email, Integer roleId, RoleName roleName,
                                      String firstName, String lastName, String college, String phoneNumber,
                                      Short graduationYear, Integer departmentId, String departmentName,
                                      Discipline departmentDiscipline) implements Serializable {
}