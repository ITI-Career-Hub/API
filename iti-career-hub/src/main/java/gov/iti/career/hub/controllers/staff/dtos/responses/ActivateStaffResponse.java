package gov.iti.career.hub.controllers.staff.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record ActivateStaffResponse(Integer id, String username, String email, Integer roleId, RoleName roleRoleName,
                                    String firstName, String lastName, Integer departmentId, String departmentName,
                                    String pictureUrl) implements Serializable {
}