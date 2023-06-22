package gov.iti.career.hub.controllers.staff.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record UpdateStaffResponse(Integer id, String username, String email, Integer roleId, RoleName roleName,
                                  String firstName, String lastName, Integer departmentId,
                                  String departmentName) implements Serializable {
}