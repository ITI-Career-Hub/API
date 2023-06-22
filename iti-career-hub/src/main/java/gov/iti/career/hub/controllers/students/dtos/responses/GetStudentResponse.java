package gov.iti.career.hub.controllers.students.dtos.responses;

import gov.iti.career.hub.controllers.Response;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.entities.enums.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Student} entity
 */
public record GetStudentResponse(
        Integer id, String username, String password, String email,
        Integer roleId, RoleName roleName, String firstName, String lastName,
        String college, String phoneNumber, Short intakeNumber, Short graduationYear,
        Integer departmentId, String departmentName, Discipline departmentDiscipline )

        implements Serializable, Response {}