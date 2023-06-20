package gov.iti.career.hub.controllers.staff.dtos.responses;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record ActivateStaffResponse(Integer id, String username, String email, Integer roleId, String roleRoleName,
                                    String firstName, String lastName, Integer departmentId,
                                    String departmentDepartmentName) implements Serializable {
}